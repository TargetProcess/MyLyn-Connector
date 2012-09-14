package org.eclipse.mylyn.targetprocess.modules;

import org.apache.axis2.AxisFault;
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
			throws AxisFault;

	public IMyAssignmentsServiceStub getMyAssignmentServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws AxisFault;

	public IBugServiceStub getBugServiceStub(TargetProcessCredentials targetProcessCredentials) throws AxisFault;

	public IGeneralUserServiceStub getGeneralUserServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws AxisFault;

	public IAssignableServiceStub getAssignableServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws AxisFault;

	public ProjectServiceStub getProjectServiceStub(TargetProcessCredentials targetProcessCredentials) throws AxisFault;

	public IUserStoryServiceStub getUserStoryServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws AxisFault;

	public ITaskServiceStub getTaskServiceStub(TargetProcessCredentials targetProcessCredentials) throws AxisFault;

	public IEntityStateServiceStub getEntityStateServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws AxisFault;

	public IPriorityServiceStub getPriorityServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws AxisFault;

	public IRequestServiceStub getRequestServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws AxisFault;

	public IGeneralServiceStub getGeneralServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws AxisFault;

	public IFileServiceStub getFileServiceStub(TargetProcessCredentials targetProcessCredentials) throws AxisFault;

	public ICommentServiceStub getCommentServiceStub(TargetProcessCredentials targetProcessCredentials) throws AxisFault;

}