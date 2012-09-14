package org.eclipse.mylyn.targetprocess.core.entityperformer;

import java.rmi.RemoteException;

import org.eclipse.mylyn.targetprocess.core.TargetProcessAttribute;
import org.eclipse.mylyn.targetprocess.core.TargetProcessEntityKind;
import org.eclipse.mylyn.targetprocess.modules.IServiceFactory;
import org.eclipse.mylyn.targetprocess.modules.TargetProcessCredentials;
import org.eclipse.mylyn.tasks.core.data.TaskData;

public class EntityPerformerFactory {

	public static final String TP_BUSINESS_OBJECTS_REQUEST = "Tp.BusinessObjects.Request";
	public static final String TP_BUSINESS_OBJECTS_BUG = "Tp.BusinessObjects.Bug";
	public static final String TP_BUSINESS_OBJECTS_TASK = "Tp.BusinessObjects.Task";
	public static final String TP_BUSINESS_OBJECTS_USER_STORY = "Tp.BusinessObjects.UserStory";

	private TargetProcessCredentials targetProcessCredentials;

	public EntityPerformerFactory(TargetProcessCredentials targetProcessCredentials) {
		this.targetProcessCredentials = targetProcessCredentials;
	}

	public IPerformer createPerformer(IServiceFactory serviceFactory, String entityTypeName) throws RemoteException {
		if (entityTypeName.equals(TargetProcessEntityKind.BUG.getName())) {
			return new BugPerformer(serviceFactory, targetProcessCredentials);
		} else if (entityTypeName.equals(TargetProcessEntityKind.TASK.getName())) {
			return new TaskPerformer(serviceFactory, targetProcessCredentials);
		} else if (entityTypeName.equals(TargetProcessEntityKind.USERSTORY.getName())) {
			return new UserStoryPerformer(serviceFactory, targetProcessCredentials);
		} else if (entityTypeName.equals(TargetProcessEntityKind.REQUEST.getName())) {
			return new RequestPerformer(serviceFactory, targetProcessCredentials);
		}

		return new NullPerformer();
	}

	public IPerformer createPerformer(IServiceFactory serviceFactory, TaskData taskData) throws RemoteException {

		String entityTypeName = taskData.getRoot().getAttribute(TargetProcessAttribute.EntityKind.getKey()).getValue();
		return createPerformer(serviceFactory, entityTypeName);
	}
}
