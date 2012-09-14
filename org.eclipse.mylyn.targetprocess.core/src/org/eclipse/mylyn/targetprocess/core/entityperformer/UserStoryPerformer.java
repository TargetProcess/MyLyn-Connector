package org.eclipse.mylyn.targetprocess.core.entityperformer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

import org.eclipse.mylyn.targetprocess.core.Messages;
import org.eclipse.mylyn.targetprocess.core.TargetProcessAttribute;
import org.eclipse.mylyn.targetprocess.core.TargetProcessEntityKind;
import org.eclipse.mylyn.targetprocess.modules.IServiceFactory;
import org.eclipse.mylyn.targetprocess.modules.TargetProcessCredentials;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AssignableToDoDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.MyAssignmentsToDo;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveAttachmentsForUserStory;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveAttachmentsForUserStoryResponse;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveCommentsForUserStory;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveCommentsForUserStoryResponse;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.UserStoryDTO;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IUserStoryServiceStub;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskData;

public class UserStoryPerformer extends PerformerBase implements IPerformer {

	private IUserStoryServiceStub userStoryServiceStub;

	public UserStoryPerformer(IServiceFactory serviceFactory, TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		super(serviceFactory, targetProcessCredentials);

	}

	@Override
	public TaskData createTaskDataBy(int userStoryID, TaskRepository taskRepository) throws RemoteException {

		UserStoryServiceStub.GetByID getById = new UserStoryServiceStub.GetByID();
		getById.setId(userStoryID);

		UserStoryDTO userStoryDto = userStoryServiceStub.getByID(getById).getGetByIDResult();

		TaskData taskData = createTaskData(userStoryID, taskRepository);

		populateTaskDataWithTPUserStoryData(taskData, userStoryDto);
		return taskData;
	}

	private void populateTaskDataWithTPUserStoryData(TaskData taskData, UserStoryDTO userStoryDTO)
			throws RemoteException {
		addAttributeTo(taskData, TargetProcessAttribute.EntityKind, getEntityKind().getName());
		addAttributeTo(taskData, TargetProcessAttribute.Name, userStoryDTO.getName());
		addDescriptionAttribute(taskData, userStoryDTO.getDescription());

		addAttributeTo(taskData, TargetProcessAttribute.ProjectName, userStoryDTO.getProjectName());
		addOwnerAttribute(taskData, userStoryDTO.getOwnerID());
		addAssignableUsersAttribute(taskData, userStoryDTO.getID());

		addAttributeTo(taskData, TargetProcessAttribute.Priority, userStoryDTO.getPriorityName());
		addTimeAttribute(taskData, TargetProcessAttribute.TimeSpent, userStoryDTO.getTimeSpent());
		addTimeAttribute(taskData, TargetProcessAttribute.TimeRemaining, userStoryDTO.getTimeRemain());
		addDateAttribute(taskData, TargetProcessAttribute.DateModification, userStoryDTO.getModifyDate());

		addDateAttribute(taskData, TargetProcessAttribute.CreationDate, userStoryDTO.getCreateDate());
		addAttributeTo(taskData, TargetProcessAttribute.Priority, userStoryDTO.getPriorityName());

		addDateAttribute(taskData, TargetProcessAttribute.DateModification, userStoryDTO.getModifyDate());

		addStateAttribute(taskData, userStoryDTO.getEntityStateID(), userStoryDTO.getEntityStateName());

		addCommentsAndAttachments(taskData, userStoryDTO.getID());
	}

	@Override
	public TargetProcessEntityKind getEntityKind() {
		return TargetProcessEntityKind.USERSTORY;
	}

	@Override
	public String getEntityTypeName() {
		return EntityPerformerFactory.TP_BUSINESS_OBJECTS_USER_STORY;
	}

	@Override
	public void update(TaskData taskData, Set<TaskAttribute> changedAttributes) throws RemoteException,
			EntityValidationException {
		UserStoryServiceStub.GetByID getByID = new UserStoryServiceStub.GetByID();
		getByID.setId(Integer.parseInt(taskData.getTaskId()));
		UserStoryServiceStub.GetByIDResponse getByIdResult = userStoryServiceStub.getByID(getByID);
		UserStoryDTO userStoryDTO = getByIdResult.getGetByIDResult();

		if (userStoryDTO != null) {
			if (!preUpdate(taskData, changedAttributes, userStoryDTO.getID())) {
				return;
			}

			userStoryDTO.setName(getAttributeValue(taskData, TargetProcessAttribute.Name));
			if (attributeHasChanged(taskData, changedAttributes, TargetProcessAttribute.Description)) {
				userStoryDTO.setDescription(getDescriptionAttributeValue(taskData));
			}
			String entityStateId = getAttributeValue(taskData, TargetProcessAttribute.State);
			userStoryDTO.setEntityStateID(Integer.parseInt(entityStateId));
			if (attributeHasChanged(taskData, changedAttributes, TargetProcessAttribute.State)) {
				userStoryDTO.setCommentOnChangingState(Messages.TargetProcessEntityPerformer_Default_Comment_On_Change_State);
			}

			UserStoryServiceStub.Update update = new UserStoryServiceStub.Update();
			update.setEntity(userStoryDTO);
			userStoryServiceStub.update(update);
		}
	}

	@Override
	public ArrayList<Comment> getComments(int entityId) throws RemoteException {
		RetrieveCommentsForUserStory retrieveCommentsParams = new RetrieveCommentsForUserStory();
		retrieveCommentsParams.setUserStoryID(entityId);
		RetrieveCommentsForUserStoryResponse response = userStoryServiceStub
				.retrieveCommentsForUserStory(retrieveCommentsParams);

		UserStoryServiceStub.CommentDTO[] commentDTOs = response.getRetrieveCommentsForUserStoryResult()
				.getCommentDTO();

		ArrayList<Comment> arrayOfComment = new ArrayList<Comment>();

		if (commentDTOs != null) {
			for (UserStoryServiceStub.CommentDTO commentDTO : commentDTOs) {
				arrayOfComment.add(new Comment(commentDTO.getDescription(), commentDTO.getOwnerID(), commentDTO
						.getCreateDate()));
			}
		}
		return arrayOfComment;
	}

	@Override
	protected void initServices() throws RemoteException {
		super.initServices();
		userStoryServiceStub = serviceFactory.getUserStoryServiceStub(targetProcessCredentials);
	}

	@Override
	protected String getEmptyNameMessage() {
		return Messages.TargetProcessClientValidation_UserStory_name_is_empty;
	}

	@Override
	public ArrayList<Attachment> getAttachments(int entityId) throws RemoteException {
		RetrieveAttachmentsForUserStory retrieveAttachmentsForUserStory = new RetrieveAttachmentsForUserStory();
		retrieveAttachmentsForUserStory.setUserStoryID(entityId);
		RetrieveAttachmentsForUserStoryResponse retrieveCommentsForTaskResponse = userStoryServiceStub
				.retrieveAttachmentsForUserStory(retrieveAttachmentsForUserStory);

		UserStoryServiceStub.AttachmentDTO[] attachmentDTOs = retrieveCommentsForTaskResponse
				.getRetrieveAttachmentsForUserStoryResult().getAttachmentDTO();

		ArrayList<Attachment> arrayOfAttachment = new ArrayList<Attachment>();

		if (attachmentDTOs != null) {
			for (UserStoryServiceStub.AttachmentDTO attachmentDTO : attachmentDTOs) {
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
