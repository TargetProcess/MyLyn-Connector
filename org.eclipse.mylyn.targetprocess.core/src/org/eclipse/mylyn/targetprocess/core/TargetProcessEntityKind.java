package org.eclipse.mylyn.targetprocess.core;

import org.eclipse.mylyn.targetprocess.core.entityperformer.EntityPerformerFactory;

public enum TargetProcessEntityKind {
	BUG(EntityPerformerFactory.TP_BUSINESS_OBJECTS_BUG, "Bug"), USERSTORY(
			EntityPerformerFactory.TP_BUSINESS_OBJECTS_USER_STORY, "User Story"), TASK(
			EntityPerformerFactory.TP_BUSINESS_OBJECTS_TASK, "Task"), REQUEST(
			EntityPerformerFactory.TP_BUSINESS_OBJECTS_REQUEST, "Request"), UNDEFINED("", "");

	private String name;
	private String readableName;

	TargetProcessEntityKind(String name, String readableName) {
		setName(name);
		setReadableName(readableName);
	}

	@Override
	public String toString() {
		return getName();
	}

	protected void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	protected void setReadableName(String readableName) {
		this.readableName = readableName;
	}

	public String getReadableName() {
		return readableName;
	}

	public static TargetProcessEntityKind parseFromString(String name) {
		for (TargetProcessEntityKind entityKind : TargetProcessEntityKind.values()) {
			if (entityKind.getName().equals(name)) {
				return entityKind;
			}
		}
		return UNDEFINED;
	}

}