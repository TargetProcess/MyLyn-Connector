package org.eclipse.mylyn.targetprocess.core.entityperformer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

import org.eclipse.mylyn.targetprocess.core.Messages;
import org.eclipse.mylyn.targetprocess.core.TargetProcessAttribute;
import org.eclipse.mylyn.targetprocess.core.TargetProcessEntityKind;
import org.eclipse.mylyn.targetprocess.modules.IServiceFactory;
import org.eclipse.mylyn.targetprocess.modules.TargetProcessCredentials;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AssignableToDoDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.MyAssignmentsToDo;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RequestDTO;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveAttachmentsForRequest;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveAttachmentsForRequestResponse;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveCommentsForRequest;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveCommentsForRequestResponse;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IRequestServiceStub;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskData;

public class RequestPerformer extends PerformerBase implements IPerformer {

	private IRequestServiceStub requestServiceStub;

	public RequestPerformer(IServiceFactory serviceFactory, TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		super(serviceFactory, targetProcessCredentials);
	}

	@Override
	public TaskData createTaskDataBy(int taskID, TaskRepository taskRepository) throws RemoteException {
		RequestServiceStub.GetByID getById = new RequestServiceStub.GetByID();
		getById.setId(taskID);

		RequestDTO requestDto = requestServiceStub.getByID(getById).getGetByIDResult();

		TaskData taskData = createTaskData(taskID, taskRepository);

		populateTaskDataWithTPRequestData(taskData, requestDto);
		return taskData;
	}

	private void populateTaskDataWithTPRequestData(TaskData taskData, RequestDTO request) throws RemoteException {
		addAttributeTo(taskData, TargetProcessAttribute.EntityKind, getEntityKind().getName());
		addAttributeTo(taskData, TargetProcessAttribute.Name, request.getName());
		addDescriptionAttribute(taskData, request.getDescription());
		addDateAttribute(taskData, TargetProcessAttribute.CreationDate, request.getCreateDate());
		addAttributeTo(taskData, TargetProcessAttribute.Priority, request.getPriorityName());

		addAttributeTo(taskData, TargetProcessAttribute.Priority, request.getPriorityName());
		addStateAttribute(taskData, request.getEntityStateID(), request.getEntityStateName());
		addAttributeTo(taskData, TargetProcessAttribute.ProjectName, request.getProjectName());
		addOwnerAttribute(taskData, request.getOwnerID());
		addAssignableUsersAttribute(taskData, request.getID());
		addTimeAttribute(taskData, TargetProcessAttribute.TimeSpent, request.getTimeSpent());
		addTimeAttribute(taskData, TargetProcessAttribute.TimeRemaining, request.getTimeRemain());
		addDateAttribute(taskData, TargetProcessAttribute.DateModification, request.getModifyDate());

		addCommentsAndAttachments(taskData, request.getID());
	}

	@Override
	public TargetProcessEntityKind getEntityKind() {
		return TargetProcessEntityKind.REQUEST;
	}

	@Override
	public String getEntityTypeName() {
		return EntityPerformerFactory.TP_BUSINESS_OBJECTS_REQUEST;
	}

	@Override
	public void update(TaskData taskData, Set<TaskAttribute> changedAttributes) throws RemoteException,
			EntityValidationException {

		RequestServiceStub.GetByID getByID = new RequestServiceStub.GetByID();
		getByID.setId(Integer.parseInt(taskData.getTaskId()));
		RequestServiceStub.GetByIDResponse getByIdResult = requestServiceStub.getByID(getByID);
		RequestDTO requestDTO = getByIdResult.getGetByIDResult();

		if (requestDTO != null) {
			if (!preUpdate(taskData, changedAttributes, requestDTO.getID()))
				return;

			requestDTO.setName(getAttributeValue(taskData, TargetProcessAttribute.Name));
			if (attributeHasChanged(taskData, changedAttributes, TargetProcessAttribute.Description)) {
				requestDTO.setDescription(getDescriptionAttributeValue(taskData));
			}
			
			String entityStateId = getAttributeValue(taskData, TargetProcessAttribute.State);
			requestDTO.setEntityStateID(Integer.parseInt(entityStateId));
			if (attributeHasChanged(taskData, changedAttributes, TargetProcessAttribute.State))
			{
				requestDTO.setCommentOnChangingState(Messages.TargetProcessEntityPerformer_Default_Comment_On_Change_State);
			}

			RequestServiceStub.Update update = new RequestServiceStub.Update();
			update.setEntity(requestDTO);
			requestServiceStub.update(update);
		}
	}

	@Override
	public ArrayList<Comment> getComments(int entityId) throws RemoteException {
		RetrieveCommentsForRequest retrieveParams = new RetrieveCommentsForRequest();
		retrieveParams.setRequestID(entityId);
		RetrieveCommentsForRequestResponse response = requestServiceStub.retrieveCommentsForRequest(retrieveParams);

		RequestServiceStub.CommentDTO[] commentDTOs = response.getRetrieveCommentsForRequestResult().getCommentDTO();

		ArrayList<Comment> arrayOfComment = new ArrayList<Comment>();

		if (commentDTOs != null) {
			for (RequestServiceStub.CommentDTO commentDTO : commentDTOs) {
				arrayOfComment.add(new Comment(commentDTO.getDescription(), commentDTO.getOwnerID(), commentDTO
						.getCreateDate()));
			}
		}
		return arrayOfComment;
	}

	@Override
	protected void initServices() throws RemoteException {
		super.initServices();
		requestServiceStub = serviceFactory.getRequestServiceStub(targetProcessCredentials);
	}

	@Override
	protected String getEmptyNameMessage() {
		return Messages.TargetProcessClientValidation_Request_name_is_empty;
	}

	@Override
	public ArrayList<Attachment> getAttachments(int entityId) throws RemoteException {

		RetrieveAttachmentsForRequest retrieveAttachmentsForRequest = new RetrieveAttachmentsForRequest();
		retrieveAttachmentsForRequest.setRequestID(entityId);
		RetrieveAttachmentsForRequestResponse retrieveCommentsForRequestResponse = requestServiceStub
				.retrieveAttachmentsForRequest(retrieveAttachmentsForRequest);

		RequestServiceStub.AttachmentDTO[] attachmentDTOs = retrieveCommentsForRequestResponse
				.getRetrieveAttachmentsForRequestResult().getAttachmentDTO();

		ArrayList<Attachment> arrayOfAttachment = new ArrayList<Attachment>();

		if (attachmentDTOs != null) {
			for (RequestServiceStub.AttachmentDTO attachmentDTO : attachmentDTOs) {
				arrayOfAttachment.add(new Attachment(fileServiceStub, attachmentDTO.getOriginalFileName(),
						attachmentDTO.getDescription(), attachmentDTO.getAttachmentFileID(), attachmentDTO
								.getUniqueFileName(), attachmentDTO.getCreateDate().getTime(), attachmentDTO
								.getOwnerID(), attachmentDTO.getID()));
			}
		}
		return arrayOfAttachment;
	}	

	@Override
	public TaskData createTaskDataBy(AssignableToDoDTO taskDTO, MyAssignmentsToDo toDoList,
			TaskRepository taskRepository) {
		TaskData taskData = super.createTaskDataBy(taskDTO, toDoList, taskRepository);
		return taskData;
	}
}
