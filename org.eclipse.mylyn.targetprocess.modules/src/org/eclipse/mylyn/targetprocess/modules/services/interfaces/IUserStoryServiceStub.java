package org.eclipse.mylyn.targetprocess.modules.services.interfaces;

import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.GetByID;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.GetByIDResponse;

public interface IUserStoryServiceStub {

	GetByIDResponse getByID(GetByID getById) throws java.rmi.RemoteException;

	public org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.UpdateResponse update(
			org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.Update update24)
			throws java.rmi.RemoteException;

	public org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveCommentsForUserStoryResponse retrieveCommentsForUserStory(
			org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveCommentsForUserStory retrieveCommentsForUserStory22)
			throws java.rmi.RemoteException;

	public org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveAttachmentsForUserStoryResponse retrieveAttachmentsForUserStory(
			org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveAttachmentsForUserStory retrieveAttachmentsForUserStory)
			throws java.rmi.RemoteException;

	public org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.AddCommentToUserStoryResponse addCommentToUserStory(
			org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.AddCommentToUserStory addCommentToUserStory86)
			throws java.rmi.RemoteException;

}
