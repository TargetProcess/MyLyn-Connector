package org.eclipse.mylyn.targetprocess.editors;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.mylyn.internal.tasks.ui.editors.TaskEditorAttachmentPart;
import org.eclipse.mylyn.internal.tasks.ui.editors.TaskEditorCommentPart;
import org.eclipse.mylyn.internal.tasks.ui.editors.TaskEditorDescriptionPart;
import org.eclipse.mylyn.internal.tasks.ui.editors.TaskEditorNewCommentPart;
import org.eclipse.mylyn.targetprocess.core.TargetProcessCorePlugin;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorPage;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorPart;
import org.eclipse.mylyn.tasks.ui.editors.AttributeEditorFactory;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditor;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditorPartDescriptor;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

@SuppressWarnings("restriction")
public class TargetProcessTaskEditorPage extends AbstractTaskEditorPage {

	public TargetProcessTaskEditorPage(TaskEditor editor) {
		this(editor, TargetProcessCorePlugin.CONNECTOR_KIND);
	}

	public TargetProcessTaskEditorPage(TaskEditor editor, String connectorKind) {
		super(editor, connectorKind);

		setNeedsPrivateSection(true);
		setNeedsSubmitButton(true);
	}

	@Override
	protected Set<TaskEditorPartDescriptor> createPartDescriptors() {

		Set<TaskEditorPartDescriptor> descriptors = new LinkedHashSet<TaskEditorPartDescriptor>();
		descriptors.add(new TaskEditorPartDescriptor(ID_PART_SUMMARY) {
			@Override
			public AbstractTaskEditorPart createPart() {
				return new TargetProcessSummaryEditorPart();
			}
		}.setPath(PATH_HEADER));

		
		descriptors.add(new TaskEditorPartDescriptor(ID_PART_PLANNING) {
			@Override
			public AbstractTaskEditorPart createPart() {
				DescriptionPart part = new DescriptionPart();
					part.setExpandVertically(true);
					part.setSectionStyle(ExpandableComposite.TITLE_BAR | ExpandableComposite.EXPANDED);
				
				return part;
			}
		}.setPath(PATH_PLANNING));
		
		descriptors.add(new TaskEditorPartDescriptor(ID_PART_COMMENTS) {			
			@Override
			public AbstractTaskEditorPart createPart() {
				return new TaskEditorCommentPart();
			}
		}.setPath(PATH_COMMENTS));
		
		descriptors.add(new TaskEditorPartDescriptor(ID_PART_NEW_COMMENT) {
			@Override
			public AbstractTaskEditorPart createPart() {
				TaskEditorNewCommentPart part = new TaskEditorNewCommentPart();
				return part;
			}
		}.setPath(PATH_COMMENTS));
		


		descriptors.add(new TaskEditorPartDescriptor(ID_PART_ATTACHMENTS) {
			@Override
			public AbstractTaskEditorPart createPart() {
				return new TaskEditorAttachmentPart();
			}
		}.setPath(PATH_ATTACHMENTS));
		
		
		

		descriptors.add(new TaskEditorPartDescriptor(ID_PART_ACTIONS) {
			@Override
			public AbstractTaskEditorPart createPart() {
				return new TargetProcessActionEditorPart();
			}
		}.setPath(PATH_ACTIONS));

		return descriptors;
	}

	protected AttributeEditorFactory createAttributeEditorFactory() {
		return new TargetProcessAttributeEditorFactory(getModel(), getTaskRepository(), getEditorSite());
	}
	
	@Override
	public void doSubmit() {
		super.doSubmit();
	}

	/*
	 * @Override protected AttributeEditorFactory createAttributeEditorFactory()
	 * { AttributeEditorFactory factory = new AttributeEditorFactory(getModel(),
	 * getTaskRepository(), getEditorSite()) {
	 * 
	 * @Override public AbstractAttributeEditor createEditor(String type, final
	 * TaskAttribute taskAttribute) { AbstractAttributeEditor editor; if
	 * (IBugzillaConstants.EDITOR_TYPE_KEYWORDS.equals(type)) { editor = new
	 * BugzillaKeywordAttributeEditor(getModel(), taskAttribute); } else if
	 * (IBugzillaConstants.EDITOR_TYPE_REMOVECC.equals(type)) { editor = new
	 * BugzillaCcAttributeEditor(getModel(), taskAttribute); } else if
	 * (IBugzillaConstants.EDITOR_TYPE_VOTES.equals(type)) { editor = new
	 * BugzillaVotesEditor(getModel(), taskAttribute); } else if
	 * (IBugzillaConstants.EDITOR_TYPE_FLAG.equals(type)) { editor = new
	 * FlagAttributeEditor(getModel(), taskAttribute); } else { editor =
	 * super.createEditor(type, taskAttribute); if
	 * (TaskAttribute.TYPE_BOOLEAN.equals(type)) {
	 * editor.setDecorationEnabled(false); } }
	 * 
	 * if (editor != null &&
	 * taskAttribute.getId().startsWith(BugzillaCustomField
	 * .CUSTOM_FIELD_PREFIX)) { editor.setLayoutHint(new
	 * LayoutHint(editor.getLayoutHint()) {
	 * 
	 * @Override public int getPriority() { return super.getPriority() * 10; }
	 * }); }
	 * 
	 * TaskAttributeMetaData properties = taskAttribute.getMetaData(); if
	 * (editor != null &&
	 * IBugzillaConstants.EDITOR_TYPE_FLAG.equals(properties.getType())) {
	 * editor.setLayoutHint(new LayoutHint(editor.getLayoutHint()) {
	 * 
	 * @Override public int getPriority() { return super.getPriority() * 5; }
	 * }); } BugzillaTaskEditorPage.this.addToAttributeEditorMap(taskAttribute,
	 * editor); return editor; } }; return factory; }
	 */

}
