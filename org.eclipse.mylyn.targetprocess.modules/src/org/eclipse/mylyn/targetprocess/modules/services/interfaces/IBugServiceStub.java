package org.eclipse.mylyn.targetprocess.modules.services.interfaces;

import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.GetByID;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.GetByIDResponse;

public interface IBugServiceStub {

	GetByIDResponse getByID(GetByID getByID0) throws java.rmi.RemoteException;

	public org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.UpdateResponse update(
			org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.Update update24)
			throws java.rmi.RemoteException;

	public org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveCommentsForBugResponse retrieveCommentsForBug(
			org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveCommentsForBug retrieveCommentsForBug14)
			throws java.rmi.RemoteException;

	public org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveAttachmentsForBugResponse retrieveAttachmentsForBug(
			org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveAttachmentsForBug retrieveAttachmentsForBug64)
			throws java.rmi.RemoteException;

	public org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.AddCommentToBugResponse addCommentToBug(
			org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.AddCommentToBug addCommentToBug2)
			throws java.rmi.RemoteException;

}
