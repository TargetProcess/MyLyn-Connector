package org.eclipse.mylyn.targetprocess.core.tests.mockservices;

import java.rmi.RemoteException;

import org.eclipse.mylyn.targetprocess.core.tests.TestContext;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.AddCommentToUserStory;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.AddCommentToUserStoryResponse;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.GetByID;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.GetByIDResponse;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveAttachmentsForUserStory;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveAttachmentsForUserStoryResponse;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveCommentsForUserStory;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveCommentsForUserStoryResponse;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.Update;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.UpdateResponse;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.UserStoryDTO;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IUserStoryServiceStub;

public class UserStoryServiceStubMock extends ServiceStubMockBase<IUserStoryServiceStub> implements IUserStoryServiceStub {

	public UserStoryServiceStubMock(TestContext context) {
		super(context);
	}

	@Override
	public GetByIDResponse getByID(GetByID getByID) throws RemoteException {
		if (this.mock != null){
			this.mock.getByID(getByID);
		}
		
		UserStoryDTO userStoryDTO = this.context.getUserStoryById(getByID.getId());
		GetByIDResponse response = new GetByIDResponse();
		response.setGetByIDResult(userStoryDTO);
		return response;
	}

	@Override
	public UpdateResponse update(Update update) throws RemoteException {
		if (this.mock != null){
			this.mock.update(update);
		}

		return new UpdateResponse();
	}

	@Override
	public RetrieveCommentsForUserStoryResponse retrieveCommentsForUserStory(
			RetrieveCommentsForUserStory retrieveCommentsForUserStory) throws RemoteException {
		
		if (this.mock != null){
			this.mock.retrieveCommentsForUserStory(retrieveCommentsForUserStory);
		}
		
		RetrieveCommentsForUserStoryResponse retrieveCommentsForBugResponse = new RetrieveCommentsForUserStoryResponse();
		UserStoryServiceStub.ArrayOfCommentDTO arrayOfCommentDTO = new UserStoryServiceStub.ArrayOfCommentDTO();
		for(UserStoryServiceStub.CommentDTO commentDTO : this.context.getUserStoryCommentList())
		{
			arrayOfCommentDTO.addCommentDTO(commentDTO);
		}
		
		retrieveCommentsForBugResponse.setRetrieveCommentsForUserStoryResult(arrayOfCommentDTO);
		return retrieveCommentsForBugResponse;
	}

	@Override
	public RetrieveAttachmentsForUserStoryResponse retrieveAttachmentsForUserStory(
			RetrieveAttachmentsForUserStory retrieveAttachmentsForUserStory) throws RemoteException {
		
		if(this.mock != null)
		{
			this.mock.retrieveAttachmentsForUserStory(retrieveAttachmentsForUserStory);
		}
		
		RetrieveAttachmentsForUserStoryResponse response = new RetrieveAttachmentsForUserStoryResponse();
		
		UserStoryServiceStub.AttachmentDTO[] attachments = this.context.getUserStoryAttachmentList();
		
		UserStoryServiceStub.ArrayOfAttachmentDTO arrayOfAttachment = new UserStoryServiceStub.ArrayOfAttachmentDTO();
		for(UserStoryServiceStub.AttachmentDTO attachment :attachments)
		{
			arrayOfAttachment.addAttachmentDTO(attachment);	
		}
		 
		response.setRetrieveAttachmentsForUserStoryResult(arrayOfAttachment);
		return response;
	}

	@Override
	public AddCommentToUserStoryResponse addCommentToUserStory(AddCommentToUserStory addCommentToUserStory86)
			throws RemoteException {
		if(this.mock != null)
		{
			this.mock.addCommentToUserStory(addCommentToUserStory86);
		}
		
		return new AddCommentToUserStoryResponse();
	}

}
