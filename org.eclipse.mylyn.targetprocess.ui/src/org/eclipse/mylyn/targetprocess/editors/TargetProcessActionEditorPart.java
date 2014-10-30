package org.eclipse.mylyn.targetprocess.editors;

import java.text.MessageFormat;

import org.eclipse.mylyn.internal.tasks.ui.editors.EditorUtil;
import org.eclipse.mylyn.internal.tasks.ui.editors.Messages;
import org.eclipse.mylyn.targetprocess.ui.TargetProcessImages;
import org.eclipse.mylyn.tasks.ui.TasksUiImages;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class TargetProcessActionEditorPart extends AbstractTaskEditorPart {

	private Button submitButton;

	public void setSubmitEnabled(boolean enabled) {
		if (submitButton != null && !submitButton.isDisposed()) {
			submitButton.setEnabled(enabled);
			if (enabled) {
				submitButton.setToolTipText(MessageFormat.format(Messages.TaskEditorActionPart_Submit_to_X,
						getTaskEditorPage().getTaskRepository().getRepositoryUrl()));
			}
		}
	}

	private void createActionButtons(Composite buttonComposite, FormToolkit toolkit) {
		submitButton = toolkit.createButton(buttonComposite, Messages.TaskEditorActionPart_Submit, SWT.NONE);
		submitButton.setImage(TargetProcessImages.getImage(TasksUiImages.REPOSITORY_SUBMIT));
		submitButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				getTaskEditorPage().doSubmit();
			}
		});
		Point minSize = submitButton.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
		GridData submitButtonData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		submitButtonData.widthHint = Math.max(100, minSize.x);
		submitButton.setLayoutData(submitButtonData);

		setSubmitEnabled(true);

		toolkit.createLabel(buttonComposite, "    "); //$NON-NLS-1$
	}

	@Override
	public void createControl(Composite parent, FormToolkit toolkit) {
		Composite buttonComposite = toolkit.createComposite(parent);
		GridLayout buttonLayout = EditorUtil.createSectionClientLayout();
		buttonLayout.numColumns = 4;
		buttonLayout.marginLeft = 488;
		buttonComposite.setLayout(buttonLayout);

		createActionButtons(buttonComposite, toolkit);
	}

}
