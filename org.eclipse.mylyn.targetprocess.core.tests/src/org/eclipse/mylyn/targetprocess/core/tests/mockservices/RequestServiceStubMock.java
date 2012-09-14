package org.eclipse.mylyn.targetprocess.core.tests.mockservices;

import java.rmi.RemoteException;

import org.eclipse.mylyn.targetprocess.core.tests.TestContext;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.AddCommentToRequest;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.AddCommentToRequestResponse;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.GetByID;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.GetByIDResponse;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveAttachmentsForRequest;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveAttachmentsForRequestResponse;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveCommentsForRequest;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveCommentsForRequestResponse;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.Update;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.UpdateResponse;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IRequestServiceStub;

public class RequestServiceStubMock extends ServiceStubMockBase<IRequestServiceStub> implements IRequestServiceStub {

	public RequestServiceStubMock(TestContext context) {
		super(context);
	}

	@Override
	public GetByIDResponse getByID(GetByID getByID0) throws RemoteException {
		if (mock != null) {
			mock.getByID(getByID0);
		}

		GetByIDResponse response = new GetByIDResponse();
		response.setGetByIDResult(context.getRequestById(getByID0.getId()));
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
	public RetrieveCommentsForRequestResponse retrieveCommentsForRequest(
			RetrieveCommentsForRequest retrieveCommentsForRequest) throws RemoteException {
		if (this.mock != null){
			this.mock.retrieveCommentsForRequest(retrieveCommentsForRequest);
		}
		
		RetrieveCommentsForRequestResponse retrieveCommentsForBugResponse = new RetrieveCommentsForRequestResponse();
		RequestServiceStub.ArrayOfCommentDTO arrayOfCommentDTO = new RequestServiceStub.ArrayOfCommentDTO();
		for(RequestServiceStub.CommentDTO commentDTO : this.context.getRequestCommentList())
		{
			arrayOfCommentDTO.addCommentDTO(commentDTO);
		}
		
		retrieveCommentsForBugResponse.setRetrieveCommentsForRequestResult(arrayOfCommentDTO);
		return retrieveCommentsForBugResponse;
	}

	@Override
	public RetrieveAttachmentsForRequestResponse retrieveAttachmentsForRequest(
			RetrieveAttachmentsForRequest retrieveAttachmentsForRequest) throws RemoteException {

		if (this.mock != null){
			this.mock.retrieveAttachmentsForRequest(retrieveAttachmentsForRequest);
		}
		
		RetrieveAttachmentsForRequestResponse response = new RequestServiceStub.RetrieveAttachmentsForRequestResponse();
		
		RequestServiceStub.AttachmentDTO[] attachments = this.context.getRequestAttachmentList();
		
		RequestServiceStub.ArrayOfAttachmentDTO arrayOfAttachment = new RequestServiceStub.ArrayOfAttachmentDTO();
		for(RequestServiceStub.AttachmentDTO attachment :attachments)
		{
			arrayOfAttachment.addAttachmentDTO(attachment);	
		}
		 
		response.setRetrieveAttachmentsForRequestResult(arrayOfAttachment);
		return response;
	}

	@Override
	public AddCommentToRequestResponse addCommentToRequest(AddCommentToRequest addCommentToRequest26)
			throws RemoteException {
		if (this.mock != null){
			this.mock.addCommentToRequest(addCommentToRequest26);
		}
		
		return new AddCommentToRequestResponse(); 
	}
}
