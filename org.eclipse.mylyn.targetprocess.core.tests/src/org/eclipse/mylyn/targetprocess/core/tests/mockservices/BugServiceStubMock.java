package org.eclipse.mylyn.targetprocess.core.tests.mockservices;

import java.rmi.RemoteException;

import org.eclipse.mylyn.targetprocess.core.tests.TestContext;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.AddCommentToBug;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.AddCommentToBugResponse;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.ArrayOfAttachmentDTO;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.ArrayOfCommentDTO;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.AttachmentDTO;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.BugDTO;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.CommentDTO;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.GetByID;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.GetByIDResponse;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveAttachmentsForBug;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveAttachmentsForBugResponse;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveCommentsForBug;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveCommentsForBugResponse;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.Update;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.UpdateResponse;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IBugServiceStub;

public class BugServiceStubMock extends ServiceStubMockBase<IBugServiceStub> implements IBugServiceStub {
	
	public BugServiceStubMock(TestContext context)
	{
		super(context);
	}
	
	@Override
	public GetByIDResponse getByID(GetByID getByID) throws RemoteException {		
		
		if (this.mock != null){
			this.mock.getByID(getByID);
		}
		
		BugDTO bugDTO = this.context.getBugById(getByID.getId());
		GetByIDResponse response = new GetByIDResponse();
		response.setGetByIDResult(bugDTO);
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
	public RetrieveCommentsForBugResponse retrieveCommentsForBug(RetrieveCommentsForBug retrieveCommentsForBug)
			throws RemoteException {
		if (this.mock != null){
			this.mock.retrieveCommentsForBug(retrieveCommentsForBug);
		}
		
		RetrieveCommentsForBugResponse retrieveCommentsForBugResponse = new RetrieveCommentsForBugResponse();
		ArrayOfCommentDTO arrayOfCommentDTO = new ArrayOfCommentDTO();
		for(CommentDTO commentDTO : this.context.getBugCommentList())
		{
			arrayOfCommentDTO.addCommentDTO(commentDTO);
		}
		
		retrieveCommentsForBugResponse.setRetrieveCommentsForBugResult(arrayOfCommentDTO);
		return retrieveCommentsForBugResponse;
	}

	@Override
	public RetrieveAttachmentsForBugResponse retrieveAttachmentsForBug(
			RetrieveAttachmentsForBug retrieveAttachmentsForBug64) throws RemoteException {
		
		if(this.mock != null)
		{
			this.mock.retrieveAttachmentsForBug(retrieveAttachmentsForBug64);
		}
		RetrieveAttachmentsForBugResponse response = new RetrieveAttachmentsForBugResponse();
		
		AttachmentDTO[] attachments = this.context.getBugAttachmentList();
		
		ArrayOfAttachmentDTO arrayOfAttachment = new ArrayOfAttachmentDTO();
		for(AttachmentDTO attachment :attachments)
		{
			arrayOfAttachment.addAttachmentDTO(attachment);	
		}
		 
		response.setRetrieveAttachmentsForBugResult(arrayOfAttachment);
		return response;
	}

	@Override
	public AddCommentToBugResponse addCommentToBug(AddCommentToBug addCommentToBug2) throws RemoteException {
		
		if(this.mock != null)
		{
			this.mock.addCommentToBug(addCommentToBug2);
		}
		
		return new AddCommentToBugResponse();
	}

}
