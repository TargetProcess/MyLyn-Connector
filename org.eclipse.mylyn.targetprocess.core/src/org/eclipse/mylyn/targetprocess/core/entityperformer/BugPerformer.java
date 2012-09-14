package org.eclipse.mylyn.targetprocess.core.entityperformer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

import org.eclipse.mylyn.targetprocess.core.Messages;
import org.eclipse.mylyn.targetprocess.core.TargetProcessAttribute;
import org.eclipse.mylyn.targetprocess.core.TargetProcessEntityKind;
import org.eclipse.mylyn.targetprocess.modules.IServiceFactory;
import org.eclipse.mylyn.targetprocess.modules.TargetProcessCredentials;
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
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AssignableToDoDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.MyAssignmentsToDo;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IBugServiceStub;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskData;

public class BugPerformer extends PerformerBase implements IPerformer {

	public static final String EntityReadableName = "Bug";
	private IBugServiceStub bugServiceStub;

	public BugPerformer(IServiceFactory serviceFactory, TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		super(serviceFactory, targetProcessCredentials);
	}

	protected void populateTaskDataWithTPBugData(TaskData taskData, BugDTO bugDTO) throws RemoteException {
		addAttributeTo(taskData, TargetProcessAttribute.EntityKind, getEntityKind().getName());
		addAttributeTo(taskData, TargetProcessAttribute.Name, bugDTO.getName());
		addDateAttribute(taskData, TargetProcessAttribute.CreationDate, bugDTO.getCreateDate());

		addDescriptionAttribute(taskData, bugDTO.getDescription());
		addAttributeTo(taskData, TargetProcessAttribute.Priority, bugDTO.getPriorityName());

		addStateAttribute(taskData, bugDTO.getEntityStateID(), bugDTO.getEntityStateName());

		addAttributeTo(taskData, TargetProcessAttribute.ProjectName, bugDTO.getProjectName());
		addOwnerAttribute(taskData, bugDTO.getOwnerID());
		addAssignableUsersAttribute(taskData, bugDTO.getID());

		addAttributeTo(taskData, TargetProcessAttribute.UserStory, bugDTO.getUserStoryName());
		addAttributeTo(taskData, TargetProcessAttribute.Severity, bugDTO.getSeverityName());
		addAttributeTo(taskData, TargetProcessAttribute.Priority, bugDTO.getPriorityName());
		addTimeAttribute(taskData, TargetProcessAttribute.TimeSpent, bugDTO.getTimeSpent());
		addTimeAttribute(taskData, TargetProcessAttribute.TimeRemaining, bugDTO.getTimeRemain());
		addDateAttribute(taskData, TargetProcessAttribute.DateModification, bugDTO.getModifyDate());

		addCommentsAndAttachments(taskData, bugDTO.getID());
	}

	@Override
	public TaskData createTaskDataBy(int bugID, TaskRepository taskRepository) throws RemoteException {

		GetByID getById = new GetByID();
		getById.setId(bugID);

		BugDTO bugDto = bugServiceStub.getByID(getById).getGetByIDResult();

		TaskData taskData = createTaskData(bugID, taskRepository);

		populateTaskDataWithTPBugData(taskData, bugDto);
		return taskData;
	}

	@Override
	public TaskData createTaskDataBy(AssignableToDoDTO bugDTO, MyAssignmentsToDo toDoList,
			TaskRepository taskRepository) {
		TaskData taskData = super.createTaskDataBy(bugDTO, toDoList, taskRepository);
		
		addAttributeTo(taskData, TargetProcessAttribute.UserStory, bugDTO.getUserStoryName());
		addAttributeTo(taskData, TargetProcessAttribute.Severity, bugDTO.getSeverityName());
		
		
		return taskData;
	}
	@Override
	public TargetProcessEntityKind getEntityKind() {
		return TargetProcessEntityKind.BUG;
	}

	@Override
	public String getEntityTypeName() {
		return EntityPerformerFactory.TP_BUSINESS_OBJECTS_BUG;
	}

	@Override
	public void update(TaskData taskData, Set<TaskAttribute> changedAttributes) throws EntityValidationException,
			EntityWasDeletedException, RemoteException {

		GetByID getByID = new GetByID();
		getByID.setId(Integer.parseInt(taskData.getTaskId()));
		GetByIDResponse getByIdResult = bugServiceStub.getByID(getByID);
		BugDTO bugDTO = getByIdResult.getGetByIDResult();

		if (bugDTO != null) {
			if (!preUpdate(taskData, changedAttributes, bugDTO.getID()))
				return;

			bugDTO.setName(getAttributeValue(taskData, TargetProcessAttribute.Name));

			if (attributeHasChanged(taskData, changedAttributes, TargetProcessAttribute.Description)) {
				bugDTO.setDescription(getDescriptionAttributeValue(taskData));
			}

			String entityStateId = getAttributeValue(taskData, TargetProcessAttribute.State);
			bugDTO.setEntityStateID(Integer.parseInt(entityStateId));
			if (attributeHasChanged(taskData, changedAttributes, TargetProcessAttribute.State))
			{
				bugDTO.setCommentOnChangingState(Messages.TargetProcessEntityPerformer_Default_Comment_On_Change_State);
			}

			Update update = new Update();
			update.setEntity(bugDTO);
			bugServiceStub.update(update);
		}
	}

	@Override
	protected void initServices() throws RemoteException {
		super.initServices();
		bugServiceStub = serviceFactory.getBugServiceStub(targetProcessCredentials);
	}

	@Override
	public ArrayList<Comment> getComments(int entityId) throws RemoteException {

		RetrieveCommentsForBug retrieveCommentsForBug = new RetrieveCommentsForBug();
		retrieveCommentsForBug.setBugID(entityId);
		RetrieveCommentsForBugResponse retrieveCommentsForBugResponse = bugServiceStub
				.retrieveCommentsForBug(retrieveCommentsForBug);

		CommentDTO[] commentDTOs = retrieveCommentsForBugResponse.getRetrieveCommentsForBugResult().getCommentDTO();

		ArrayList<Comment> arrayOfComment = new ArrayList<Comment>();

		if (commentDTOs != null) {
			for (CommentDTO commentDTO : commentDTOs) {
				arrayOfComment.add(new Comment(commentDTO.getDescription(), commentDTO.getOwnerID(), commentDTO
						.getCreateDate()));
			}
		}
		return arrayOfComment;
	}

	@Override
	protected String getEmptyNameMessage() {
		return Messages.TargetProcessClientValidation_Bug_name_is_empty;
	}

	@Override
	public ArrayList<Attachment> getAttachments(int entityId) throws RemoteException {

		RetrieveAttachmentsForBug retrieveAttachmentsForBug = new RetrieveAttachmentsForBug();
		retrieveAttachmentsForBug.setBugID(entityId);
		RetrieveAttachmentsForBugResponse retrieveCommentsForBugResponse = bugServiceStub
				.retrieveAttachmentsForBug(retrieveAttachmentsForBug);

		AttachmentDTO[] attachmentDTOs = retrieveCommentsForBugResponse.getRetrieveAttachmentsForBugResult()
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
