package org.eclipse.mylyn.targetprocess.core;

import java.rmi.RemoteException;

import org.eclipse.mylyn.targetprocess.modules.IServiceFactory;
import org.eclipse.mylyn.targetprocess.modules.TargetProcessCredentials;
import org.eclipse.mylyn.tasks.core.data.TaskData;

public class EntityUpdater {
	
	public EntityUpdater(IServiceFactory serviceFactory, TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		serviceFactory.getRequestServiceStub(targetProcessCredentials);
		serviceFactory.getBugServiceStub(targetProcessCredentials);
		serviceFactory.getUserStoryServiceStub(targetProcessCredentials);
		serviceFactory.getTaskServiceStub(targetProcessCredentials);
	}

	public void update(TaskData taskData) {
		
		
	}

}
