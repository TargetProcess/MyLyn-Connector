package org.eclipse.mylyn.targetprocess.core.tests;

import org.apache.axis2.AxisFault;
import org.eclipse.mylyn.targetprocess.core.tests.mockservices.AssignableUserServiceStubMock;
import org.eclipse.mylyn.targetprocess.core.tests.mockservices.BugServiceStubMock;
import org.eclipse.mylyn.targetprocess.core.tests.mockservices.CommentServiceStubMock;
import org.eclipse.mylyn.targetprocess.core.tests.mockservices.EntityStateServiceStubMock;
import org.eclipse.mylyn.targetprocess.core.tests.mockservices.FileServiceStubMock;
import org.eclipse.mylyn.targetprocess.core.tests.mockservices.GeneralServiceStubMock;
import org.eclipse.mylyn.targetprocess.core.tests.mockservices.GeneralUserServiceStubMock;
import org.eclipse.mylyn.targetprocess.core.tests.mockservices.MyAssignmentsServiceStubMock;
import org.eclipse.mylyn.targetprocess.core.tests.mockservices.PriorityServiceStubMock;
import org.eclipse.mylyn.targetprocess.core.tests.mockservices.RequestServiceStubMock;
import org.eclipse.mylyn.targetprocess.core.tests.mockservices.TaskServiceStubMock;
import org.eclipse.mylyn.targetprocess.core.tests.mockservices.UserStoryServiceStubMock;
import org.eclipse.mylyn.targetprocess.modules.IServiceFactory;
import org.eclipse.mylyn.targetprocess.modules.TargetProcessCredentials;
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

public class MockServiceFactory implements IServiceFactory {

	private TestContext context;
	private BugServiceStubMock bugServiceStubMock;
	private GeneralUserServiceStubMock generalUserServiceStubMock;	
	private AssignableUserServiceStubMock assignableUserServiceStubMock;
	private MyAssignmentsServiceStubMock myAssignmentsServiceStubMock;
	private UserStoryServiceStubMock userStoryServiceStubMock;
	public TaskServiceStubMock taskServiceStubMock;
	private PriorityServiceStubMock priorityServiceStubMock;
	private RequestServiceStubMock requestUserServiceStubMock;
	private EntityStateServiceStubMock entityStateServiceStubMock;
	private GeneralServiceStubMock generalServiceStubMock;
	private FileServiceStubMock fileServiceStubMock;
	private CommentServiceStubMock commentServiceStubMock;

	public MockServiceFactory() {
		context = new TestContext();
		bugServiceStubMock = new BugServiceStubMock(context);
		generalUserServiceStubMock = new GeneralUserServiceStubMock(context);
		assignableUserServiceStubMock = new AssignableUserServiceStubMock(context);
		myAssignmentsServiceStubMock = new MyAssignmentsServiceStubMock(context);
		userStoryServiceStubMock = new UserStoryServiceStubMock(context);
		taskServiceStubMock = new TaskServiceStubMock(context);
		priorityServiceStubMock = new PriorityServiceStubMock(context);
		requestUserServiceStubMock = new RequestServiceStubMock(context);
		entityStateServiceStubMock = new EntityStateServiceStubMock(context);
		generalServiceStubMock = new GeneralServiceStubMock(context);
		fileServiceStubMock = new FileServiceStubMock(context);		
		commentServiceStubMock = new CommentServiceStubMock (context);
	}

	public void setMock(IBugServiceStub mock) {
		bugServiceStubMock.setMock(mock);
	}

	public void setMock(IUserStoryServiceStub mock) {
		userStoryServiceStubMock.setMock(mock);
	}

	public void setMock(ITaskServiceStub mock) {
		taskServiceStubMock.setMock(mock);
	}

	public TestContext getContext() {
		return context;
	}

	public IBugServiceStub getBugServiceStub() {
		return bugServiceStubMock;
	}

	public IGeneralUserServiceStub getGeneralUserServiceStub(TargetProcessCredentials targetProcessCredentials) {
		return getGeneralUserServiceStub();
	}

	public IAssignableServiceStub getAssignableServiceStub(TargetProcessCredentials targetProcessCredentials) {
		return getAssignableServiceStub();
	}

	public IGeneralUserServiceStub getGeneralUserServiceStub() {
		return generalUserServiceStubMock;
	}

	public IAssignableServiceStub getAssignableServiceStub() {
		return assignableUserServiceStubMock;
	}

	public void setMock(IGeneralUserServiceStub mock) {
		generalUserServiceStubMock.setMock(mock);
	}

	public void setMock(ICommentServiceStub mock) {
		commentServiceStubMock.setMock(mock);
	}
	public void setMock(IAssignableServiceStub mock) {
		assignableUserServiceStubMock.setMock(mock);
	}

	public void setMock(IPriorityServiceStub mock) {
		priorityServiceStubMock.setMock(mock);
	}

	@Override
	public AuthenticationServiceStub getAuthentificationServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws AxisFault {
		return null;
	}

	@Override
	public IBugServiceStub getBugServiceStub(TargetProcessCredentials targetProcessCredentials) throws AxisFault {
		return getBugServiceStub();
	}

	@Override
	public IMyAssignmentsServiceStub getMyAssignmentServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws AxisFault {
		return getMyAssignmentServiceStub();
	}

	private IMyAssignmentsServiceStub getMyAssignmentServiceStub() {
		return myAssignmentsServiceStubMock;
	}

	public void setMock(IMyAssignmentsServiceStub mock) {
		myAssignmentsServiceStubMock.setMock(mock);
	}

	@Override
	public ProjectServiceStub getProjectServiceStub(TargetProcessCredentials targetProcessCredentials) throws AxisFault {
		return null;
	}

	public IUserStoryServiceStub getUserStoryServiceStub() {
		return userStoryServiceStubMock;
	}

	@Override
	public IUserStoryServiceStub getUserStoryServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws AxisFault {
		return getUserStoryServiceStub();
	}

	@Override
	public ITaskServiceStub getTaskServiceStub(TargetProcessCredentials targetProcessCredentials) throws AxisFault {
		return getTaskServiceStub();
	}

	public ITaskServiceStub getTaskServiceStub() {
		return taskServiceStubMock;
	}

	public IPriorityServiceStub getPriorityServiceStub() throws AxisFault {
		return priorityServiceStubMock;
	}

	@Override
	public IPriorityServiceStub getPriorityServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws AxisFault {
		return getPriorityServiceStub();
	}

	public void setMock(IRequestServiceStub mock) {
		requestUserServiceStubMock.setMock(mock);
	}

	@Override
	public IRequestServiceStub getRequestServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws AxisFault {
		return getRequestServiceStub();
	}

	public IRequestServiceStub getRequestServiceStub() {
		return requestUserServiceStubMock;
	}

	public void setMock(IEntityStateServiceStub mock) {
		entityStateServiceStubMock.setMock(mock);
	}

	public void setMock(IFileServiceStub mock) {
		fileServiceStubMock.setMock(mock);
	}

	@Override
	public IEntityStateServiceStub getEntityStateServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws AxisFault {
		return getEntityStateServiceStub();
	}

	public IEntityStateServiceStub getEntityStateServiceStub() {
		return entityStateServiceStubMock;
	}

	public IGeneralServiceStub getGeneralServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws AxisFault {
		return geGeneralServiceStub();
	}

	public IGeneralServiceStub geGeneralServiceStub() {
		return generalServiceStubMock;
	}

	@Override
	public IFileServiceStub getFileServiceStub(TargetProcessCredentials targetProcessCredentials) throws AxisFault {
		return fileServiceStubMock;
	}

	@Override
	public ICommentServiceStub getCommentServiceStub(TargetProcessCredentials targetProcessCredentials) {
		return commentServiceStubMock;
	}	

}
