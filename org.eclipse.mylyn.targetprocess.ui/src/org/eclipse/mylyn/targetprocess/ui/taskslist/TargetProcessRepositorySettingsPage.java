package org.eclipse.mylyn.targetprocess.ui.taskslist;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.httpclient.URI;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.mylyn.commons.net.AuthenticationCredentials;
import org.eclipse.mylyn.commons.net.AuthenticationType;
import org.eclipse.mylyn.internal.tasks.ui.editors.EditorUtil;
import org.eclipse.mylyn.targetprocess.core.TargetProcessClient;
import org.eclipse.mylyn.targetprocess.core.TargetProcessClientFactory;
import org.eclipse.mylyn.targetprocess.core.TargetProcessCorePlugin;
import org.eclipse.mylyn.targetprocess.core.TargetProcessRepositoryStatus;
import org.eclipse.mylyn.targetprocess.modules.TPAuthenticationException;
import org.eclipse.mylyn.tasks.core.RepositoryStatus;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.TasksUi;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositorySettingsPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class TargetProcessRepositorySettingsPage extends AbstractRepositorySettingsPage {

	private static final String TIITLE = "Connection settings";
	private static final String DESCRIPTION = "Targetprocess connection settings";
	private Combo authenticationTypeCombo;
	private TargetProcessFieldEditor targetProcessUrlEditor;
	private TargetProcessFieldEditor targetProcessLoginEditor;
	private TargetProcessFieldEditor targetProcessPasswordEditor;
	private TargetProcessFieldEditor targetProcessLabelEditor;
	private String originalUrl;
	private HashSet<String> repositoryUrls;

	// private Object originalUserName;

	public TargetProcessRepositorySettingsPage(TaskRepository taskRepository) {
		super(TIITLE, DESCRIPTION, taskRepository);
		org.eclipse.mylyn.internal.tasks.ui.wizards.Messages.AbstractRepositorySettingsPage_Validate_Settings = "Check connection";
		setNeedsEncoding(false);
		setNeedsTimeZone(false);
		setNeedsProxy(false);
		setNeedsHttpAuth(false);
	}

	@Override
	public void createSettingControls(Composite parent) {
		compositeContainer = parent;

		GridLayout layout = EditorUtil.createSectionClientLayout();
		layout.numColumns = 1;
		layout.horizontalSpacing = 20;
		compositeContainer.setLayout(layout);

		targetProcessUrlEditor = createStringFieldEditor(Messages.TargetProcessRepositorySettingsPage_Url_, 470, true,
				Messages.TargetProcessRepositorySettingsPage_EmptyUrl_);
		targetProcessLabelEditor = createStringFieldEditor(Messages.TargetProcessRepositorySettingsPage_Label_, 470,
				false, "");

		Label serverLabel = new Label(compositeContainer, SWT.NONE);
		serverLabel.setText(Messages.TargetProcessRepositorySettingsPage_Authentication_);

		authenticationTypeCombo = new Combo(compositeContainer, SWT.DROP_DOWN | SWT.READ_ONLY);

		authenticationTypeCombo.setItems(new String[] {
				Messages.TargetProcessRepositorySettingsPage_TargetProcessAuthentication_,
				Messages.TargetProcessRepositorySettingsPage_IntegratedWindowsAuthentication_ });
		authenticationTypeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (repository != null) {
					repository.setProperty(TargetProcessCorePlugin.IS_WINDOWS_AUTHENTICATION_KEY,
							isWindowsAuthenticationSelected() ? "iswind" : null);
				}
			}
		});

		GridData gridData = new GridData();
		gridData.widthHint = 226;
		gridData.horizontalSpan = 2;
		gridData.verticalSpan = SWT.DEFAULT;
		authenticationTypeCombo.setLayoutData(gridData);

		targetProcessLoginEditor = createStringFieldEditor(Messages.TargetProcessRepositorySettingsPage_Login_, 240,
				true, Messages.TargetProcessRepositorySettingsPage_EmptyLogin_);
		targetProcessPasswordEditor = createStringFieldEditor(Messages.TargetProcessRepositorySettingsPage_Password_,
				240, true, Messages.TargetProcessRepositorySettingsPage_EmptyPassword_);

		if (repository != null) {
			originalUrl = repository.getRepositoryUrl().trim();
			// originalUserName = repository.getUserName();

			targetProcessUrlEditor.setStringValue(repository.getRepositoryUrl());
			targetProcessLabelEditor.setStringValue(repository.getRepositoryLabel());
			AuthenticationCredentials credentials = repository.getCredentials(AuthenticationType.REPOSITORY);
			targetProcessLoginEditor.setStringValue(credentials != null ? credentials.getUserName() : "");
			targetProcessPasswordEditor.setStringValue(credentials != null ? credentials.getPassword() : "");
			if (repository.getProperty(TargetProcessCorePlugin.IS_WINDOWS_AUTHENTICATION_KEY) != null) {
				authenticationTypeCombo.select(1);
			} else {
				authenticationTypeCombo.select(0);
			}
		} else {
			authenticationTypeCombo.select(0);
		}
	}

	private TargetProcessFieldEditor createStringFieldEditor(String label, int width, final boolean isRequired,
			String errorMessage) {
		return new TargetProcessFieldEditor(compositeContainer, label, errorMessage, width) {

			@Override
			protected void valueChanged() {
				super.valueChanged();
				if (isRequired) {
					isPageComplete();
				}
				if (getWizard() != null) {
					getWizard().getContainer().updateButtons();
				}
			}
		};
	}

	@Override
	public void applyTo(TaskRepository repository) {
		repository.setProperty(TargetProcessCorePlugin.IS_WINDOWS_AUTHENTICATION_KEY,
				isWindowsAuthenticationSelected() ? "iswind" : null);

		super.applyTo(repository);
	}

	@Override
	public String getConnectorKind() {
		return TargetProcessCorePlugin.CONNECTOR_KIND;
	}

	@Override
	protected Validator getValidator(TaskRepository repository) {
		if (validateFields(true)) {
			return new TargetProcessValidator(repository, this);
		}
		return null;
	}

	@Override
	protected boolean isValidUrl(String url) {
		if (url.startsWith("https://") || url.startsWith("http://")) { //$NON-NLS-1$//$NON-NLS-2$
			try {
				new URI(url, true, "UTF-8"); //$NON-NLS-1$
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean isPageComplete() {
		String errorMessage = null;
		String url = getRepositoryUrl();
		errorMessage = isUniqueUrl(url);
		if (errorMessage == null && !isValidUrl(url)) {
			errorMessage = Messages.TargetProcessRepositorySettingsPage_Enter_a_valid_server_url;
		}
		if (errorMessage == null) {
			errorMessage = credentialsComplete();
		}
		setErrorMessage(errorMessage);
		return errorMessage == null && validateFields(false);
	}

	private String credentialsComplete() {
		return isMissingCredentials() ? Messages.AbstractRepositorySettingsPage_Enter_a_user_id_Message0 : null;
	}

	protected boolean isMissingCredentials() {
		return StringUtils.isBlank(targetProcessLoginEditor.getStringValue())
				|| StringUtils.isBlank(targetProcessPasswordEditor.getStringValue());
	}

	@Override
	protected String isUniqueUrl(String urlString) {
		if (!urlString.equals(originalUrl)) {
			if (repositoryUrls == null) {
				List<TaskRepository> repositories = TasksUi.getRepositoryManager().getAllRepositories();
				repositoryUrls = new HashSet<String>(repositories.size());
				for (TaskRepository repository : repositories) {
					repositoryUrls.add(repository.getRepositoryUrl().trim());
				}
			}

			if (repositoryUrls.contains(urlString.trim())) {
				return org.eclipse.mylyn.internal.tasks.ui.wizards.Messages.AbstractRepositorySettingsPage_Repository_already_exists;
			}
		}
		return null;
	}

	public boolean validateFields(boolean showErrorMessage) {
		boolean validateResult = true;
		validateResult &= validateField(targetProcessUrlEditor, showErrorMessage);
		validateResult &= validateField(targetProcessLoginEditor, showErrorMessage);
		validateResult &= validateField(targetProcessPasswordEditor, showErrorMessage);
		return validateResult;
	}

	private boolean validateField(TargetProcessFieldEditor stringFieldEditor, boolean showErrorMessage) {
		if (StringUtils.isBlank(stringFieldEditor.getStringValue())) {
			if (showErrorMessage) {
				stringFieldEditor.showErrorMessage();
			}
			return false;
		}
		stringFieldEditor.hideErrorMessage();
		return true;
	}

	private boolean isWindowsAuthenticationSelected() {
		return authenticationTypeCombo.getText().equals(
				Messages.TargetProcessRepositorySettingsPage_IntegratedWindowsAuthentication_);
	}

	public class TargetProcessValidator extends Validator {

		final TaskRepository repository;

		public TargetProcessValidator(TaskRepository repository,
				TargetProcessRepositorySettingsPage targetProcessRepositorySettingsPage) {
			this.repository = repository;
		}

		@Override
		public void run(IProgressMonitor monitor) throws CoreException {
			try {
				validate(monitor);
			} catch (Exception e) {
				displayError(repository.getRepositoryUrl(), e);
			}
		}

		private void displayError(final String serverUrl, Throwable e) {
			IStatus status;
			status = new TargetProcessRepositoryStatus(IStatus.ERROR, TargetProcessCorePlugin.ID_PLUGIN,
					RepositoryStatus.ERROR_INTERNAL,
					Messages.TargetProcessRepositorySettingsPage_Server_Connect_to_server_failed);
			setStatus(status);
		}

		public void validate(IProgressMonitor monitor) throws IOException, CoreException, TPAuthenticationException {

			if (monitor == null) {
				monitor = new NullProgressMonitor();
			}
			try {
				monitor.beginTask(Messages.TargetProcessRepositorySettingsPage_Validating_server_settings,
						IProgressMonitor.UNKNOWN);
				TargetProcessClient client = null;

				client = TargetProcessClientFactory.createClient(repository);
				client.validate(monitor);
			} finally {
				monitor.done();
			}
		}
	}

	@Override
	protected void createAdditionalControls(Composite parent) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getUserName() {
		return targetProcessLoginEditor.getStringValue().trim();
	}

	@Override
	public String getRepositoryLabel() {
		return targetProcessLabelEditor.getStringValue().trim();
	}

	@Override
	public String getPassword() {
		return targetProcessPasswordEditor.getStringValue().trim();
	}

	@Override
	public String getRepositoryUrl() {
		return targetProcessUrlEditor.getStringValue().trim();
	}

	@Override
	public Boolean getSavePassword() {
		return true;
	}
}
