package org.eclipse.mylyn.targetprocess.core.tests.mockservices;

import java.rmi.RemoteException;

import org.eclipse.mylyn.targetprocess.core.tests.TestContext;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.ArrayOfAssignableToDoDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AssignableToDoDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.GetMyAssigments;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.GetMyAssigmentsResponse;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.GetMyAssignmentById;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.GetMyAssignmentByIdResponse;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.GetMyToDoList;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.GetMyToDoListResponse;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.MyAssignments;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.MyAssignmentsToDo;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IMyAssignmentsServiceStub;

public class MyAssignmentsServiceStubMock extends ServiceStubMockBase<IMyAssignmentsServiceStub> implements IMyAssignmentsServiceStub {

	public MyAssignmentsServiceStubMock(TestContext context)
	{
		super(context);
	}
	
	@Override
	public GetMyAssigmentsResponse getMyAssigments(GetMyAssigments getMyAssigments) throws RemoteException {
		if (mock != null){
			mock.getMyAssigments(getMyAssigments);
		}
		
		MyAssignments myAssignments = this.context.getMyAssignments();
		GetMyAssigmentsResponse response = new GetMyAssigmentsResponse();
		response.setGetMyAssigmentsResult(myAssignments);
		return response;
	}

	@Override
	public GetMyToDoListResponse getMyToDoList(GetMyToDoList getMyToDoList) throws RemoteException {
		if (mock != null){
			mock.getMyToDoList(getMyToDoList);
		}
		GetMyToDoListResponse response = new GetMyToDoListResponse();
		MyAssignmentsToDo result = this.context.getMyToDoList();
		response.setGetMyToDoListResult(result);		
		return response;
		
	}

	@Override
	public GetMyAssignmentByIdResponse getMyAssignmentById(GetMyAssignmentById getMyAssignmentById2)
			throws RemoteException {
		
		if (mock != null){
			mock.getMyAssignmentById(getMyAssignmentById2);
		}
		
		GetMyAssignmentByIdResponse response = new GetMyAssignmentByIdResponse();
		MyAssignmentsToDo toDo = this.context.getMyToDoList();
		for (AssignableToDoDTO assignableToDoDTO : toDo.getAssignableEntities().getAssignableToDoDTO())
		{
			if(assignableToDoDTO.getID() == getMyAssignmentById2.getId())
			{
				MyAssignmentsToDo assignmentToDo = new MyAssignmentsToDo();				
				assignmentToDo.setAssignableEntities(new ArrayOfAssignableToDoDTO());
				assignmentToDo.getAssignableEntities().addAssignableToDoDTO(assignableToDoDTO);
				assignmentToDo.setUsers(toDo.getUsers());
				assignmentToDo.setUsers(toDo.getUsers());
				assignmentToDo.setEntityStates(toDo.getEntityStates());
				response.setGetMyAssignmentByIdResult(assignmentToDo);
			}
		}		
		return response;
	}
}
