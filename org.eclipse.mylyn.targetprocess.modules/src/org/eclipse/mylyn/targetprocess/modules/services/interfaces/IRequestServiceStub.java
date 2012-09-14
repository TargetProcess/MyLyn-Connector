package org.eclipse.mylyn.targetprocess.modules.services.interfaces;

public interface IRequestServiceStub {
	public org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.GetByIDResponse getByID(
			org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.GetByID getByID0)
			throws java.rmi.RemoteException;

	public org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.UpdateResponse update(
			org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.Update update22)
			throws java.rmi.RemoteException;

	public org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveCommentsForRequestResponse retrieveCommentsForRequest(
			org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveCommentsForRequest retrieveCommentsForRequest102)
			throws java.rmi.RemoteException;

	public org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveAttachmentsForRequestResponse retrieveAttachmentsForRequest(
			org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveAttachmentsForRequest retrieveAttachmentsForBug64)
			throws java.rmi.RemoteException;

	public org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.AddCommentToRequestResponse addCommentToRequest(
			org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.AddCommentToRequest addCommentToRequest26)
			throws java.rmi.RemoteException;
}
