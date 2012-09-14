package org.eclipse.mylyn.targetprocess.modules.services.interfaces;

public interface ITaskServiceStub {
	public org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.GetByIDResponse getByID(
			org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.GetByID getByID)
			throws java.rmi.RemoteException;

	public org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.UpdateResponse update(
			org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.Update update22)
			throws java.rmi.RemoteException;

	public org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveCommentsForTaskResponse retrieveCommentsForTask(
			org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveCommentsForTask retrieveCommentsForTask54)
			throws java.rmi.RemoteException;

	public org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveAttachmentsForTaskResponse retrieveAttachmentsForTask(
			org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveAttachmentsForTask retrieveAttachmentsForTask)
			throws java.rmi.RemoteException;

	public org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.AddCommentToTaskResponse addCommentToTask(
			org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.AddCommentToTask addCommentToTask14)
			throws java.rmi.RemoteException;
}
