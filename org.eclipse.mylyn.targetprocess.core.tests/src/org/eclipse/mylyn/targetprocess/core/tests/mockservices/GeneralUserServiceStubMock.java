package org.eclipse.mylyn.targetprocess.core.tests.mockservices;

import java.rmi.RemoteException;

import org.eclipse.mylyn.targetprocess.core.tests.TestContext;
import org.eclipse.mylyn.targetprocess.modules.services.GeneralUserServiceStub.ArrayOfGeneralUserDTO;
import org.eclipse.mylyn.targetprocess.modules.services.GeneralUserServiceStub.GeneralUserDTO;
import org.eclipse.mylyn.targetprocess.modules.services.GeneralUserServiceStub.GetByID;
import org.eclipse.mylyn.targetprocess.modules.services.GeneralUserServiceStub.GetByIDResponse;
import org.eclipse.mylyn.targetprocess.modules.services.GeneralUserServiceStub.Retrieve;
import org.eclipse.mylyn.targetprocess.modules.services.GeneralUserServiceStub.RetrieveResponse;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IGeneralUserServiceStub;

public class GeneralUserServiceStubMock extends ServiceStubMockBase<IGeneralUserServiceStub> implements IGeneralUserServiceStub {

	public GeneralUserServiceStubMock(TestContext context) {
		super(context);
	}

	@Override
	public GetByIDResponse getByID(GetByID getByID) throws RemoteException {
		if (mock != null){
			mock.getByID(getByID);
		}
		
		GeneralUserDTO userDTO = this.context.getUserById(getByID.getId());
		
		GetByIDResponse response = new GetByIDResponse();
		response.setGetByIDResult(userDTO);
		
		return response;		
	}

	@Override
	public RetrieveResponse retrieve(Retrieve retrieve12) throws RemoteException {
		if (mock != null){
			mock.retrieve(retrieve12);
		}
		
		GeneralUserDTO user = this.context.getUserList().get(0);
		RetrieveResponse retrieveResponse = new RetrieveResponse();
		ArrayOfGeneralUserDTO arrayOfGeneralUserDto = new ArrayOfGeneralUserDTO();;		
		GeneralUserDTO[] usersDTO = new GeneralUserDTO[]{user};
		arrayOfGeneralUserDto.setGeneralUserDTO(usersDTO );
		retrieveResponse.setRetrieveResult(arrayOfGeneralUserDto );
		return retrieveResponse;
	}
}
