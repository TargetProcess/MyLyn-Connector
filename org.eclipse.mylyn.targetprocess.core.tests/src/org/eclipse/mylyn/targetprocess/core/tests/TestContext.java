package org.eclipse.mylyn.targetprocess.core.tests;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.eclipse.mylyn.targetprocess.core.entityperformer.EntityPerformerFactory;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.ArrayOfTeamDTO;
import org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.TeamDTO;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.AttachmentDTO;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.BugDTO;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.CommentDTO;
import org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.EntityStateDTO;
import org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.GeneralDTO;
import org.eclipse.mylyn.targetprocess.modules.services.GeneralUserServiceStub.GeneralUserDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.ArrayOfAssignableSimpleDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.ArrayOfAssignableToDoDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.ArrayOfAttachmentDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.ArrayOfCommentDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.ArrayOfEntityStateDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.ArrayOfUserDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AssignableSimpleDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AssignableToDoDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.MyAssignments;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.MyAssignmentsToDo;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.UserDTO;
import org.eclipse.mylyn.targetprocess.modules.services.PriorityServiceStub.PriorityDTO;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RequestDTO;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.TaskDTO;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.UserStoryDTO;

public class TestContext {

	private ArrayList<GeneralUserDTO> userList;
	private ArrayList<BugDTO> bugList;
	private HashMap<Integer, ArrayOfTeamDTO> assignableTeams;
	private ArrayList<UserStoryDTO> userStoryList;
	private ArrayList<GeneralDTO> generalDTOList;
	private ArrayList<TaskDTO> taskList;
	private ArrayList<PriorityDTO> priorityList;
	private ArrayList<RequestDTO> requestList;
	private ArrayList<EntityStateDTO> entityStateList;
	private ArrayList<CommentDTO> bugCommentsList;
	private ArrayList<TaskServiceStub.CommentDTO> taskCommentsList;
	private ArrayList<RequestServiceStub.CommentDTO> requestCommentsList;
	private ArrayList<UserStoryServiceStub.CommentDTO> userStoryCommentsList;

	private ArrayList<BugServiceStub.AttachmentDTO> bugAttachmentsList;
	private ArrayList<TaskServiceStub.AttachmentDTO> taskAttachmentsList;
	private ArrayList<RequestServiceStub.AttachmentDTO> requestAttachmentsList;
	private ArrayList<UserStoryServiceStub.AttachmentDTO> userStoryAttachmentsList;

	private int id = 0;
	private MyAssignments myAssignmentsList;
	private MyAssignmentsToDo myAssignmentsToDo;
	private Calendar attachmentCreationDate;
	private EntityStateDTO closedBugState;
	private GeneralUserDTO userDTO;
	private GeneralUserDTO sergeyDTO;
	private GeneralUserDTO linichDTO;

	protected int genId() {
		return id++;
	}

	public TestContext() {

		initLists();

		addBugPriority(new String("Very Important"), 0);
		addBugPriority(new String("Fix ASAP"), 1);
		addBugPriority(new String("Fix If Time"), 2);

		addUserStoryPriority(new String("Must Have"), 1);
		addUserStoryPriority(new String("Great"), 2);
		addUserStoryPriority(new String("Good"), 3);
		addUserStoryPriority(new String("Average"), 4);
		addUserStoryPriority(new String("Nice To Have"), 5);
		addUserStoryPriority(new String("Some minor"), 6);

		PriorityDTO urgentPriority = addRequestPriority(new String("Urgent"), 1);
		addRequestPriority(new String("Normal"), 2);

		String process = "All Practices";
		EntityStateDTO openUserStoryState = addEnetityState("Open",
				EntityPerformerFactory.TP_BUSINESS_OBJECTS_USER_STORY, true, process);
		EntityStateDTO doneUserStoryState = addEnetityState("Done",
				EntityPerformerFactory.TP_BUSINESS_OBJECTS_USER_STORY, false, process);
		openUserStoryState.setNextStates(String.valueOf(doneUserStoryState.getID()));
		doneUserStoryState.setNextStates(String.valueOf(openUserStoryState.getID()));

		EntityStateDTO openTaskState = addEnetityState("Open", EntityPerformerFactory.TP_BUSINESS_OBJECTS_TASK, true,
				process);

		EntityStateDTO openBugState = addEnetityState("Open", EntityPerformerFactory.TP_BUSINESS_OBJECTS_BUG, true,
				process);
		EntityStateDTO doneBugState = addEnetityState("Done", EntityPerformerFactory.TP_BUSINESS_OBJECTS_BUG, false,
				process);

		doneBugState.setFinal(true);

		closedBugState = addEnetityState("Closed", EntityPerformerFactory.TP_BUSINESS_OBJECTS_BUG, false, process);
		closedBugState.setFinal(true);
		openBugState.setNextStates(String.valueOf(doneBugState.getID()));
		doneBugState.setNextStates(String.valueOf(closedBugState.getID()));
		closedBugState.setNextStates(String.valueOf(openBugState.getID()) + "," + String.valueOf(doneBugState.getID()));

		EntityStateDTO openRequestState = addEnetityState("Open", EntityPerformerFactory.TP_BUSINESS_OBJECTS_REQUEST,
				true, process);

		userDTO = AddUser("Vasya", "Pupkin");
		sergeyDTO = AddUser("Sergey", "Truhtanov");
		linichDTO = AddUser("Maksim", "Linich");

		BugDTO bugDTO = addBug(userDTO, openBugState);
		addGeneralDTO(bugDTO.getID());

		UserStoryDTO userStoryDTO = addUserStory(userDTO, doneUserStoryState);
		TaskDTO taskDTO = addTask(userDTO, userStoryDTO, openTaskState);
		RequestDTO request = addRequest("New Request", urgentPriority, "Description<br /> of request", userDTO,
				openRequestState);

		addCommentToBug(bugDTO.getBugID(), sergeyDTO.getID(), "descr 1");
		addCommentToBug(bugDTO.getBugID(), linichDTO.getID(), "descr 2");

		addCommentToTask(taskDTO.getTaskID(), sergeyDTO.getID(), "Task comment descr 1");
		addCommentToTask(taskDTO.getTaskID(), linichDTO.getID(), "Task comment descr 2");

		addCommentToUserStory(userStoryDTO.getUserStoryID(), linichDTO.getID(), "User story comment descr 1");
		addCommentToUserStory(userStoryDTO.getUserStoryID(), linichDTO.getID(), "User story comment descr 2");

		addCommentToRequest(request.getRequestID(), linichDTO.getID(), "Request comment descr 1");
		addCommentToRequest(request.getRequestID(), linichDTO.getID(), "Request comment descr 2");

		setAttachmentCreationDate(Calendar.getInstance());

		addAttachToBug(bugDTO, sergeyDTO, "bug attach description", getAttachmentCreationDate(), "BugFileName");

		addAttachToUserStory(userStoryDTO, sergeyDTO, "user story attach description", getAttachmentCreationDate(),
				"UserStoryFileName");

		addAttachToTask(taskDTO, sergeyDTO, "task attach description", getAttachmentCreationDate(), "taskFileName");

		addAttachToRequest(request, sergeyDTO, "task attach description", getAttachmentCreationDate(), "taskFileName");

		ArrayOfTeamDTO teams = new ArrayOfTeamDTO();

		TeamDTO teamDTO = new TeamDTO();
		teamDTO.setID(genId());
		teamDTO.setTeamID(teamDTO.getID());
		teamDTO.setRoleName("DEVELOPER");
		teamDTO.setUserID(sergeyDTO.getUserID());
		teams.addTeamDTO(teamDTO);

		teamDTO = new TeamDTO();
		teamDTO.setUserID(linichDTO.getUserID());
		teamDTO.setRoleName("developer");
		teams.addTeamDTO(teamDTO);

		assignableTeams.put(bugDTO.getBugID(), teams);
		assignableTeams.put(userStoryDTO.getUserStoryID(), teams);
		assignableTeams.put(taskDTO.getTaskID(), teams);
		assignableTeams.put(request.getID(), teams);

		myAssignmentsList = InitMyAssignments();
		myAssignmentsToDo = InitMyAssignmentsToDo();
	}

	public BugDTO addClosedBug() {
		BugDTO colosedBug = addBug(userDTO, closedBugState);
		colosedBug.setEndDate(Calendar.getInstance());
		ArrayOfTeamDTO teams = new ArrayOfTeamDTO();
		
		TeamDTO teamDTO = new TeamDTO();
		teamDTO.setID(genId());
		teamDTO.setTeamID(teamDTO.getID());
		teamDTO.setRoleName("DEVELOPER");
		teamDTO.setUserID(sergeyDTO.getUserID());
		teams.addTeamDTO(teamDTO);

		teamDTO = new TeamDTO();
		teamDTO.setUserID(linichDTO.getUserID());
		teamDTO.setRoleName("developer");
		teams.addTeamDTO(teamDTO);

		assignableTeams.put(colosedBug.getBugID(), teams);		
		
		return colosedBug;
	}

	private MyAssignmentsToDo InitMyAssignmentsToDo() {
		MyAssignmentsToDo todo = new MyAssignmentsToDo();
		ArrayOfAssignableToDoDTO assignables = new ArrayOfAssignableToDoDTO();
		todo.setAssignableEntities(assignables);

		for (AssignableSimpleDTO assignable : myAssignmentsList.getAssignables().getAssignableSimpleDTO()) {
			AssignableToDoDTO assignableToDoDto = null;
			if (assignable.getEntityTypeName().equals(EntityPerformerFactory.TP_BUSINESS_OBJECTS_BUG)) {
				assignableToDoDto = createAssignableToDoDTOFromBug(assignable, todo);
			} else if (assignable.getEntityTypeName().equals(EntityPerformerFactory.TP_BUSINESS_OBJECTS_REQUEST)) {
				assignableToDoDto = createAssignableToDoDTOFromRequest(assignable, todo);
			} else if (assignable.getEntityTypeName().equals(EntityPerformerFactory.TP_BUSINESS_OBJECTS_USER_STORY)) {
				assignableToDoDto = createAssignableToDoDTOFromUserStory(assignable, todo);
			} else if (assignable.getEntityTypeName().equals(EntityPerformerFactory.TP_BUSINESS_OBJECTS_TASK)) {
				assignableToDoDto = createAssignableToDoDTOFromTask(assignable, todo);
			}
			assignableToDoDto.setEntityTypeName(assignable.getEntityTypeName());
			assignables.addAssignableToDoDTO(assignableToDoDto);
			addUser(todo, assignableToDoDto.getOwnerID());
		}

		return todo;
	}

	public MyAssignmentsToDo CreateMyAssignmentsToDoFromBug(BugDTO bugDTO) {
		MyAssignmentsToDo todo = new MyAssignmentsToDo();
		ArrayOfAssignableToDoDTO assignables = new ArrayOfAssignableToDoDTO();
		todo.setAssignableEntities(assignables);

		AssignableSimpleDTO assignable = new AssignableSimpleDTO();
		assignable.setEntityTypeName(bugDTO.getEntityTypeName());
		assignable.setID(bugDTO.getID());
		assignable.setEntityStateName(bugDTO.getEntityStateName());
		assignable.setEntityStateID(bugDTO.getEntityStateID());
		assignable.setProjectID(bugDTO.getPriorityID());
		assignable.setProjectName(bugDTO.getProjectName());
		assignable.setSeverityID(bugDTO.getSeverityID());
		assignable.setSeverityName(bugDTO.getSeverityName());
		assignable.setTimeRemain(bugDTO.getTimeRemain());
		assignable.setTimeSpent(bugDTO.getTimeSpent());
		AssignableToDoDTO assignableToDoDto = null;
		// if
		// (assignable.getEntityTypeName().equals(EntityPerformerFactory.TP_BUSINESS_OBJECTS_BUG))
		// {
		assignableToDoDto = createAssignableToDoDTOFromBug(assignable, todo);
		if (this.getEntityStateById(bugDTO.getEntityStateID()).getFinal())
		{
			assignableToDoDto.setEndDate(Calendar.getInstance());
		}

		assignableToDoDto.setEntityTypeName(assignable.getEntityTypeName());
		assignables.addAssignableToDoDTO(assignableToDoDto);
		addUser(todo, assignableToDoDto.getOwnerID());

		return todo;
	}

	private AssignableToDoDTO createAssignableToDoDTOFromUserStory(AssignableSimpleDTO assignable,
			MyAssignmentsToDo todo) {

		AssignableToDoDTO assignableToDo = new AssignableToDoDTO();
		UserStoryDTO userStoryDto = getUserStoryById(assignable.getID());
		assignableToDo.setEntityTypeName(assignable.getEntityTypeName());
		assignableToDo.setName(userStoryDto.getName());
		assignableToDo.setDescription(userStoryDto.getDescription());
		assignableToDo.setPriorityName(userStoryDto.getPriorityName());
		assignableToDo.setProjectName(userStoryDto.getProjectName());
		assignableToDo.setTimeRemain(userStoryDto.getTimeRemain());
		assignableToDo.setTimeSpent(userStoryDto.getTimeSpent());
		assignableToDo.setID(userStoryDto.getUserStoryID());
		assignableToDo.setEntityStateName(userStoryDto.getEntityStateName());
		assignableToDo.setEntityStateID(userStoryDto.getEntityStateID());
		assignableToDo.setOwnerID(userStoryDto.getOwnerID());

		assignableToDo.setTeamsCollection(getTeamsForEntity(userStoryDto.getUserStoryID(), todo));
		assignableToDo.setCommentsCollection(getCommentsForUserStory(userStoryDto, todo));
		assignableToDo.setAttachmentsCollection(getAttachmentsForUserStory(userStoryDto, todo));

		addEntityStates(userStoryDto.getEntityStateID(), todo);

		return assignableToDo;
	}

	private AssignableToDoDTO createAssignableToDoDTOFromRequest(AssignableSimpleDTO assignable, MyAssignmentsToDo todo) {
		AssignableToDoDTO assignableToDo = new AssignableToDoDTO();
		RequestDTO requestDto = getRequestById(assignable.getID());
		assignableToDo.setEntityTypeName(assignable.getEntityTypeName());
		assignableToDo.setName(requestDto.getName());
		assignableToDo.setDescription(requestDto.getDescription());
		assignableToDo.setPriorityName(requestDto.getPriorityName());
		assignableToDo.setProjectName(requestDto.getProjectName());
		assignableToDo.setTimeRemain(requestDto.getTimeRemain());
		assignableToDo.setTimeSpent(requestDto.getTimeSpent());
		assignableToDo.setID(requestDto.getRequestID());
		assignableToDo.setEntityStateName(requestDto.getEntityStateName());
		assignableToDo.setEntityStateID(requestDto.getEntityStateID());
		assignableToDo.setOwnerID(requestDto.getOwnerID());

		assignableToDo.setTeamsCollection(getTeamsForEntity(requestDto.getRequestID(), todo));
		assignableToDo.setCommentsCollection(getCommentsForRequest(requestDto, todo));
		assignableToDo.setAttachmentsCollection(getAttachmentsForRequest(requestDto, todo));

		addEntityStates(requestDto.getEntityStateID(), todo);

		return assignableToDo;
	}

	private AssignableToDoDTO createAssignableToDoDTOFromTask(AssignableSimpleDTO assignable, MyAssignmentsToDo todo) {
		AssignableToDoDTO assignableToDo = new AssignableToDoDTO();
		TaskDTO taskDto = getTaskById(assignable.getID());
		assignableToDo.setEntityTypeName(assignable.getEntityTypeName());
		assignableToDo.setName(taskDto.getName());
		assignableToDo.setDescription(taskDto.getDescription());
		assignableToDo.setPriorityName(taskDto.getPriorityName());
		assignableToDo.setProjectName(taskDto.getProjectName());
		assignableToDo.setTimeRemain(taskDto.getTimeRemain());
		assignableToDo.setTimeSpent(taskDto.getTimeSpent());
		assignableToDo.setID(taskDto.getTaskID());
		assignableToDo.setEntityStateName(taskDto.getEntityStateName());
		assignableToDo.setEntityStateID(taskDto.getEntityStateID());
		assignableToDo.setOwnerID(taskDto.getOwnerID());

		assignableToDo.setTeamsCollection(getTeamsForEntity(taskDto.getTaskID(), todo));
		assignableToDo.setCommentsCollection(getCommentsForTask(taskDto, todo));
		assignableToDo.setAttachmentsCollection(getAttachmentsForTask(taskDto, todo));

		addEntityStates(taskDto.getEntityStateID(), todo);

		return assignableToDo;
	}

	private AssignableToDoDTO createAssignableToDoDTOFromBug(AssignableSimpleDTO assignable, MyAssignmentsToDo todo) {
		AssignableToDoDTO assignableToDo = new AssignableToDoDTO();
		BugDTO bugDto = getBugById(assignable.getID());

		assignableToDo.setName(bugDto.getName());
		assignableToDo.setDescription(bugDto.getDescription());
		assignableToDo.setPriorityName(bugDto.getPriorityName());
		assignableToDo.setProjectName(bugDto.getProjectName());
		assignableToDo.setTimeRemain(bugDto.getTimeRemain());
		assignableToDo.setTimeSpent(bugDto.getTimeSpent());
		assignableToDo.setID(bugDto.getBugID());
		assignableToDo.setEntityStateName(bugDto.getEntityStateName());
		assignableToDo.setEntityStateID(bugDto.getEntityStateID());
		assignableToDo.setOwnerID(bugDto.getOwnerID());
		assignableToDo.setSeverityName(bugDto.getSeverityName());
		assignableToDo.setUserStoryName(bugDto.getUserStoryName());

		assignableToDo.setTeamsCollection(getTeamsForEntity(bugDto.getBugID(), todo));
		assignableToDo.setCommentsCollection(getCommentsForBug(bugDto, todo));
		assignableToDo.setAttachmentsCollection(getAttachmentsForBug(bugDto, todo));

		addEntityStates(bugDto.getEntityStateID(), todo);
		return assignableToDo;
	}

	private void addEntityStates(int entityStateID, MyAssignmentsToDo todo) {

		EntityStateDTO state = getEntityStateById(entityStateID);
		addStateInToDo(todo, state);
		if (state.getNextStates() != null && state.getNextStates().trim().length() != 0) {
			String[] ids = state.getNextStates().split(",");
			for (String stringId : ids) {
				state = getEntityStateById(Integer.parseInt(stringId));
				if (state != null) {
					addStateInToDo(todo, state);
				}
			}

		}

	}

	private void addStateInToDo(MyAssignmentsToDo todo, EntityStateDTO state) {
		if (todo.getEntityStates() == null) {
			ArrayOfEntityStateDTO param = new ArrayOfEntityStateDTO();
			todo.setEntityStates(param);
		}

		if (todo.getEntityStates().getEntityStateDTO() == null) {
			todo.getEntityStates().addEntityStateDTO(convertState(state));
		} else {
			for (MyAssignmentsServiceStub.EntityStateDTO addedState : todo.getEntityStates().getEntityStateDTO()) {
				if (addedState.getEntityStateID() == state.getEntityStateID()) {
					return;
				}
			}
			todo.getEntityStates().addEntityStateDTO(convertState(state));
		}

	}

	private MyAssignmentsServiceStub.EntityStateDTO convertState(EntityStateDTO state) {
		MyAssignmentsServiceStub.EntityStateDTO convertedState = new MyAssignmentsServiceStub.EntityStateDTO();

		convertedState.setEntityStateID(state.getEntityStateID());
		convertedState.setID(state.getID());
		convertedState.setRoleID(state.getRoleID());
		convertedState.setRoleName(state.getRoleName());
		convertedState.setEntityTypeName(state.getEntityTypeName());
		convertedState.setFinal(state.getFinal());
		convertedState.setInitial(state.getInitial());
		convertedState.setName(state.getName());
		convertedState.setNextStates(state.getNextStates());
		convertedState.setPlanned(state.getPlanned());
		convertedState.setProcessID(state.getProcessID());
		convertedState.setProcessName(state.getProcessName());
		convertedState.setRequiredComment(state.getRequiredComment());

		return convertedState;
	}

	private EntityStateDTO getEntityStateById(int entityStateID) {
		EntityStateDTO[] states = retrieveAllEntityStates();
		for (EntityStateDTO state : states) {
			if (state.getEntityStateID() == entityStateID) {
				return state;
			}
		}

		return null;
	}

	private ArrayOfAttachmentDTO getAttachmentsForBug(BugDTO bugDto, MyAssignmentsToDo todo) {
		ArrayOfAttachmentDTO attachments = new ArrayOfAttachmentDTO();

		AttachmentDTO[] bugAttachments = getBugAttachmentList();
		for (AttachmentDTO attachmentDto : bugAttachments) {
			org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AttachmentDTO attachment = new org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AttachmentDTO();
			attachment.setAttachmentID(attachmentDto.getAttachmentID());
			attachment.setCreateDate(attachmentDto.getCreateDate());
			attachment.setDescription(attachmentDto.getDescription());
			attachment.setGeneralID(attachmentDto.getGeneralID());
			attachment.setGeneralName(attachmentDto.getGeneralName());
			attachment.setID(attachmentDto.getID());
			attachment.setOwnerID(attachmentDto.getOwnerID());
			attachment.setAttachmentFileID(attachmentDto.getAttachmentFileID());
			attachments.addAttachmentDTO(attachment);

			addUser(todo, attachmentDto.getOwnerID());
		}
		return attachments;
	}

	private ArrayOfCommentDTO getCommentsForBug(BugDTO bug, MyAssignmentsToDo todo) {
		ArrayOfCommentDTO comments = new ArrayOfCommentDTO();

		ArrayList<CommentDTO> bugComments = getBugCommentList();
		for (CommentDTO commentDto : bugComments) {
			org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.CommentDTO comment = new org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.CommentDTO();
			comment.setCommentID(commentDto.getCommentID());
			comment.setCreateDate(commentDto.getCreateDate());
			comment.setDescription(commentDto.getDescription());
			comment.setGeneralID(commentDto.getGeneralID());
			comment.setGeneralName(commentDto.getGeneralName());
			comment.setID(commentDto.getID());
			comment.setOwnerID(commentDto.getOwnerID());
			comment.setParentID(commentDto.getParentID());
			comments.addCommentDTO(comment);

			addUser(todo, commentDto.getOwnerID());
		}
		return comments;
	}

	private ArrayOfCommentDTO getCommentsForRequest(RequestDTO request, MyAssignmentsToDo todo) {
		ArrayOfCommentDTO comments = new ArrayOfCommentDTO();

		org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.CommentDTO[] requestComments = getRequestCommentList();
		for (org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.CommentDTO commentDto : requestComments) {
			org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.CommentDTO comment = new org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.CommentDTO();
			comment.setCommentID(commentDto.getCommentID());
			comment.setCreateDate(commentDto.getCreateDate());
			comment.setDescription(commentDto.getDescription());
			comment.setGeneralID(commentDto.getGeneralID());
			comment.setGeneralName(commentDto.getGeneralName());
			comment.setID(commentDto.getID());
			comment.setOwnerID(commentDto.getOwnerID());
			comment.setParentID(commentDto.getParentID());
			comments.addCommentDTO(comment);

			addUser(todo, commentDto.getOwnerID());
		}
		return comments;
	}

	private ArrayOfAttachmentDTO getAttachmentsForRequest(RequestDTO requestDto, MyAssignmentsToDo todo) {
		ArrayOfAttachmentDTO attachments = new ArrayOfAttachmentDTO();

		org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.AttachmentDTO[] requestAttachments = getRequestAttachmentList();
		for (org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.AttachmentDTO attachmentDto : requestAttachments) {
			org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AttachmentDTO attachment = new org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AttachmentDTO();
			attachment.setAttachmentID(attachmentDto.getAttachmentID());
			attachment.setCreateDate(attachmentDto.getCreateDate());
			attachment.setDescription(attachmentDto.getDescription());
			attachment.setGeneralID(attachmentDto.getGeneralID());
			attachment.setGeneralName(attachmentDto.getGeneralName());
			attachment.setID(attachmentDto.getID());
			attachment.setOwnerID(attachmentDto.getOwnerID());
			attachment.setAttachmentFileID(attachmentDto.getAttachmentFileID());
			attachments.addAttachmentDTO(attachment);

			addUser(todo, attachmentDto.getOwnerID());
		}
		return attachments;
	}

	private ArrayOfCommentDTO getCommentsForUserStory(UserStoryDTO userStory, MyAssignmentsToDo todo) {
		ArrayOfCommentDTO comments = new ArrayOfCommentDTO();

		org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.CommentDTO[] userStoryComments = getUserStoryCommentList();
		for (org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.CommentDTO commentDto : userStoryComments) {
			org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.CommentDTO comment = new org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.CommentDTO();
			comment.setCommentID(commentDto.getCommentID());
			comment.setCreateDate(commentDto.getCreateDate());
			comment.setDescription(commentDto.getDescription());
			comment.setGeneralID(commentDto.getGeneralID());
			comment.setGeneralName(commentDto.getGeneralName());
			comment.setID(commentDto.getID());
			comment.setOwnerID(commentDto.getOwnerID());
			comment.setParentID(commentDto.getParentID());
			comments.addCommentDTO(comment);

			addUser(todo, commentDto.getOwnerID());
		}
		return comments;
	}

	private ArrayOfAttachmentDTO getAttachmentsForUserStory(UserStoryDTO bugDto, MyAssignmentsToDo todo) {
		ArrayOfAttachmentDTO attachments = new ArrayOfAttachmentDTO();

		org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.AttachmentDTO[] requestAttachments = getUserStoryAttachmentList();
		for (org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.AttachmentDTO attachmentDto : requestAttachments) {
			org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AttachmentDTO attachment = new org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AttachmentDTO();
			attachment.setAttachmentID(attachmentDto.getAttachmentID());
			attachment.setCreateDate(attachmentDto.getCreateDate());
			attachment.setDescription(attachmentDto.getDescription());
			attachment.setGeneralID(attachmentDto.getGeneralID());
			attachment.setGeneralName(attachmentDto.getGeneralName());
			attachment.setID(attachmentDto.getID());
			attachment.setOwnerID(attachmentDto.getOwnerID());
			attachment.setAttachmentFileID(attachmentDto.getAttachmentFileID());
			attachments.addAttachmentDTO(attachment);

			addUser(todo, attachmentDto.getOwnerID());
		}
		return attachments;
	}

	private ArrayOfCommentDTO getCommentsForTask(TaskDTO task, MyAssignmentsToDo todo) {
		ArrayOfCommentDTO comments = new ArrayOfCommentDTO();

		org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.CommentDTO[] taskComments = retrieveCommentsForTask(task
				.getTaskID());
		for (org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.CommentDTO commentDto : taskComments) {
			org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.CommentDTO comment = new org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.CommentDTO();
			comment.setCommentID(commentDto.getCommentID());
			comment.setCreateDate(commentDto.getCreateDate());
			comment.setDescription(commentDto.getDescription());
			comment.setGeneralID(commentDto.getGeneralID());
			comment.setGeneralName(commentDto.getGeneralName());
			comment.setID(commentDto.getID());
			comment.setOwnerID(commentDto.getOwnerID());
			comment.setParentID(commentDto.getParentID());
			comments.addCommentDTO(comment);

			addUser(todo, commentDto.getOwnerID());
		}
		return comments;
	}

	private ArrayOfAttachmentDTO getAttachmentsForTask(TaskDTO taskDto, MyAssignmentsToDo todo) {
		ArrayOfAttachmentDTO attachments = new ArrayOfAttachmentDTO();

		org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.AttachmentDTO[] taskAttachments = getTaskAttachmentList(taskDto
				.getTaskID());
		for (org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.AttachmentDTO attachmentDto : taskAttachments) {
			org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AttachmentDTO attachment = new org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AttachmentDTO();
			attachment.setAttachmentID(attachmentDto.getAttachmentID());
			attachment.setCreateDate(attachmentDto.getCreateDate());
			attachment.setDescription(attachmentDto.getDescription());
			attachment.setGeneralID(attachmentDto.getGeneralID());
			attachment.setGeneralName(attachmentDto.getGeneralName());
			attachment.setID(attachmentDto.getID());
			attachment.setOwnerID(attachmentDto.getOwnerID());
			attachment.setAttachmentFileID(attachmentDto.getAttachmentFileID());
			attachments.addAttachmentDTO(attachment);

			addUser(todo, attachmentDto.getOwnerID());
		}
		return attachments;
	}

	private org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.ArrayOfTeamDTO getTeamsForEntity(
			int entityID, MyAssignmentsToDo todo) {
		org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.ArrayOfTeamDTO teams = new org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.ArrayOfTeamDTO();
		TeamDTO[] assingableTeams = RetrieveTeamsForAssignableResponse(entityID).getTeamDTO();
		for (TeamDTO teamDTO : assingableTeams) {
			org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.TeamDTO team = new org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.TeamDTO();
			team.setID(teamDTO.getID());
			team.setRoleID(teamDTO.getRoleID());
			team.setRoleName(teamDTO.getRoleName());
			team.setAssignableID(teamDTO.getAssignableID());
			team.setAssignableName(teamDTO.getAssignableName());
			team.setTeamID(teamDTO.getTeamID());
			team.setUserID(teamDTO.getUserID());
			teams.addTeamDTO(team);

			addUser(todo, teamDTO.getUserID());
		}
		return teams;
	}

	private void addUser(MyAssignmentsToDo todo, int userID) {

		if (todo.getUsers() == null) {
			ArrayOfUserDTO users = new ArrayOfUserDTO();
			todo.setUsers(users);
		}
		if (todo.getUsers().getUserDTO() != null) {

			for (MyAssignmentsServiceStub.UserDTO user : todo.getUsers().getUserDTO()) {
				if (user.getUserID() == userID) {
					return;
				}
			}
		}
		GeneralUserDTO generalUser = getUserById(userID);

		UserDTO user = new UserDTO();
		user.setUserID(generalUser.getUserID());
		user.setID(generalUser.getID());
		user.setLogin(generalUser.getLogin());
		user.setFirstName(generalUser.getFirstName());
		user.setLastName(generalUser.getLastName());
		todo.getUsers().addUserDTO(user);
	}

	private void addAttachToBug(BugDTO bug, GeneralUserDTO owner, String description, Calendar createdDate,
			String originalFileName) {

		BugServiceStub.AttachmentDTO attachment = new BugServiceStub.AttachmentDTO();
		attachment.setID(genId());
		attachment.setAttachmentID(attachment.getID());
		attachment.setCreateDate(createdDate);
		attachment.setDescription(description);
		attachment.setOwnerID(owner.getUserID());
		attachment.setOriginalFileName(originalFileName);
		attachment.setGeneralName(bug.getName());

		bugAttachmentsList.add(attachment);
	}

	private void addAttachToTask(TaskDTO task, GeneralUserDTO owner, String description, Calendar createdDate,
			String originalFileName) {

		TaskServiceStub.AttachmentDTO attachment = new TaskServiceStub.AttachmentDTO();
		attachment.setID(genId());
		attachment.setAttachmentID(attachment.getID());
		attachment.setCreateDate(createdDate);
		attachment.setDescription(description);
		attachment.setOwnerID(owner.getUserID());
		attachment.setOriginalFileName(originalFileName);
		attachment.setGeneralName(task.getName());

		taskAttachmentsList.add(attachment);
	}

	private void addAttachToRequest(RequestDTO request, GeneralUserDTO owner, String description, Calendar createdDate,
			String originalFileName) {

		RequestServiceStub.AttachmentDTO attachment = new RequestServiceStub.AttachmentDTO();
		attachment.setID(genId());
		attachment.setAttachmentID(attachment.getID());
		attachment.setCreateDate(createdDate);
		attachment.setDescription(description);
		attachment.setOwnerID(owner.getUserID());
		attachment.setOriginalFileName(originalFileName);
		attachment.setGeneralName(request.getName());

		requestAttachmentsList.add(attachment);
	}

	private void addAttachToUserStory(UserStoryDTO bug, GeneralUserDTO owner, String description, Calendar createdDate,
			String originalFileName) {

		UserStoryServiceStub.AttachmentDTO attachment = new UserStoryServiceStub.AttachmentDTO();
		attachment.setID(genId());
		attachment.setAttachmentID(attachment.getID());
		attachment.setCreateDate(createdDate);
		attachment.setDescription(description);
		attachment.setOwnerID(owner.getUserID());
		attachment.setOriginalFileName(originalFileName);
		attachment.setGeneralName(bug.getName());

		userStoryAttachmentsList.add(attachment);
	}

	private void initLists() {
		bugList = new ArrayList<BugDTO>();
		userList = new ArrayList<GeneralUserDTO>();
		userStoryList = new ArrayList<UserStoryDTO>();
		assignableTeams = new HashMap<Integer, ArrayOfTeamDTO>();
		taskList = new ArrayList<TaskDTO>();
		priorityList = new ArrayList<PriorityDTO>();
		requestList = new ArrayList<RequestDTO>();
		entityStateList = new ArrayList<EntityStateDTO>();
		generalDTOList = new ArrayList<GeneralDTO>();

		bugCommentsList = new ArrayList<CommentDTO>();
		taskCommentsList = new ArrayList<TaskServiceStub.CommentDTO>();
		requestCommentsList = new ArrayList<RequestServiceStub.CommentDTO>();
		userStoryCommentsList = new ArrayList<UserStoryServiceStub.CommentDTO>();

		bugAttachmentsList = new ArrayList<BugServiceStub.AttachmentDTO>();
		taskAttachmentsList = new ArrayList<TaskServiceStub.AttachmentDTO>();
		requestAttachmentsList = new ArrayList<RequestServiceStub.AttachmentDTO>();
		userStoryAttachmentsList = new ArrayList<UserStoryServiceStub.AttachmentDTO>();
	}

	private void addCommentToBug(int bugID, int ownerId, String description) {
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setOwnerID(ownerId);
		commentDTO.setDescription(description);
		commentDTO.setCommentID(genId());
		commentDTO.setID(commentDTO.getCommentID());
		Calendar calendar = Calendar.getInstance();
		calendar.set(2009, 12, 31);
		commentDTO.setCreateDate(calendar);
		bugCommentsList.add(commentDTO);
	}

	private void addCommentToUserStory(int userStoryID, int ownerId, String description) {
		UserStoryServiceStub.CommentDTO commentDTO = new UserStoryServiceStub.CommentDTO();
		commentDTO.setOwnerID(ownerId);
		commentDTO.setDescription(description);
		commentDTO.setCommentID(genId());
		commentDTO.setID(commentDTO.getCommentID());
		Calendar calendar = Calendar.getInstance();
		calendar.set(2009, 12, 31);
		commentDTO.setCreateDate(calendar);
		userStoryCommentsList.add(commentDTO);
	}

	private void addCommentToRequest(int requestID, int ownerId, String description) {
		RequestServiceStub.CommentDTO commentDTO = new RequestServiceStub.CommentDTO();
		commentDTO.setOwnerID(ownerId);
		commentDTO.setDescription(description);
		commentDTO.setCommentID(genId());
		commentDTO.setID(commentDTO.getCommentID());
		Calendar calendar = Calendar.getInstance();
		calendar.set(2009, 12, 31);
		commentDTO.setCreateDate(calendar);
		requestCommentsList.add(commentDTO);
	}

	private void addCommentToTask(int taskID, int ownerId, String description) {
		TaskServiceStub.CommentDTO commentDTO = new TaskServiceStub.CommentDTO();
		commentDTO.setOwnerID(ownerId);
		commentDTO.setDescription(description);
		commentDTO.setCommentID(genId());
		commentDTO.setID(commentDTO.getCommentID());
		Calendar calendar = Calendar.getInstance();
		calendar.set(2009, 12, 31);
		commentDTO.setCreateDate(calendar);
		taskCommentsList.add(commentDTO);
	}

	private GeneralDTO addGeneralDTO(int id) {
		GeneralDTO generalDTO = new GeneralDTO();
		generalDTO.setGeneralID(id);
		generalDTO.setID(generalDTO.getGeneralID());

		generalDTO.setEntityTypeName(EntityPerformerFactory.TP_BUSINESS_OBJECTS_BUG);
		generalDTOList.add(generalDTO);
		return generalDTO;
	}

	private EntityStateDTO addEnetityState(String name, String entityTypeName, boolean initial, String process) {

		EntityStateDTO entityState = new EntityStateDTO();

		entityState.setID(genId());
		entityState.setEntityStateID(entityState.getID());
		entityState.setName(name);
		entityState.setInitial(initial);
		entityState.setEntityTypeName(entityTypeName);
		entityState.setProcessName(process);

		entityStateList.add(entityState);

		return entityState;
	}

	private RequestDTO addRequest(String name, PriorityDTO priority, String description, GeneralUserDTO owner,
			EntityStateDTO entityStateDTO) {
		RequestDTO request = new RequestDTO();

		request.setEntityTypeName(EntityPerformerFactory.TP_BUSINESS_OBJECTS_REQUEST);
		request.setID(genId());
		request.setRequestID(request.getID());
		request.setName(name);
		request.setPriorityID(priority.getID());
		request.setPriorityName(priority.getName());

		request.setEntityTypeName(entityStateDTO.getEntityTypeName());
		request.setEntityStateName(entityStateDTO.getName());
		request.setEntityStateID(entityStateDTO.getID());

		request.setProjectName("Project");
		request.setName(priority.getName());
		request.setDescription(description);
		request.setOwnerID(owner.getID());
		request.setTimeSpent(new BigDecimal(4.0));
		request.setTimeRemain(new BigDecimal(2.0));

		GregorianCalendar createDate = new GregorianCalendar();
		request.setCreateDate(createDate);

		requestList.add(request);
		return request;
	}

	private PriorityDTO addRequestPriority(String priorityName, int importance) {
		return addPriority(priorityName, importance, new String(EntityPerformerFactory.TP_BUSINESS_OBJECTS_REQUEST));
	}

	private void addUserStoryPriority(String priorityName, int importance) {
		addPriority(priorityName, importance, new String(EntityPerformerFactory.TP_BUSINESS_OBJECTS_USER_STORY));
	}

	private void addBugPriority(String priorityName, int importance) {
		addPriority(priorityName, importance, new String(EntityPerformerFactory.TP_BUSINESS_OBJECTS_BUG));
	}

	private PriorityDTO addPriority(String priorityName, int importance, String entityTypeName) {
		PriorityDTO priority = new PriorityDTO();
		priority.setID(genId());
		priority.setPriorityID(priority.getID());
		priority.setName(priorityName);
		priority.setImportance(importance);
		priority.setEntityTypeName(entityTypeName);
		priorityList.add(priority);

		return priority;
	}

	private TaskDTO addTask(GeneralUserDTO userDTO, UserStoryDTO userStoryDTO, EntityStateDTO entityStateDTO) {
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setTaskID(genId());
		taskDTO.setID(taskDTO.getTaskID());
		taskDTO.setName("Taskmock name");
		taskDTO.setOwnerID(userDTO.getUserID());
		taskDTO.setDescription("task mock<br /> description");

		taskDTO.setPriorityName("Average");
		taskDTO.setEntityTypeName(entityStateDTO.getEntityTypeName());
		taskDTO.setEntityStateName(entityStateDTO.getName());
		taskDTO.setEntityStateID(entityStateDTO.getID());

		taskDTO.setUserStoryID(userStoryDTO.getUserStoryID());
		taskDTO.setCreateDate(new GregorianCalendar());

		taskList.add(taskDTO);
		return taskDTO;
	}

	private UserStoryDTO addUserStory(GeneralUserDTO userDTO, EntityStateDTO entityStateDTO) {

		UserStoryDTO userStoryDTO = new UserStoryDTO();
		userStoryDTO.setUserStoryID(genId());
		userStoryDTO.setID(userStoryDTO.getUserStoryID());
		userStoryDTO.setName("User story mock name");
		userStoryDTO.setOwnerID(userDTO.getUserID());
		userStoryDTO.setDescription("user story mock<br /> description");

		userStoryDTO.setEntityTypeName(entityStateDTO.getEntityTypeName());
		userStoryDTO.setEntityStateName(entityStateDTO.getName());
		userStoryDTO.setEntityStateID(entityStateDTO.getID());

		userStoryDTO.setPriorityName("Average");
		userStoryDTO.setCreateDate(new GregorianCalendar());
		userStoryList.add(userStoryDTO);
		return userStoryDTO;
	}

	private BugDTO addBug(GeneralUserDTO userDTO, EntityStateDTO entityStateDTO) {
		BugDTO bugDTO = new BugDTO();
		bugDTO.setBugID(genId());
		bugDTO.setID(bugDTO.getBugID());
		bugDTO.setName("Mock bug name");
		bugDTO.setDescription("Mock bug<br /> description");

		bugDTO.setEntityStateName(entityStateDTO.getName());
		bugDTO.setEntityTypeName(entityStateDTO.getEntityTypeName());
		bugDTO.setEntityStateID(entityStateDTO.getID());

		bugDTO.setProjectName("Mock project name");
		bugDTO.setOwnerID(userDTO.getUserID());
		bugDTO.setUserStoryName("Mock user storry");
		bugDTO.setSeverityName("Mock user severity name");
		bugDTO.setTimeRemain(new BigDecimal(1.0));
		bugDTO.setTimeSpent(new BigDecimal(3.0));
		bugDTO.setPriorityName("Fix ASAP");
		bugDTO.setCreateDate(new GregorianCalendar());

		if (entityStateDTO.getFinal()) {
			bugDTO.setEndDate(Calendar.getInstance());
		}

		bugList.add(bugDTO);
		return bugDTO;
	}

	private GeneralUserDTO AddUser(String firstName, String lastName) {
		GeneralUserDTO userDTO = new GeneralUserDTO();
		userDTO.setUserID(genId());
		userDTO.setID(userDTO.getUserID());
		userDTO.setFirstName(firstName);
		userDTO.setLastName(lastName);

		userList.add(userDTO);

		return userDTO;
	}

	public HashMap<Integer, ArrayOfTeamDTO> getAssignableTeams() {
		return assignableTeams;
	}

	public ArrayList<BugDTO> getBugList() {
		return bugList;
	}

	public ArrayList<GeneralUserDTO> getUserList() {
		return userList;
	}

	public GeneralUserDTO getUserById(int id) {

		for (GeneralUserDTO userDTO : getUserList()) {
			if (userDTO.getUserID() == id) {
				return userDTO;
			}
		}

		return null;
	}

	public BugDTO getBugById(int id) {

		for (BugDTO bugDTO : getBugList()) {
			if (bugDTO.getBugID() == id) {
				return bugDTO;
			}
		}

		return null;
	}

	public TaskDTO getTaskById(int id) {
		for (TaskDTO taskDTO : getTaskList()) {
			if (taskDTO.getTaskID() == id) {
				return taskDTO;
			}
		}

		return null;
	}

	public ArrayList<TaskDTO> getTaskList() {
		return taskList;
	}

	public RequestDTO getRequestById(int id) {
		for (RequestDTO request : getRequestList()) {
			if (request.getRequestID() == id) {
				return request;
			}
		}
		return null;
	}

	public ArrayList<RequestDTO> getRequestList() {
		return requestList;
	}

	public UserStoryDTO getUserStoryById(int id) {

		for (UserStoryDTO userStoryDTO : getUserStoryList()) {
			if (userStoryDTO.getUserStoryID() == id) {
				return userStoryDTO;
			}
		}

		return null;
	}

	public GeneralDTO getGeneralDTOById(int id2) {
		for (GeneralDTO generalDTO : getGeneralDTOList()) {
			if (generalDTO.getGeneralID() == id2) {
				return generalDTO;
			}
		}

		return null;
	}

	public ArrayOfTeamDTO RetrieveTeamsForAssignableResponse(int assignableID) {
		return getAssignableTeams().get(assignableID);
	}

	public MyAssignments getMyAssignments() {
		return myAssignmentsList;
	}

	private MyAssignments InitMyAssignments() {
		MyAssignments myAssignments = new MyAssignments();
		ArrayOfAssignableSimpleDTO arrayOfAssignableSimpleDTO = new ArrayOfAssignableSimpleDTO();

		for (BugDTO bugDTO : bugList) {
			AssignableSimpleDTO assignableSimpleDTO = new AssignableSimpleDTO();

			assignableSimpleDTO.setEntityTypeName(EntityPerformerFactory.TP_BUSINESS_OBJECTS_BUG);
			assignableSimpleDTO.set_entityTypeName(EntityPerformerFactory.TP_BUSINESS_OBJECTS_BUG);
			assignableSimpleDTO.setID(bugDTO.getID());

			arrayOfAssignableSimpleDTO.addAssignableSimpleDTO(assignableSimpleDTO);
		}

		for (UserStoryDTO userStoryDTO : userStoryList) {
			AssignableSimpleDTO assignableSimpleDTO = new AssignableSimpleDTO();

			assignableSimpleDTO.setEntityTypeName(EntityPerformerFactory.TP_BUSINESS_OBJECTS_USER_STORY);
			assignableSimpleDTO.set_entityTypeName(EntityPerformerFactory.TP_BUSINESS_OBJECTS_USER_STORY);
			assignableSimpleDTO.setID(userStoryDTO.getID());

			arrayOfAssignableSimpleDTO.addAssignableSimpleDTO(assignableSimpleDTO);
		}

		for (TaskDTO taskDTO : taskList) {
			AssignableSimpleDTO assignableSimpleDTO = new AssignableSimpleDTO();

			assignableSimpleDTO.setEntityTypeName(EntityPerformerFactory.TP_BUSINESS_OBJECTS_TASK);
			assignableSimpleDTO.set_entityTypeName(EntityPerformerFactory.TP_BUSINESS_OBJECTS_TASK);
			assignableSimpleDTO.setID(taskDTO.getID());

			arrayOfAssignableSimpleDTO.addAssignableSimpleDTO(assignableSimpleDTO);
		}

		for (RequestDTO requestDTO : requestList) {
			AssignableSimpleDTO assignableSimpleDTO = new AssignableSimpleDTO();

			assignableSimpleDTO.setEntityTypeName(EntityPerformerFactory.TP_BUSINESS_OBJECTS_REQUEST);
			assignableSimpleDTO.set_entityTypeName(EntityPerformerFactory.TP_BUSINESS_OBJECTS_REQUEST);
			assignableSimpleDTO.setID(requestDTO.getID());

			arrayOfAssignableSimpleDTO.addAssignableSimpleDTO(assignableSimpleDTO);
		}

		myAssignments.setAssignables(arrayOfAssignableSimpleDTO);
		return myAssignments;
	}

	public ArrayList<UserStoryDTO> getUserStoryList() {
		return userStoryList;
	}

	public ArrayList<GeneralDTO> getGeneralDTOList() {
		return generalDTOList;
	}

	public ArrayList<PriorityDTO> getPriorityList() {
		return priorityList;
	}

	public EntityStateDTO[] retrieveAllEntityStates() {
		EntityStateDTO[] entityStateDTOs = new EntityStateDTO[entityStateList.size()];
		return entityStateList.toArray(entityStateDTOs);
	}

	public EntityStateDTO retrieveEntityStateBy(String entityTypeName, String entityStateName) {
		for (EntityStateDTO entityStateDTO : entityStateList) {
			if (entityStateDTO.getEntityTypeName().equals(entityTypeName)
					&& entityStateDTO.getName().equals(entityStateName)) {
				return entityStateDTO;
			}
		}

		return null;
	}

	public void cleanMyToDoList() {
		myAssignmentsList.getAssignables().setAssignableSimpleDTO(null);
		myAssignmentsToDo.getAssignableEntities().setAssignableToDoDTO(null);
	}

	public ArrayList<CommentDTO> getBugCommentList() {
		return bugCommentsList;
	}

	public TaskServiceStub.CommentDTO[] retrieveCommentsForTask(int taskID) {
		TaskServiceStub.CommentDTO[] a = new TaskServiceStub.CommentDTO[taskCommentsList.size()];
		return taskCommentsList.toArray(a);
	}

	public UserStoryServiceStub.CommentDTO[] getUserStoryCommentList() {
		UserStoryServiceStub.CommentDTO[] a = new UserStoryServiceStub.CommentDTO[userStoryCommentsList.size()];
		return userStoryCommentsList.toArray(a);
	}

	public RequestServiceStub.CommentDTO[] getRequestCommentList() {
		RequestServiceStub.CommentDTO[] a = new RequestServiceStub.CommentDTO[requestCommentsList.size()];
		return requestCommentsList.toArray(a);
	}

	public TaskServiceStub.AttachmentDTO[] getTaskAttachmentList(int taskID) {
		TaskServiceStub.AttachmentDTO[] a = new TaskServiceStub.AttachmentDTO[taskAttachmentsList.size()];
		return taskAttachmentsList.toArray(a);
	}

	public UserStoryServiceStub.AttachmentDTO[] getUserStoryAttachmentList() {
		UserStoryServiceStub.AttachmentDTO[] a = new UserStoryServiceStub.AttachmentDTO[userStoryAttachmentsList.size()];
		return userStoryAttachmentsList.toArray(a);
	}

	public RequestServiceStub.AttachmentDTO[] getRequestAttachmentList() {
		RequestServiceStub.AttachmentDTO[] a = new RequestServiceStub.AttachmentDTO[requestAttachmentsList.size()];
		return requestAttachmentsList.toArray(a);
	}

	public BugServiceStub.AttachmentDTO[] getBugAttachmentList() {
		BugServiceStub.AttachmentDTO[] a = new BugServiceStub.AttachmentDTO[bugAttachmentsList.size()];
		return bugAttachmentsList.toArray(a);
	}

	public long getFileSize(String fileName) {
		return 255;
	}

	protected void setAttachmentCreationDate(Calendar attachmentCreationDate) {
		this.attachmentCreationDate = attachmentCreationDate;
	}

	public Calendar getAttachmentCreationDate() {
		return attachmentCreationDate;
	}

	public MyAssignmentsToDo getMyToDoList() {
		return myAssignmentsToDo;
	}

}
