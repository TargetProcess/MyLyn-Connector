package org.eclipse.mylyn.targetprocess.core.tests.mockservices;

import java.rmi.RemoteException;

import org.eclipse.mylyn.targetprocess.core.tests.TestContext;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.AddCommentToTask;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.AddCommentToTaskResponse;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.ArrayOfCommentDTO;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.CommentDTO;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.GetByID;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.GetByIDResponse;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveAttachmentsForTask;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveAttachmentsForTaskResponse;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveCommentsForTask;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveCommentsForTaskResponse;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.TaskDTO;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.Update;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.UpdateResponse;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.ITaskServiceStub;

public class TaskServiceStubMock extends ServiceStubMockBase<ITaskServiceStub> implements ITaskServiceStub {

	public TaskServiceStubMock(TestContext context) {
		super(context);
	}

	@Override
	public GetByIDResponse getByID(GetByID getByID) throws RemoteException {
		if (mock != null) {
			mock.getByID(getByID);
		}

		TaskDTO taskDTO = context.getTaskById(getByID.getId());
		GetByIDResponse response = new GetByIDResponse();
		response.setGetByIDResult(taskDTO);
		return response;
	}

	@Override
	public UpdateResponse update(Update update22) throws RemoteException {
		if (mock != null) {
			mock.update(update22);
		}

		return new UpdateResponse();
	}

	@Override
	public RetrieveCommentsForTaskResponse retrieveCommentsForTask(RetrieveCommentsForTask task) throws RemoteException {
		if (mock != null) {
			mock.retrieveCommentsForTask(task);
		}

		CommentDTO[] comments = context.retrieveCommentsForTask(task.getTaskID());
		RetrieveCommentsForTaskResponse response = new RetrieveCommentsForTaskResponse();
		ArrayOfCommentDTO arrayOfComments = new ArrayOfCommentDTO();
		
		for (CommentDTO comment : comments) {
			arrayOfComments.addCommentDTO(comment);
		}
		
		response.setRetrieveCommentsForTaskResult(arrayOfComments);

		return response;
	}

	@Override
	public RetrieveAttachmentsForTaskResponse retrieveAttachmentsForTask(
			RetrieveAttachmentsForTask retrieveAttachmentsForTask) throws RemoteException {
		if (this.mock != null)
		{
			this.mock.retrieveAttachmentsForTask(retrieveAttachmentsForTask);
		}

		RetrieveAttachmentsForTaskResponse response = new RetrieveAttachmentsForTaskResponse();
		
		TaskServiceStub.AttachmentDTO[] attachments = this.context.getTaskAttachmentList(retrieveAttachmentsForTask.getTaskID());
		
		TaskServiceStub.ArrayOfAttachmentDTO arrayOfAttachment = new TaskServiceStub.ArrayOfAttachmentDTO();
		for(TaskServiceStub.AttachmentDTO attachment :attachments)
		{
			arrayOfAttachment.addAttachmentDTO(attachment);	
		}
		 
		response.setRetrieveAttachmentsForTaskResult(arrayOfAttachment);
		return response;
	}

	@Override
	public AddCommentToTaskResponse addCommentToTask(AddCommentToTask addCommentToTask14) throws RemoteException {
		if (this.mock != null)
		{
			this.mock.addCommentToTask(addCommentToTask14);
		}
		
		return new AddCommentToTaskResponse();
	}

}
