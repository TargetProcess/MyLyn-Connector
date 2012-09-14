package org.eclipse.mylyn.targetprocess.core.tests.mockservices;

import java.rmi.RemoteException;

import org.eclipse.mylyn.targetprocess.core.tests.TestContext;
import org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.ArrayOfTeamDTO;
import org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrieveTeamsForAssignable;
import org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrieveTeamsForAssignableResponse;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IAssignableServiceStub;

public class AssignableUserServiceStubMock extends ServiceStubMockBase<IAssignableServiceStub> implements IAssignableServiceStub {

	public AssignableUserServiceStubMock(TestContext context) {
		super(context);
	}

	@Override
	public RetrieveTeamsForAssignableResponse retrieveTeamsForAssignable(
			RetrieveTeamsForAssignable retrieveTeamsForAssignable)
			throws RemoteException {
		if (mock != null){
			mock.retrieveTeamsForAssignable(retrieveTeamsForAssignable);
		}
		
		ArrayOfTeamDTO teams = this.context.RetrieveTeamsForAssignableResponse(retrieveTeamsForAssignable.getAssignableID());
		
		RetrieveTeamsForAssignableResponse response = new RetrieveTeamsForAssignableResponse();
		response.setRetrieveTeamsForAssignableResult(teams);

		return response;
	}	

}
