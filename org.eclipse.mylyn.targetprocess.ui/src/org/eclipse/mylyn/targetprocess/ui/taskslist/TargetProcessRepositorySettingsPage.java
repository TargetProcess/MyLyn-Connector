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
import org.eclipse.mylyn.commons.net.AbstractWebLocation;
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
import org.eclipse.mylyn.tasks.core.TaskRepositoryLocationFactory;
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

	protected static TaskRepositoryLocationFactory taskRepositoryLocationFactory = new TaskRepositoryLocationFactory();
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
		setNeedsAdvanced(false);
	}



	@Override
	public String getConnectorKind() {
		return TargetProcessCorePlugin.CONNECTOR_KIND;
	}

	@Override
	protected Validator getValidator(TaskRepository repository) {
		return new TargetProcessValidator(repository, this);
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

				AbstractWebLocation location = taskRepositoryLocationFactory.createWebLocation(repository);
				client = TargetProcessClientFactory.createClient(location, repository);
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
}
