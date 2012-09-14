package org.eclipse.mylyn.targetprocess.ui.taskslist;

import org.eclipse.mylyn.internal.tasks.ui.editors.EditorUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class TargetProcessFieldEditor {

	private Text textField;
	private Label label;
	private Label errorMessageLabel;

	public TargetProcessFieldEditor(Composite parentComposite, String labelText, String errorMessage, int width) {
		Composite composite = new Composite(parentComposite, SWT.NONE);

		GridLayout layout = EditorUtil.createSectionClientLayout();
		layout.numColumns = 2;
		layout.horizontalSpacing = 20;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.verticalSpacing = 0;
		layout.marginTop = 0;
		layout.marginBottom = 0;
		layout.marginLeft = 0;
		composite.setLayout(layout);

		label = new Label(composite, SWT.NONE);

		errorMessageLabel = new Label(composite, SWT.NONE);
		errorMessageLabel.setForeground(new Color(parentComposite.getDisplay(), 255, 0, 0));
		textField = new Text(parentComposite, SWT.BORDER);
		textField.setTextLimit(255);

		label.setText(labelText);
		errorMessageLabel.setText(errorMessage);

		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				valueChanged();
			}
		});

		GridData gridData = new GridData();
		gridData.widthHint = width;
		gridData.horizontalSpan = 2;
		gridData.verticalSpan = SWT.DEFAULT;
		textField.setLayoutData(gridData);

		errorMessageLabel.setVisible(false);
	}

	public String getStringValue() {
		return textField.getText();
	}

	public void setStringValue(String value) {
		textField.setText(value);
	}

	protected void valueChanged() {
	}

	public void showErrorMessage() {
		errorMessageLabel.setVisible(true);

	}

	public void hideErrorMessage() {
		errorMessageLabel.setVisible(false);
	}
}
