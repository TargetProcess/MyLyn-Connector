package org.eclipse.mylyn.targetprocess.core.tests.mockservices;

import java.rmi.RemoteException;

import org.eclipse.mylyn.targetprocess.core.tests.TestContext;
import org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.GeneralDTO;
import org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.GetByID;
import org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.GetByIDResponse;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IGeneralServiceStub;

public class GeneralServiceStubMock extends ServiceStubMockBase<IGeneralServiceStub> implements IGeneralServiceStub {

	public GeneralServiceStubMock(TestContext context) {
		super(context);
	}

	@Override
	public GetByIDResponse getByID(GetByID getByID) throws RemoteException {
		if (mock != null) {
			mock.getByID(getByID);
		}

		GeneralDTO generalDTO = context.getGeneralDTOById(getByID.getId());

		GetByIDResponse response = new GetByIDResponse();
		response.setGetByIDResult(generalDTO);

		return response;
	}
}
