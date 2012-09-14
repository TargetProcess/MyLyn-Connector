package org.eclipse.mylyn.targetprocess.core.tests.mockservices;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.eclipse.mylyn.targetprocess.core.tests.TestContext;
import org.eclipse.mylyn.targetprocess.modules.services.PriorityServiceStub.ArrayOfPriorityDTO;
import org.eclipse.mylyn.targetprocess.modules.services.PriorityServiceStub.PriorityDTO;
import org.eclipse.mylyn.targetprocess.modules.services.PriorityServiceStub.RetrieveAll;
import org.eclipse.mylyn.targetprocess.modules.services.PriorityServiceStub.RetrieveAllResponse;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IPriorityServiceStub;

public class PriorityServiceStubMock extends ServiceStubMockBase<IPriorityServiceStub> implements IPriorityServiceStub {

	public PriorityServiceStubMock(TestContext context) {
		super(context);
	}

	@Override
	public RetrieveAllResponse retrieveAll(RetrieveAll retrieveAll) throws RemoteException {

		if (mock != null) {
			mock.retrieveAll(retrieveAll);
		}

		RetrieveAllResponse response = new RetrieveAllResponse();
		ArrayOfPriorityDTO arrayOfPriority = new ArrayOfPriorityDTO();

		ArrayList<PriorityDTO> priorityList = context.getPriorityList();

		for (PriorityDTO priority : priorityList) {
			arrayOfPriority.addPriorityDTO(priority);
		}

		response.setRetrieveAllResult(arrayOfPriority);

		return response;
	}

}
