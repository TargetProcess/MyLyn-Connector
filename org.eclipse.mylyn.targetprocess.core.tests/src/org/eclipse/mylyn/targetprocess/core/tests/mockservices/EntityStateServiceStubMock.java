package org.eclipse.mylyn.targetprocess.core.tests.mockservices;

import java.rmi.RemoteException;

import org.eclipse.mylyn.targetprocess.core.tests.TestContext;
import org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.ArrayOfEntityStateDTO;
import org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.RetrieveAll;
import org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.RetrieveAllResponse;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IEntityStateServiceStub;

public class EntityStateServiceStubMock extends ServiceStubMockBase<IEntityStateServiceStub> implements
		IEntityStateServiceStub {

	public EntityStateServiceStubMock(TestContext context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public RetrieveAllResponse retrieveAll(RetrieveAll retrieveAll) throws RemoteException {
		if (mock != null) {
			mock.retrieveAll(retrieveAll);
		}

		RetrieveAllResponse retrieveAllResponse = new RetrieveAllResponse();
		ArrayOfEntityStateDTO param = new ArrayOfEntityStateDTO();
		param.setEntityStateDTO(context.retrieveAllEntityStates());
		retrieveAllResponse.setRetrieveAllResult(param);
		return retrieveAllResponse;
	}
}
