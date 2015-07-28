package org.eclipse.mylyn.targetprocess.ui.taskslist;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.TasksUiImages;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositoryQueryPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class TargetProcessQueryWizardPage extends AbstractRepositoryQueryPage {

	private static final String TITLE = Messages.TargetProcessQueryTypeWizardPage_Choose_query_type;

	private static final String DESCRIPTION = Messages.TargetProcessQueryTypeWizardPage_Select_from_the_available_query_types;

	private Button myToDOListButton;

	private Label queryDescription;

	private StringFieldEditor queryTitleEditor;

	// private TaskRepository repository;

	public TargetProcessQueryWizardPage(TaskRepository repository) {
		super(TITLE, repository);
		setTitle(TITLE);
		setDescription(DESCRIPTION);
		setImageDescriptor(TasksUiImages.BANNER_REPOSITORY);
		// this.repository = repository;
	}

	@Override
	public boolean isPageComplete() {
		return true;
	}

	@Override
	public IWizardPage getNextPage() {
		return null;
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessVerticalSpace = false;

		Composite descriptionComposite = new Composite(composite, SWT.NONE);

		descriptionComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout descriptionLayout = new GridLayout();
		descriptionLayout.marginWidth = 0;
		descriptionLayout.marginHeight = 0;
		descriptionLayout.horizontalSpacing = 2;
		descriptionLayout.numColumns = 2;
		descriptionComposite.setLayout(descriptionLayout);

		queryDescription = new Label(descriptionComposite, SWT.NONE);
		queryDescription.setText(Messages.TargetProcessQueryWizard_Description_);

		Composite queryTitleComposite = new Composite(composite, SWT.NONE);
		GridData queryTitleGridData = new GridData(SWT.FILL, SWT.NONE, false, false, 1, 1);
		queryTitleGridData.minimumWidth = 4000;
		queryTitleComposite.setLayoutData(queryTitleGridData);
		GridLayout queryTitleLayout = new GridLayout();
		queryTitleLayout.marginWidth = 0;
		queryTitleLayout.marginHeight = 0;
		queryTitleLayout.horizontalSpacing = 2;
		queryTitleLayout.numColumns = 1;
		queryTitleComposite.setLayout(queryTitleLayout);

		composite.setLayoutData(gridData);
		composite.setLayout(new GridLayout(1, false));

		queryTitleEditor = new StringFieldEditor("", Messages.TargetProcessRepositorySettingsPage_QueryTitle_,
				StringFieldEditor.UNLIMITED, queryTitleComposite) {

			@Override
			public int getNumberOfControls() {
				return 2;
			}
		};
		queryTitleEditor.setTextLimit(255);

		myToDOListButton = new Button(composite, SWT.RADIO);
		myToDOListButton.setText("My TODO List");
		myToDOListButton.setSelection(true);
		myToDOListButton.setVisible(false);

		setPageComplete(true);
		setControl(composite);
		Dialog.applyDialogFont(composite);
	}

	@Override
	public void applyTo(IRepositoryQuery query) {
		if (StringUtils.isNotBlank(getQueryTitle())) {
			query.setSummary(getQueryTitle());
		}
	}

	@Override
	public String getQueryTitle() {
		return queryTitleEditor.getStringValue().trim();
	}
}
