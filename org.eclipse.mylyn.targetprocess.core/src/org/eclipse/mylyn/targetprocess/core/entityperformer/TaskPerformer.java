package org.eclipse.mylyn.targetprocess.core.entityperformer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

import org.eclipse.mylyn.targetprocess.core.Messages;
import org.eclipse.mylyn.targetprocess.core.TargetProcessAttribute;
import org.eclipse.mylyn.targetprocess.core.TargetProcessEntityKind;
import org.eclipse.mylyn.targetprocess.modules.IServiceFactory;
import org.eclipse.mylyn.targetprocess.modules.TargetProcessCredentials;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AssignableToDoDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.MyAssignmentsToDo;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.AttachmentDTO;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveAttachmentsForTask;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveAttachmentsForTaskResponse;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveCommentsForTask;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveCommentsForTaskResponse;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.TaskDTO;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.GetByIDResponse;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.ITaskServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IUserStoryServiceStub;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskData;

public class TaskPerformer extends PerformerBase implements IPerformer {

	private ITaskServiceStub taskServiceStub;
	private IUserStoryServiceStub userStoryServiceStub;

	public TaskPerformer(IServiceFactory serviceFactory, TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		super(serviceFactory, targetProcessCredentials);
	}

	@Override
	public TaskData createTaskDataBy(int taskID, TaskRepository taskRepository) throws RemoteException {

		TaskServiceStub.GetByID getById = new TaskServiceStub.GetByID();
		getById.setId(taskID);

		TaskDTO taskDto = taskServiceStub.getByID(getById).getGetByIDResult();

		TaskData taskData = createTaskData(taskID, taskRepository);

		populateTaskDataWithTPTaskData(taskData, taskDto);

		return taskData;
	}

	@Override
	public TaskData createTaskDataBy(AssignableToDoDTO taskDTO, MyAssignmentsToDo toDoList,
			TaskRepository taskRepository) {
		TaskData taskData = super.createTaskDataBy(taskDTO, toDoList, taskRepository);

		addAttributeTo(taskData, TargetProcessAttribute.UserStory, taskDTO.getUserStoryName());

		return taskData;
	}

	private void populateTaskDataWithTPTaskData(TaskData taskData, TaskDTO taskDTO) throws RemoteException {
		addAttributeTo(taskData, TargetProcessAttribute.EntityKind, getEntityKind().toString());
		addAttributeTo(taskData, TargetProcessAttribute.Name, taskDTO.getName());
		addDescriptionAttribute(taskData, taskDTO.getDescription());
		addDateAttribute(taskData, TargetProcessAttribute.CreationDate, taskDTO.getCreateDate());
		addStateAttribute(taskData, taskDTO.getEntityStateID(), taskDTO.getEntityStateName());

		addAttributeTo(taskData, TargetProcessAttribute.ProjectName, taskDTO.getProjectName());
		addOwnerAttribute(taskData, taskDTO.getOwnerID());
		addAssignableUsersAttribute(taskData, taskDTO.getID());

		addAttributeTo(taskData, TargetProcessAttribute.Priority, taskDTO.getPriorityName());
		addAttributeTo(taskData, TargetProcessAttribute.UserStory, taskDTO.getUserStoryName());
		addTimeAttribute(taskData, TargetProcessAttribute.TimeSpent, taskDTO.getTimeSpent());
		addTimeAttribute(taskData, TargetProcessAttribute.TimeRemaining, taskDTO.getTimeRemain());
		addDateAttribute(taskData, TargetProcessAttribute.DateModification, taskDTO.getModifyDate());

		UserStoryServiceStub.GetByID getByID = new UserStoryServiceStub.GetByID();
		getByID.setId(taskDTO.getUserStoryID());
		GetByIDResponse userStoryResponse = userStoryServiceStub.getByID(getByID);
		addAttributeTo(taskData, TargetProcessAttribute.Priority, userStoryResponse.getGetByIDResult()
				.getPriorityName());
		addDateAttribute(taskData, TargetProcessAttribute.DateModification, taskDTO.getModifyDate());
		addCommentsAndAttachments(taskData, taskDTO.getID());
	}

	@Override
	public TargetProcessEntityKind getEntityKind() {
		return TargetProcessEntityKind.TASK;
	}

	@Override
	public String getEntityTypeName() {
		return EntityPerformerFactory.TP_BUSINESS_OBJECTS_TASK;
	}

	@Override
	public void update(TaskData taskData, Set<TaskAttribute> changedAttributes) throws RemoteException,
			EntityValidationException {
		TaskServiceStub.GetByID getByID = new TaskServiceStub.GetByID();
		getByID.setId(Integer.parseInt(taskData.getTaskId()));
		TaskServiceStub.GetByIDResponse getByIdResult = taskServiceStub.getByID(getByID);
		TaskDTO taskDTO = getByIdResult.getGetByIDResult();

		if (taskDTO != null) {
			if (!preUpdate(taskData, changedAttributes, taskDTO.getID())) {
				return;
			}

			taskDTO.setName(getAttributeValue(taskData, TargetProcessAttribute.Name));
			if (attributeHasChanged(taskData, changedAttributes, TargetProcessAttribute.Description)) {
				taskDTO.setDescription(getDescriptionAttributeValue(taskData));
			}
			String entityStateId = getAttributeValue(taskData, TargetProcessAttribute.State);
			taskDTO.setEntityStateID(Integer.parseInt(entityStateId));
			if (attributeHasChanged(taskData, changedAttributes, TargetProcessAttribute.State)) {
				taskDTO.setCommentOnChangingState(Messages.TargetProcessEntityPerformer_Default_Comment_On_Change_State);
			}

			TaskServiceStub.Update update = new TaskServiceStub.Update();
			update.setEntity(taskDTO);
			taskServiceStub.update(update);
		}
	}

	@Override
	public ArrayList<Comment> getComments(int entityId) throws RemoteException {
		RetrieveCommentsForTask retrieveCommentsForTask = new RetrieveCommentsForTask();
		retrieveCommentsForTask.setTaskID(entityId);
		RetrieveCommentsForTaskResponse response = taskServiceStub.retrieveCommentsForTask(retrieveCommentsForTask);

		TaskServiceStub.CommentDTO[] commentDTOs = response.getRetrieveCommentsForTaskResult().getCommentDTO();

		ArrayList<Comment> arrayOfComment = new ArrayList<Comment>();

		if (commentDTOs != null) {
			for (TaskServiceStub.CommentDTO commentDTO : commentDTOs) {
				arrayOfComment.add(new Comment(commentDTO.getDescription(), commentDTO.getOwnerID(), commentDTO
						.getCreateDate()));
			}
		}
		return arrayOfComment;
	}

	@Override
	protected void initServices() throws RemoteException {
		super.initServices();
		taskServiceStub = serviceFactory.getTaskServiceStub(targetProcessCredentials);
		userStoryServiceStub = serviceFactory.getUserStoryServiceStub(targetProcessCredentials);
	}

	@Override
	protected String getEmptyNameMessage() {
		return Messages.TargetProcessClientValidation_Task_name_is_empty;
	}

	@Override
	public ArrayList<Attachment> getAttachments(int entityId) throws RemoteException {
		RetrieveAttachmentsForTask retrieveAttachmentsForTask = new RetrieveAttachmentsForTask();
		retrieveAttachmentsForTask.setTaskID(entityId);
		RetrieveAttachmentsForTaskResponse retrieveCommentsForTaskResponse = taskServiceStub
				.retrieveAttachmentsForTask(retrieveAttachmentsForTask);

		AttachmentDTO[] attachmentDTOs = retrieveCommentsForTaskResponse.getRetrieveAttachmentsForTaskResult()
				.getAttachmentDTO();

		ArrayList<Attachment> arrayOfAttachment = new ArrayList<Attachment>();

		if (attachmentDTOs != null) {
			for (AttachmentDTO attachmentDTO : attachmentDTOs) {
				arrayOfAttachment.add(new Attachment(fileServiceStub, attachmentDTO.getOriginalFileName(),
						attachmentDTO.getDescription(), attachmentDTO.getAttachmentFileID(), attachmentDTO
								.getUniqueFileName(), attachmentDTO.getCreateDate().getTime(), attachmentDTO
								.getOwnerID(), attachmentDTO.getID()));
			}
		}
		return arrayOfAttachment;
	}

}
