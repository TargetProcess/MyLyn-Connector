package org.eclipse.mylyn.targetprocess.editors;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.mylyn.commons.core.StatusHandler;
import org.eclipse.mylyn.internal.tasks.ui.TasksUiPlugin;
import org.eclipse.mylyn.internal.tasks.ui.editors.EditorUtil;
import org.eclipse.mylyn.targetprocess.core.TargetProcessAttribute;
import org.eclipse.mylyn.targetprocess.ui.TargetProcessImages;
import org.eclipse.mylyn.targetprocess.ui.editors.DescriptionEditor;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.ui.editors.AbstractAttributeEditor;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorPage;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.internal.forms.widgets.FormUtil;

@SuppressWarnings("restriction")
public class DescriptionPart extends AbstractTaskEditorPart {

	private TaskAttribute attribute;

	private int sectionStyle;

	private Composite composite;

	private DescriptionEditor editor;

	private Object toggleToMaximizePartAction;

	// private FormToolkit toolkit;

	public DescriptionPart() {
		setPartName(Messages.TaskEditorDescriptionPart_Description_);
	}

	@Override
	public void createControl(Composite parent, FormToolkit toolkit) {
		if (getAttribute() == null) {
			return;
		}

		if (attribute == null) {
			return;
		}

		// this.toolkit = toolkit;
		AbstractAttributeEditor attributEditor = createAttributeEditor(attribute);
		if (!(attributEditor instanceof DescriptionEditor)) {
			String clazz;
			if (attributEditor != null) {
				clazz = attributEditor.getClass().getName();
			} else {
				clazz = "<null>"; //$NON-NLS-1$
			}
			StatusHandler.log(new Status(IStatus.WARNING, TasksUiPlugin.ID_PLUGIN,
					"Expected an instance of RichTextAttributeEditor, got \"" + clazz + "\"")); //$NON-NLS-1$ //$NON-NLS-2$
			return;
		}

		Section section = createSection(parent, toolkit, getSectionStyle());

		composite = toolkit.createComposite(section);
		composite.setLayout(EditorUtil.createSectionClientLayout());

		editor = (DescriptionEditor) attributEditor;

		editor.createControl(composite, toolkit);

		editor.getControl().setLayoutData(
				EditorUtil.getTextControlLayoutData(getTaskEditorPage(), editor.getControl(), getExpandVertically()));

		editor.getControl().setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);

		toolkit.paintBordersFor(composite);
		section.setClient(composite);
		setSection(toolkit, section);
	}

	@Override
	public void initialize(AbstractTaskEditorPage taskEditorPage) {
		super.initialize(taskEditorPage);
		setAttribute(getModel().getTaskData().getRoot().getMappedAttribute(TargetProcessAttribute.Description.getKey()));
	}

	@Override
	protected void fillToolBar(ToolBarManager barManager) {

		final Action editAction = new Action("") { //$NON-NLS-1$
			@Override
			public void run() {

				if (editor.isInEditMode()) {

					editor.showPreview();
					setChecked(false);
				} else {

					setChecked(true);
					editor.showEditor();
				}
			}
		};

		editAction.setImageDescriptor(TargetProcessImages.EDIT);
		editAction.setToolTipText(Messages.TaskEditorDescriptionPart_Edit_);
		editAction.setChecked(true);
		editAction.setChecked(false);
		barManager.add(editAction);
	}

	private class ToggleToMaximizePartAction extends Action {

		private static final String COMMAND_ID = "org.eclipse.mylyn.tasks.ui.command.maximizePart"; //$NON-NLS-1$

		private/* static */final String MAXIMIZE = Messages.TaskEditorRichTextPart_Maximize;

		private static final int SECTION_HEADER_HEIGHT = 50;

		private int originalHeight = -1;

		public ToggleToMaximizePartAction() {
			super("", SWT.TOGGLE); //$NON-NLS-1$
			setImageDescriptor(TargetProcessImages.PART_MAXIMIZE);
			setToolTipText(MAXIMIZE);
			setActionDefinitionId(COMMAND_ID);
			setChecked(false);
		}

		@Override
		public void run() {
			if (!(getEditor().getControl().getLayoutData() instanceof GridData)) {
				return;
			}

			GridData gd = (GridData) getEditor().getControl().getLayoutData();

			if (originalHeight == -1) {
				originalHeight = gd.heightHint;
			}

			try {
				getTaskEditorPage().setReflow(false);

				int heightHint;
				if (isChecked()) {
					heightHint = getManagedForm().getForm().getClientArea().height - SECTION_HEADER_HEIGHT;
				} else {
					heightHint = originalHeight;
				}

				// ignore when not necessary
				if (gd.heightHint == heightHint) {
					return;
				}
				gd.heightHint = heightHint;
				gd.minimumHeight = heightHint;
			} finally {
				getTaskEditorPage().setReflow(true);
			}

			getTaskEditorPage().reflow();
			ensureVisible(getEditor().getControl());
		}
	}

	protected Action getMaximizePartAction() {
		if (toggleToMaximizePartAction == null) {
			toggleToMaximizePartAction = new ToggleToMaximizePartAction();
		}
		return (Action) toggleToMaximizePartAction;
	}

	protected DescriptionEditor getEditor() {
		return editor;
	}

	protected Composite getComposite() {
		return composite;
	}

	public void setAttribute(TaskAttribute attribute) {
		this.attribute = attribute;
	}

	public TaskAttribute getAttribute() {
		return attribute;
	}

	public void setSectionStyle(int sectionStyle) {
		this.sectionStyle = sectionStyle;
	}

	public int getSectionStyle() {
		return sectionStyle;
	}

	private static void ensureVisible(Control control) {
		ScrolledComposite form = FormUtil.getScrolledComposite(control);
		if (form != null) {
			FormUtil.ensureVisible(form, control);
		}
	}

}
