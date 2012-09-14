package org.eclipse.mylyn.targetprocess.core.entityperformer;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.mylyn.targetprocess.core.EntityStateManager;
import org.eclipse.mylyn.targetprocess.core.TargetProcessAttachmentMapper;
import org.eclipse.mylyn.targetprocess.core.TargetProcessAttribute;
import org.eclipse.mylyn.targetprocess.core.TargetProcessAttributeMapper;
import org.eclipse.mylyn.targetprocess.modules.IServiceFactory;
import org.eclipse.mylyn.targetprocess.modules.TargetProcessCredentials;
import org.eclipse.mylyn.targetprocess.modules.services.CommentServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.GeneralUserServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrieveTeamsForAssignable;
import org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.TeamDTO;
import org.eclipse.mylyn.targetprocess.modules.services.CommentServiceStub.Create;
import org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.EntityStateDTO;
import org.eclipse.mylyn.targetprocess.modules.services.GeneralUserServiceStub.ArrayOfAnyType;
import org.eclipse.mylyn.targetprocess.modules.services.GeneralUserServiceStub.GeneralUserDTO;
import org.eclipse.mylyn.targetprocess.modules.services.GeneralUserServiceStub.Retrieve;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.ArrayOfEntityStateDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AssignableToDoDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AttachmentDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.CommentDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.MyAssignmentsToDo;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.UserDTO;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IAssignableServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.ICommentServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IEntityStateServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IFileServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IGeneralUserServiceStub;
import org.eclipse.mylyn.tasks.core.IRepositoryPerson;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskCommentMapper;
import org.eclipse.mylyn.tasks.core.data.TaskData;

public abstract class PerformerBase implements IPerformer {

	protected IServiceFactory serviceFactory;
	protected TargetProcessCredentials targetProcessCredentials;
	protected IGeneralUserServiceStub generalUserService;
	protected EntityStateManager entityStateManager;
	protected IEntityStateServiceStub entityStateServiceStub;
	protected IAssignableServiceStub assignableServiceStub;
	protected IFileServiceStub fileServiceStub;
	protected ICommentServiceStub commentServiceStub;
	private ArrayList<TargetProcessAttribute> editableAttributes;

	public GeneralUserDTO getUserByLoginName(String loginName) throws RemoteException {
		Retrieve retrieve12 = new Retrieve();
		
		retrieve12.setHql("select user from User as user where (user.Login = '" + loginName + 
					"' or user.ActiveDirectoryName = '" + loginName + "') and user.DeleteDate is null");

        ArrayOfAnyType param = new ArrayOfAnyType();
		retrieve12.setParameters(param);
		
		GeneralUserDTO[] users = generalUserService.retrieve(retrieve12).getRetrieveResult().getGeneralUserDTO();
		if (users.length != 0) {
			return users[0];
		}

		return null;
	}

	public PerformerBase(IServiceFactory serviceFactory, TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		this.serviceFactory = serviceFactory;
		this.targetProcessCredentials = targetProcessCredentials;

		initServices();
		initEditableAttributes();
	}

	protected void initServices() throws RemoteException {
		generalUserService = serviceFactory.getGeneralUserServiceStub(targetProcessCredentials);
		entityStateServiceStub = serviceFactory.getEntityStateServiceStub(targetProcessCredentials);
		entityStateManager = new EntityStateManager(entityStateServiceStub, targetProcessCredentials);
		assignableServiceStub = serviceFactory.getAssignableServiceStub(targetProcessCredentials);
		fileServiceStub = serviceFactory.getFileServiceStub(targetProcessCredentials);
		commentServiceStub = serviceFactory.getCommentServiceStub(targetProcessCredentials);
	}

	protected abstract String getEmptyNameMessage();

	protected void validateFields(TaskData taskData) throws EntityValidationException {
		String entityName = getAttributeValue(taskData, TargetProcessAttribute.Name);
		if (stringIsNullOrEmpty(entityName)) {
			throw new EntityValidationException(getEmptyNameMessage());
		}
	}

	public TaskData createTaskData(int entityID, TaskRepository taskRepository) {
		TaskData taskData = new TaskData(new TargetProcessAttributeMapper(taskRepository), taskRepository
				.getConnectorKind(), taskRepository.getRepositoryUrl(), Integer.toString(entityID));

		addAttributeTo(taskData, TargetProcessAttribute.TaskKey, String.format("#%s", entityID));
		addAttributeTo(taskData, TargetProcessAttribute.NewComment, "");

		return taskData;
	}

	protected TaskData createTaskData(AssignableToDoDTO assignable, TaskRepository taskRepository) {
		TaskData taskData = new TaskData(new TargetProcessAttributeMapper(taskRepository), taskRepository
				.getConnectorKind(), taskRepository.getRepositoryUrl(), Integer.toString(assignable.getID()));

		addAttributeTo(taskData, TargetProcessAttribute.TaskKey, String.format("#%s", assignable.getID()));
		addAttributeTo(taskData, TargetProcessAttribute.NewComment, "");

		addAttributeTo(taskData, TargetProcessAttribute.EntityKind, getEntityKind().toString());

		return taskData;
	}

	protected void createNewComment(TaskData taskData, int entityId) throws RemoteException {
		TaskAttribute commentNewAttribute = taskData.getRoot().getMappedAttribute(TaskAttribute.COMMENT_NEW);

		if (commentNewAttribute != null) {

			String comment = commentNewAttribute.getValue().trim();
			comment = convertToHTML(comment);
			if (comment.length() != 0) {
				CommentServiceStub.CommentDTO commentDto = new CommentServiceStub.CommentDTO();
				GeneralUserDTO userDto = getUserByLoginName(taskData.getAttributeMapper().getTaskRepository()
						.getUserName());
				commentDto.setOwnerID(userDto.getUserID());
				commentDto.setDescription(comment);
				commentDto.setGeneralID(entityId);
				commentDto.setCreateDate(Calendar.getInstance());
				commentDto.setParentID(java.lang.Integer.MIN_VALUE);
				commentDto.setID(java.lang.Integer.MIN_VALUE);

				Create create14 = new Create();
				create14.setEntity(commentDto);
				commentServiceStub.create(create14);
			}
		}
	}

	protected void addStateAttribute(TaskData taskData, int entityStateID, String entityStateName) {
		TaskAttribute stateAttribute = taskData.getRoot().createAttribute(TargetProcessAttribute.State.getKey());
		stateAttribute.getMetaData().defaults().setReadOnly(TargetProcessAttribute.State.isReadOnly())
		// .setKind(TaskAttribute.STATUS)
				.setLabel(TargetProcessAttribute.State.toString()).setType(TargetProcessAttribute.State.getType());

		for (EntityStateDTO entityStateDTO : entityStateManager.retrieveEntityStatesFor(entityStateID)) {
			stateAttribute.putOption(String.valueOf(entityStateDTO.getID()), entityStateDTO.getName());
		}

		stateAttribute.setValue(String.valueOf(entityStateID));
	}

	public void addStateAttribute(TaskData taskData, int entityStateID, String entityStateName,
			ArrayOfEntityStateDTO entityStates) {
		TaskAttribute stateAttribute = taskData.getRoot().createAttribute(TargetProcessAttribute.State.getKey());
		stateAttribute.getMetaData().defaults().setReadOnly(TargetProcessAttribute.State.isReadOnly()).setLabel(
				TargetProcessAttribute.State.toString()).setType(TargetProcessAttribute.State.getType());

		stateAttribute.putOption(String.valueOf(entityStateID), entityStateName);

		if (entityStates.getEntityStateDTO() != null) {
			MyAssignmentsServiceStub.EntityStateDTO currentState = findEntity(entityStateID, entityStates
					.getEntityStateDTO());
			if (currentState != null) {
				if (currentState.getNextStates() != null && currentState.getNextStates().trim().length() > 0) {

					String[] ids = currentState.getNextStates().split(",");
					for (String stringId : ids) {
						MyAssignmentsServiceStub.EntityStateDTO nextState = findEntity(Integer.parseInt(stringId),
								entityStates.getEntityStateDTO());
						stateAttribute.putOption(stringId, nextState.getName());
					}
				}
			}
		}

		stateAttribute.setValue(String.valueOf(entityStateID));
	}

	public MyAssignmentsServiceStub.EntityStateDTO findEntity(int entityId,
			MyAssignmentsServiceStub.EntityStateDTO[] entityStateDTOs) {
		for (MyAssignmentsServiceStub.EntityStateDTO state : entityStateDTOs) {
			if (state.getEntityStateID() == entityId) {
				return state;
			}
		}
		return null;
	}

	protected void addTimeAttribute(TaskData taskData, TargetProcessAttribute attributeName, BigDecimal time) {
		String timeString = time == null ? "N/A" : String.format("%.1f h", time.floatValue()).replace(",", ".")
				.replace(".0", "");
		addAttributeTo(taskData, attributeName, timeString);
	}

	protected void addAssignableUsersAttribute(TaskData taskData, int entityId) throws RemoteException {
		RetrieveTeamsForAssignable retrieveTeamsForAssignable = new RetrieveTeamsForAssignable();
		retrieveTeamsForAssignable.setAssignableID(entityId);
		TeamDTO[] teams = assignableServiceStub.retrieveTeamsForAssignable(retrieveTeamsForAssignable)
				.getRetrieveTeamsForAssignableResult().getTeamDTO();

		HashMap<String, String> assigmentsUsers = new HashMap<String, String>();
		if (teams != null) {

			for (TeamDTO teamDTO : teams) {
				int userID = teamDTO.getUserID();
				GeneralUserDTO userDTO = getGeneralUser(userID);
				if (assigmentsUsers.containsKey(getShortActorName(teamDTO))) {
					assigmentsUsers.put(getShortActorName(teamDTO), String.format("%s | %s", assigmentsUsers
							.get(getShortActorName(teamDTO)), getShortName(userDTO)));
				} else {
					assigmentsUsers.put(getShortActorName(teamDTO), getShortName(userDTO));
				}
			}
		}

		String[] keys = new String[assigmentsUsers.keySet().size()];
		assigmentsUsers.keySet().toArray(keys);

		if (keys.length == 0) {
			addAttributeTo(taskData, TargetProcessAttribute.AssignedPeople, "Unassigned");
		} else {
			Arrays.sort(keys, new Comparator<String>() {
				public int compare(String s1, String s2) {
					return s1.compareTo(s2);
				}
			});

			addAttributeTo(taskData, TargetProcessAttribute.AssignedPeople, getUserNameWithRole(assigmentsUsers,
					keys[0]));
			if (keys.length > 1) {
				addAttributeTo(taskData, TargetProcessAttribute.AssignedPeople2, getUserNameWithRole(assigmentsUsers,
						keys[1]));
			}

		}
	}

	private String getShortActorName(TeamDTO teamDTO) {
		if (teamDTO.getRoleName() == null || teamDTO.getRoleName().length() < 3) {
			return teamDTO.getRoleName();
		}
		return teamDTO.getRoleName().substring(0, 3) + ".";
	}

	protected String getUserNameWithRole(HashMap<String, String> assigmentsUsers, String key) {
		return String.format("%s %s", key, assigmentsUsers.get(key));
	}

	protected String getShortName(GeneralUserDTO userDTO) {
		return String.format("%s %s.", userDTO.getLastName(), userDTO.getFirstName().trim().charAt(0));
	}

	protected String getShortName(UserDTO userDTO) {
		return String.format("%s %s.", userDTO.getLastName(), userDTO.getFirstName().trim().charAt(0));
	}

	protected void addOwnerAttribute(TaskData taskData, GeneralUserDTO ownerDto) {
		addAttributeTo(taskData, TargetProcessAttribute.Owner, getGeneralUserFullName(ownerDto));
	}

	protected void addOwnerAttribute(TaskData taskData, int ownerID) throws RemoteException {
		GeneralUserDTO generalUser = getGeneralUser(ownerID);
		addAttributeTo(taskData, TargetProcessAttribute.Owner, getGeneralUserFullName(generalUser));
	}

	protected void addOwnerAttribute(TaskData taskData, UserDTO ownerDto) {
		addAttributeTo(taskData, TargetProcessAttribute.Owner, getUserFullName(ownerDto));
	}

	protected String getUserFullName(UserDTO ownerDto) {
		return String.format("%1$s %2$s", ownerDto.getFirstName(), ownerDto.getLastName());
	}

	protected String getGeneralUserFullName(GeneralUserDTO generalUser) {
		return String.format("%1$s %2$s", generalUser.getFirstName(), generalUser.getLastName());
	}

	protected GeneralUserDTO getGeneralUser(int ownerID) throws RemoteException {
		GeneralUserServiceStub.GetByID getById = new GeneralUserServiceStub.GetByID();
		getById.setId(ownerID);
		GeneralUserDTO generalUser = generalUserService.getByID(getById).getGetByIDResult();
		return generalUser;
	}

	protected void addAttributeTo(TaskData taskData, TargetProcessAttribute targetProcessAttribute, String value) {
		TaskAttribute taskAttribute = taskData.getRoot().createAttribute(targetProcessAttribute.getKey());
		taskAttribute.getMetaData().defaults().setReadOnly(targetProcessAttribute.isReadOnly()).setLabel(
				targetProcessAttribute.toString()).setType(targetProcessAttribute.getType()).setReadOnly(
				targetProcessAttribute.isReadOnly());
		if (value != null) {
			taskAttribute.setValue(value);
		}
	}

	protected String getAttributeValue(TaskData taskData, TargetProcessAttribute targetProcessAttribute) {
		return taskData.getRoot().getAttribute(targetProcessAttribute.getKey()).getValue();
	}

	protected TaskAttribute getAttribute(TaskData taskData, TargetProcessAttribute targetProcessAttribute) {
		return taskData.getRoot().getAttribute(targetProcessAttribute.getKey());
	}

	protected void addDateAttribute(TaskData taskData, TargetProcessAttribute targetProcessAttribute, Calendar date) {
		if (date != null) {
			addAttributeTo(taskData, targetProcessAttribute, convertCalendarToString(date));
		}
	}

	protected String convertCalendarToString(Calendar date) {
		return Long.toString(date.getTime().getTime());
	}

	protected String getEntityKind(TaskData taskData) {
		return taskData.getRoot().getAttribute(TargetProcessAttribute.EntityKind.getKey()).getValue();
	}

	public boolean isSupportTaskData(TaskData taskData) {
		return getEntityKind(taskData).equals(getEntityKind().getName());
	}

	protected void addDescriptionAttribute(TaskData taskData, String description) {
		addAttributeTo(taskData, TargetProcessAttribute.Description, addRepositoryUrlToImg(taskData, description));
	}

	public static String addRepositoryUrlToImg(TaskData taskData, String description) {
		if (description != null) {
			return description.replaceAll("src=\"&#126;&#47;", "src=\"" + taskData.getRepositoryUrl() + "/")
					.replaceAll("src='&#126;&#47;", "src='" + taskData.getRepositoryUrl() + "/").replaceAll("src='~/",
							"src='" + taskData.getRepositoryUrl() + "/").replaceAll("src=\"~/",
							"src=\"" + taskData.getRepositoryUrl() + "/");
		}
		return description;
	}

	public static String removeRepositoryUrlFromImg(TaskData taskData, String description) {
		if (description != null) {
			return description.replaceAll("src=\"" + taskData.getRepositoryUrl() + "/", "src=\"&#126;&#47;")
					.replaceAll("src='" + taskData.getRepositoryUrl() + "/", "src='&#126;&#47;");
		}
		return description;
	}

	public static String removeHTML(String htmlString) {
		if (htmlString == null) {
			return "";
		}

		String noHTMLString = htmlString.replace("\n", "").replace("\r", "").replaceAll("\\</\\s*[l|L][i|I]>", "\n")
				.replaceAll("\\<[b|B][r|R]\\s*?/?>", "\n").replaceAll("\\<.*?\\>", "");

		noHTMLString = noHTMLString.replaceAll("\'", "&#39;");
		noHTMLString = noHTMLString.replaceAll("\"", "&quot;");

		noHTMLString = StringEscapeUtils.unescapeHtml(noHTMLString);
		return noHTMLString;
	}

	protected static boolean stringIsNullOrEmpty(String value) {
		return value == null || value.trim().length() == 0;
	}

	protected String getDescriptionAttributeValue(TaskData taskData) {
		return removeRepositoryUrlFromImg(taskData, getAttributeValue(taskData, TargetProcessAttribute.Description));
	}

	private String convertToHTML(String value) {
		return value.replace("\n", "<br />").replace("\r", "");
	}

	@Override
	public TaskData createTaskDataBy(AssignableToDoDTO taskDTO, MyAssignmentsToDo toDoList,
			TaskRepository taskRepository) {
		TaskData taskData = createTaskData(taskDTO, taskRepository);
		addAttributeTo(taskData, TargetProcessAttribute.Name, taskDTO.getName());
		addDescriptionAttribute(taskData, taskDTO.getDescription());
		addDateAttribute(taskData, TargetProcessAttribute.CreationDate, taskDTO.getCreateDate());
		addStateAttribute(taskData, taskDTO.getEntityStateID(), taskDTO.getEntityStateName(), toDoList
				.getEntityStates());

		addAttributeTo(taskData, TargetProcessAttribute.ProjectName, taskDTO.getProjectName());
		addOwnerAttribute(taskData, taskDTO, toDoList);
		addAssignableUsersAttribute(taskData, taskDTO, toDoList);

		addAttributeTo(taskData, TargetProcessAttribute.Priority, taskDTO.getPriorityName());

		addTimeAttribute(taskData, TargetProcessAttribute.TimeSpent, taskDTO.getTimeSpent());
		addTimeAttribute(taskData, TargetProcessAttribute.TimeRemaining, taskDTO.getTimeRemain());

		addModificationDateAttribute(taskDTO, taskData);

		addDateAttribute(taskData, TargetProcessAttribute.EndDate, taskDTO.getEndDate());

		addAttributeTo(taskData, TargetProcessAttribute.Priority, taskDTO.getPriorityName());
		addModificationDateAttribute(taskDTO, taskData);
		addCommentsAndAttachments(taskData, taskDTO, toDoList);
		return taskData;
	}

	private void addModificationDateAttribute(AssignableToDoDTO taskDTO, TaskData taskData) {
		Calendar modifyDate = taskDTO.getModifyDate();

		if (taskDTO.getAttachmentsCollection() != null && taskDTO.getAttachmentsCollection().getAttachmentDTO() != null) {
			for (AttachmentDTO attachment : taskDTO.getAttachmentsCollection().getAttachmentDTO()) {
				if (attachment.getCreateDate().after(modifyDate)) {
					modifyDate = attachment.getCreateDate();
				}
			}
		}

		if (taskDTO.getCommentsCollection() != null && taskDTO.getCommentsCollection().getCommentDTO() != null) {
			for (CommentDTO comment : taskDTO.getCommentsCollection().getCommentDTO()) {
				if (comment.getCreateDate().after(modifyDate)) {
					modifyDate = comment.getCreateDate();
				}
			}
		}
		addDateAttribute(taskData, TargetProcessAttribute.DateModification, modifyDate);

	}

	protected void addCommentsTo(TaskData taskData, int entityId) throws RemoteException {

		ArrayList<Comment> comments = getComments(entityId);
		int commentNum = 1;
		for (Comment commentDTO : comments) {
			TaskAttribute attribute = taskData.getRoot().createAttribute(TaskAttribute.PREFIX_COMMENT + commentNum);
			TaskCommentMapper taskComment = TaskCommentMapper.createFrom(attribute);
			taskComment.setCommentId(commentNum + ""); //$NON-NLS-1$
			taskComment.setNumber(commentNum);

			GeneralUserDTO commentOwner = getGeneralUser(commentDTO.getOwnerID());
			IRepositoryPerson author = taskData.getAttributeMapper().getTaskRepository().createPerson(
					String.valueOf(commentOwner.getLogin()));

			author.setName(getGeneralUserFullName(commentOwner));
			taskComment.setAuthor(author);

			taskComment.setCreationDate(commentDTO.getCreateDate().getTime());
			if (commentDTO.getDescription() != null) {
				String commentText = commentDTO.getDescription().trim();
				taskComment.setText(removeHTML(commentText));

			}
			taskComment.applyTo(attribute);
			commentNum++;
		}

	}

	protected void addCommentsAndAttachments(TaskData taskData, int entityId) throws RemoteException {
		addCommentsTo(taskData, entityId);
		addAttachmentsTo(taskData, entityId);
	}

	protected void addAttachmentsTo(TaskData taskData, int entityId) throws RemoteException {
		ArrayList<Attachment> attachments = getAttachments(entityId);
		int attachmentNum = 1;
		for (Attachment attachment : attachments) {
			TaskAttribute attribute = taskData.getRoot().createAttribute(
					TaskAttribute.PREFIX_ATTACHMENT + attachmentNum);
			TargetProcessAttachmentMapper attachmentMapper = TargetProcessAttachmentMapper.createFrom(attribute);
			attachmentMapper.setLength(attachment.getFileSize());
			attachmentMapper.setAttachmentId(attachment.getUniqueFileName());
			attachmentMapper.setPatch(false);
			attachmentMapper.setDeprecated(false);
			attachmentMapper.setToken(null);
			attachmentMapper.setCreationDate(attachment.getCreationDate());
			attachmentMapper.setDescription(attachment.getDescription());
			attachmentMapper.setFileName(attachment.getOriginalFileName());
			attachmentMapper.setUrl(getAttachmentUrl(attachment.getID()));

			IRepositoryPerson author = taskData.getAttributeMapper().getTaskRepository().createPerson(
					getGeneralUserFullName(getGeneralUser(attachment.getOwnerId())));

			attachmentMapper.setAuthor(author);

			attachmentMapper.applyTo(attribute);
			attachmentNum++;
		}
	}

	protected String getAttachmentUrl(int attachmentID) {
		return targetProcessCredentials.getRepositoryUrl().toString() + "/Attachment.aspx?AttachmentID=" + attachmentID;
	}

	private void initEditableAttributes() {
		editableAttributes = new ArrayList<TargetProcessAttribute>();
		editableAttributes.add(TargetProcessAttribute.Name);
		editableAttributes.add(TargetProcessAttribute.Description);
		editableAttributes.add(TargetProcessAttribute.State);
	}

	protected boolean taskHasChanged(TaskData taskData, Set<TaskAttribute> changedAttributes) {
		if (changedAttributes != null) {
			for (TargetProcessAttribute editableAttribute : editableAttributes) {
				if (attributeHasChanged(taskData, changedAttributes, editableAttribute)) {
					return true;
				}
			}
		}
		return false;
	}

	protected boolean attributeHasChanged(TaskData taskData, Set<TaskAttribute> changedAttributes,
			TargetProcessAttribute targetProcessAttribute) {
		if (changedAttributes != null) {
			TaskAttribute attribute = getAttribute(taskData, targetProcessAttribute);
			if (changedAttributes.contains(attribute)) {
				return true;
			}
		}
		return false;
	}

	protected boolean preUpdate(TaskData taskData, Set<TaskAttribute> changedAttributes, int entityId)
			throws EntityValidationException, RemoteException {
		validateFields(taskData);
		createNewComment(taskData, entityId);

		if (!taskHasChanged(taskData, changedAttributes)) {
			return false;
		}

		return true;
	}

	protected void addCommentsAndAttachments(TaskData taskData, AssignableToDoDTO taskDTO, MyAssignmentsToDo toDoList) {
		addCommentsTo(taskData, taskDTO, toDoList);
		addAttachmentsTo(taskData, taskDTO, toDoList);
	}

	protected void addAttachmentsTo(TaskData taskData, AssignableToDoDTO taskDTO, MyAssignmentsToDo toDoList) {
		MyAssignmentsServiceStub.AttachmentDTO[] attachments = taskDTO.getAttachmentsCollection().getAttachmentDTO();
		if (attachments != null) {
			int attachmentNum = 1;
			for (MyAssignmentsServiceStub.AttachmentDTO attachment : attachments) {
				TaskAttribute attribute = taskData.getRoot().createAttribute(
						TaskAttribute.PREFIX_ATTACHMENT + attachmentNum);
				TargetProcessAttachmentMapper attachmentMapper = TargetProcessAttachmentMapper.createFrom(attribute);
				attachmentMapper.setLength(attachment.getFileSize());
				attachmentMapper.setAttachmentId(attachment.getUniqueFileName());
				attachmentMapper.setPatch(false);
				attachmentMapper.setDeprecated(false);
				attachmentMapper.setToken(null);
				attachmentMapper.setCreationDate(attachment.getCreateDate().getTime());
				attachmentMapper.setDescription(attachment.getDescription());
				attachmentMapper.setFileName(attachment.getOriginalFileName());
				attachmentMapper.setUrl(getAttachmentUrl(attachment.getID()));

				IRepositoryPerson author = taskData.getAttributeMapper().getTaskRepository().createPerson(
						getUserFullName(getUser(attachment.getOwnerID(), toDoList)));

				attachmentMapper.setAuthor(author);

				attachmentMapper.applyTo(attribute);
				attachmentNum++;
			}
		}

	}

	protected void addCommentsTo(TaskData taskData, AssignableToDoDTO taskDTO, MyAssignmentsToDo toDoList) {

		CommentDTO[] comments = taskDTO.getCommentsCollection().getCommentDTO();
		if (comments != null) {
			int commentNum = 1;
			for (CommentDTO commentDTO : comments) {
				TaskAttribute attribute = taskData.getRoot().createAttribute(TaskAttribute.PREFIX_COMMENT + commentNum);
				TaskCommentMapper taskComment = TaskCommentMapper.createFrom(attribute);
				taskComment.setCommentId(commentNum + ""); //$NON-NLS-1$
				taskComment.setNumber(commentNum);

				UserDTO commentOwner = getUser(commentDTO.getOwnerID(), toDoList);
				IRepositoryPerson author = taskData.getAttributeMapper().getTaskRepository().createPerson(
						String.valueOf(commentOwner.getLogin()));

				author.setName(getUserFullName(commentOwner));
				taskComment.setAuthor(author);

				taskComment.setCreationDate(commentDTO.getCreateDate().getTime());
				if (commentDTO.getDescription() != null) {
					String commentText = commentDTO.getDescription().trim();
					taskComment.setText(removeHTML(commentText));

				}
				taskComment.applyTo(attribute);
				commentNum++;
			}
		}

	}

	protected void addAssignableUsersAttribute(TaskData taskData, AssignableToDoDTO taskDTO, MyAssignmentsToDo toDoList) {

		MyAssignmentsServiceStub.TeamDTO[] teams = taskDTO.getTeamsCollection().getTeamDTO();

		HashMap<String, String> assigmentsUsers = new HashMap<String, String>();
		if (teams != null) {

			for (MyAssignmentsServiceStub.TeamDTO teamDTO : teams) {
				int userID = teamDTO.getUserID();
				UserDTO userDTO = getUser(userID, toDoList);
				if (assigmentsUsers.containsKey(getShortActorName(teamDTO))) {
					assigmentsUsers.put(getShortActorName(teamDTO), String.format("%s | %s", assigmentsUsers
							.get(getShortActorName(teamDTO)), getShortName(userDTO)));
				} else {
					assigmentsUsers.put(getShortActorName(teamDTO), getShortName(userDTO));
				}
			}
		}

		String[] keys = new String[assigmentsUsers.keySet().size()];
		assigmentsUsers.keySet().toArray(keys);

		if (keys.length == 0) {
			addAttributeTo(taskData, TargetProcessAttribute.AssignedPeople, "");
		} else {
			Arrays.sort(keys, new Comparator<String>() {
				public int compare(String s1, String s2) {
					return s1.compareTo(s2);
				}
			});

			addAttributeTo(taskData, TargetProcessAttribute.AssignedPeople, getUserNameWithRole(assigmentsUsers,
					keys[0]));
			if (keys.length > 1) {
				addAttributeTo(taskData, TargetProcessAttribute.AssignedPeople2, getUserNameWithRole(assigmentsUsers,
						keys[1]));
			}

		}
	}

	private String getShortActorName(MyAssignmentsServiceStub.TeamDTO teamDTO) {
		return teamDTO.getRoleName() == null || teamDTO.getRoleName().length() <= 3 ? teamDTO.getRoleName() : teamDTO
				.getRoleName().substring(0, 3)
				+ ".";
	}

	private UserDTO getUser(int userID, MyAssignmentsToDo toDoList) {
		for (UserDTO user : toDoList.getUsers().getUserDTO()) {
			if (user.getID() == userID) {
				return user;
			}
		}
		return null;
	}

	protected void addOwnerAttribute(TaskData taskData, AssignableToDoDTO taskDTO, MyAssignmentsToDo toDoList) {
		for (UserDTO user : toDoList.getUsers().getUserDTO()) {
			if (user.getID() == taskDTO.getOwnerID()) {
				addOwnerAttribute(taskData, user);
				return;
			}
		}
	}

}
