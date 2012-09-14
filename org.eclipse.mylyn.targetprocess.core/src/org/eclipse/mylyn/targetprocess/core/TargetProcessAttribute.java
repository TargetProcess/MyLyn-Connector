package org.eclipse.mylyn.targetprocess.core;

import org.eclipse.mylyn.tasks.core.data.TaskAttribute;

public enum TargetProcessAttribute {

	ID(Messages.TargetProcessAttribute_used_by_search_engine_id, "id", TaskAttribute.TYPE_SHORT_TEXT, true, false), //$NON-NLS-1$

	EntityKind(Messages.TargetProcessAttribute_used_by_search_engine_entityKind, TaskAttribute.TASK_KIND,
			TaskAttribute.TYPE_SHORT_TEXT, true, false), Name(
			Messages.TargetProcessAttribute_used_by_search_engine_name,
			"name", TaskAttribute.TYPE_SHORT_TEXT, false, false), //$NON-NLS-1$
	Description(Messages.TargetProcessAttribute_used_by_search_engine_desc,
			"description", "html_text", false, false), //$NON-NLS-1$
	State(Messages.TargetProcessAttribute_used_by_search_engine_state, TaskAttribute.STATUS,
			TaskAttribute.TYPE_SINGLE_SELECT, false, false), ProjectName(
			Messages.TargetProcessAttribute_used_by_search_engine_project,
			"projectName", TaskAttribute.TYPE_SHORT_TEXT, false, true), //$NON-NLS-1$
	Owner(Messages.TargetProcessAttribute_used_by_search_engine_owner,
			"owner", TaskAttribute.TYPE_SHORT_TEXT, false, true), //$NON-NLS-1$
	UserStory(Messages.TargetProcessAttribute_used_by_search_engine_userStory,
			"userStory", TaskAttribute.TYPE_SHORT_TEXT, false, true), //$NON-NLS-1$
	AssignedPeople(Messages.TargetProcessAttribute_used_by_search_engine_assignedPeople,
			"assignedPeople", TaskAttribute.TYPE_SHORT_TEXT, false, true), //$NON-NLS-1$
	AssignedPeople2(Messages.EmptyMessage, "assignedPeople2", TaskAttribute.TYPE_SHORT_TEXT, false, true), //$NON-NLS-1$
	Severity(Messages.TargetProcessAttribute_used_by_search_engine_severity,
			"severity", TaskAttribute.TYPE_SHORT_TEXT, false, true), //$NON-NLS-1$
	TimeSpent(Messages.TargetProcessAttribute_used_by_search_engine_timeSpent,
			"timeSpent", TaskAttribute.TYPE_SHORT_TEXT, false, true), //$NON-NLS-1$
	NewComment(Messages.TargetProcessAttribute_new_comment, TaskAttribute.COMMENT_NEW,
			TaskAttribute.TYPE_LONG_RICH_TEXT, true, false), TimeRemaining(
			Messages.TargetProcessAttribute_used_by_search_engine_timeRemaining,
			"timeRemaining", TaskAttribute.TYPE_SHORT_TEXT, false, true), //$NON-NLS-1$
	Priority(Messages.TargetProcessAttribute_used_by_search_engine_numericPriority, TaskAttribute.PRIORITY,
			TaskAttribute.TYPE_SHORT_TEXT, true, true), CreationDate(
			Messages.TargetProcessAttribute_used_by_search_engine_creationDate, TaskAttribute.DATE_CREATION,
			TaskAttribute.TYPE_SHORT_TEXT, true, false), DateModification("", TaskAttribute.DATE_MODIFICATION,
			TaskAttribute.DATE_MODIFICATION, true, true), EndDate("", TaskAttribute.DATE_COMPLETION,
			TaskAttribute.DATE_COMPLETION, true, true), AttachId(Messages.TargetProcessAttribute_ATTACH_ID,
			"attachid", TaskAttribute.TYPE_SHORT_TEXT, false, false), //$NON-NLS-1$
	Attachment(Messages.TargetProcessAttribute_attachment, "attachment", TaskAttribute.TYPE_ATTACHMENT, false, false), TOKEN(
			"token", "token", null, true, true), TaskKey(Messages.TargetProcessAttribute_Task_Key,
			TaskAttribute.TASK_KEY, TaskAttribute.TASK_KEY, true, false);

	private final boolean isHidden;

	private final boolean isReadOnly;

	private final String keyString;

	private final String prettyName;

	private final String type;

	TargetProcessAttribute(String prettyName, String idKey, String type, boolean hidden, boolean readonly) {
		this.prettyName = prettyName;
		keyString = idKey;
		this.type = type;
		isHidden = hidden;
		isReadOnly = readonly;
	}

	public String getKey() {
		return keyString;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public boolean isReadOnly() {
		return isReadOnly;
	}

	@Override
	public String toString() {
		return prettyName;
	}

	public String getKind() {
		return isHidden() ? null : TaskAttribute.KIND_DEFAULT;
	}

	public String getType() {
		return type;
	}

}
