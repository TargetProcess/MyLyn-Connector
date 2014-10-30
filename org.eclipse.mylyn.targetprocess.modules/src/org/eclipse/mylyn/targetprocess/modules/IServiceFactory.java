package org.eclipse.mylyn.targetprocess.modules;

import java.rmi.RemoteException;

import org.eclipse.mylyn.targetprocess.modules.services.AuthenticationServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IAssignableServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IBugServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.ICommentServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IEntityStateServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IFileServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IGeneralServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IGeneralUserServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IMyAssignmentsServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IPriorityServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IRequestServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.ITaskServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IUserStoryServiceStub;

public interface IServiceFactory {

	public AuthenticationServiceStub getAuthentificationServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException;

	public IMyAssignmentsServiceStub getMyAssignmentServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException;

	public IBugServiceStub getBugServiceStub(TargetProcessCredentials targetProcessCredentials) throws RemoteException;

	public IGeneralUserServiceStub getGeneralUserServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException;

	public IAssignableServiceStub getAssignableServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException;

	public ProjectServiceStub getProjectServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException;

	public IUserStoryServiceStub getUserStoryServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException;

	public ITaskServiceStub getTaskServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException;

	public IEntityStateServiceStub getEntityStateServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException;

	public IPriorityServiceStub getPriorityServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException;

	public IRequestServiceStub getRequestServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException;

	public IGeneralServiceStub getGeneralServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException;

	public IFileServiceStub getFileServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException;

	public ICommentServiceStub getCommentServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException;

}
