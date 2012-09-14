 package org.eclipse.mylyn.targetprocess.core.tests;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.rmi.RemoteException;
import java.util.List;

import junit.framework.Assert;

import org.eclipse.mylyn.targetprocess.core.EntityCollector;
import org.eclipse.mylyn.targetprocess.core.EntityStateManager;
import org.eclipse.mylyn.targetprocess.core.TargetProcessAttribute;
import org.eclipse.mylyn.targetprocess.core.TargetProcessCredentialsFactory;
import org.eclipse.mylyn.targetprocess.core.TargetProcessEntityKind;
import org.eclipse.mylyn.targetprocess.modules.services.GeneralUserServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrieveTeamsForAssignable;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.BugDTO;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.GetByID;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveAttachmentsForBug;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveCommentsForBug;
import org.eclipse.mylyn.targetprocess.modules.services.FileServiceStub.GetFileSize;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AssignableToDoDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.MyAssignmentsToDo;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RequestDTO;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.TaskDTO;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.UserStoryDTO;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IAssignableServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IBugServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IEntityStateServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IFileServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IGeneralUserServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IRequestServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.ITaskServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IUserStoryServiceStub;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EntityCollectorTests {

	private IUserStoryServiceStub userStoryServiceStubMock;
	private EntityCollector entityCollector;
	private TaskRepository taskRepository;
	private MockServiceFactory factory;
	private IGeneralUserServiceStub generalUserServiceStubMock;
	private IBugServiceStub bugServiceStubMock;
	private IRequestServiceStub requestServiceStubMock;
	private IAssignableServiceStub assignableServiceStubMock;
	private ITaskServiceStub taskServiceStubMock;
	private IEntityStateServiceStub entityServiceStubMock;
	private IFileServiceStub fileServiceStubMock;

	private final String connectorKind = "connectorKind";
	private final String repositoryUrl = "http://repositoryUrl";

	@After
	public void TerDown() {
		EntityStateManager.clean();
	}

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws RemoteException {
		factory = new MockServiceFactory();
		taskRepository = new TaskRepository(connectorKind, repositoryUrl);
		taskRepository.setAuthenticationCredentials("userName", "password");
		factory.getBugServiceStub();
		factory.getGeneralUserServiceStub();
		factory.getAssignableServiceStub();
		userStoryServiceStubMock = factory.getUserStoryServiceStub();
		bugServiceStubMock = createMock(IBugServiceStub.class);
		generalUserServiceStubMock = createMock(IGeneralUserServiceStub.class);
		assignableServiceStubMock = createMock(IAssignableServiceStub.class);
		userStoryServiceStubMock = createMock(IUserStoryServiceStub.class);
		taskServiceStubMock = createMock(ITaskServiceStub.class);
		requestServiceStubMock = createMock(IRequestServiceStub.class);
		entityServiceStubMock = createMock(IEntityStateServiceStub.class);
		fileServiceStubMock = createMock(IFileServiceStub.class);

		factory.setMock(fileServiceStubMock);
		factory.setMock(bugServiceStubMock);
		factory.setMock(generalUserServiceStubMock);
		factory.setMock(assignableServiceStubMock);
		factory.setMock(userStoryServiceStubMock);
		factory.setMock(taskServiceStubMock);
		factory.setMock(requestServiceStubMock);
		factory.setMock(entityServiceStubMock);

		entityCollector = new EntityCollector(factory, TargetProcessCredentialsFactory
				.createTargetProcessCredentials(taskRepository));
	}

	@Test
	public void testWillCorrectlyPopulateTaskDataFromMyToDoList() throws RemoteException {
		expect(taskServiceStubMock.getByID(isA(TaskServiceStub.GetByID.class))).andReturn(null);
		expect(taskServiceStubMock.retrieveAttachmentsForTask((isA(TaskServiceStub.RetrieveAttachmentsForTask.class))))
				.andReturn(null);
		expect(userStoryServiceStubMock.getByID(isA(UserStoryServiceStub.GetByID.class))).andReturn(null);
		expect(taskServiceStubMock.retrieveCommentsForTask(isA(TaskServiceStub.RetrieveCommentsForTask.class)))
				.andReturn(null);
		expect(generalUserServiceStubMock.getByID(isA(GeneralUserServiceStub.GetByID.class))).andReturn(null).times(6);
		// expect(entityServiceStubMock.ret(isA(UserStoryServiceStub.GetByID.class))).andReturn(null);
		expect(fileServiceStubMock.getFileSize(isA(GetFileSize.class))).andReturn(null);

		replay(fileServiceStubMock);
		replay(generalUserServiceStubMock);
		replay(taskServiceStubMock);
		replay(userStoryServiceStubMock);

		TaskDTO taskDTO = factory.getContext().getTaskList().get(0);
		entityCollector.accept(factory.getContext().getMyAssignments().getAssignables().getAssignableSimpleDTO()[2],
				taskRepository);

		Assert.assertEquals(1, entityCollector.getTaskDataList().size());
		TaskData taskDataRetrieved = entityCollector.getTaskDataList().get(0);
		Assert.assertEquals(connectorKind, taskDataRetrieved.getConnectorKind());
		Assert.assertEquals(repositoryUrl, taskDataRetrieved.getRepositoryUrl());

		Assert.assertEquals(TargetProcessEntityKind.TASK.toString(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.EntityKind.getKey()).getValue());
		Assert.assertEquals(String.valueOf(taskDTO.getID()), taskDataRetrieved.getTaskId());
		Assert.assertEquals(taskDTO.getName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Name.getKey()).getValue());
		Assert.assertEquals(taskDTO.getDescription(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Description.getKey()).getValue());
		Assert.assertEquals(taskDTO.getPriorityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Priority.getKey()).getValue());

		TaskAttribute stateAttribute = taskDataRetrieved.getRoot().getAttribute(TargetProcessAttribute.State.getKey());
		Assert.assertEquals(String.valueOf(taskDTO.getEntityStateID()), stateAttribute.getValue());
		Assert.assertEquals(1, stateAttribute.getOptions().size());
		Assert.assertTrue(stateAttribute.getOptions().containsValue("Open"));

		Assert.assertEquals(factory.getContext().getUserStoryById(taskDTO.getUserStoryID()).getPriorityName(),
				taskDataRetrieved.getRoot().getAttribute(TargetProcessAttribute.Priority.getKey()).getValue());

		List<TaskAttribute> commentAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_COMMENT);

		Assert.assertEquals(2, commentAttributes.size());

		List<TaskAttribute> attachAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_ATTACHMENT);

		Assert.assertEquals(1, attachAttributes.size());

		Assert.assertNotNull(taskDataRetrieved.getRoot().getMappedAttribute(TaskAttribute.COMMENT_NEW));

		verify(fileServiceStubMock);
		verify(taskServiceStubMock);
		verify(generalUserServiceStubMock);
	}
	
	@Test
	public void testWillCorrectlyPopulateTaskDataFromMyTaskToDoDto() throws RemoteException {		
		
		replay(fileServiceStubMock);
		replay(generalUserServiceStubMock);
		replay(taskServiceStubMock);
		replay(userStoryServiceStubMock);

		MyAssignmentsToDo toDoList = factory.getContext().getMyToDoList();
		AssignableToDoDTO taskDTO = toDoList.getAssignableEntities().getAssignableToDoDTO()[2];
		entityCollector.accept(taskDTO,toDoList,taskRepository);
		

		Assert.assertEquals(1, entityCollector.getTaskDataList().size());
		TaskData taskDataRetrieved = entityCollector.getTaskDataList().get(0);
		Assert.assertEquals(connectorKind, taskDataRetrieved.getConnectorKind());
		Assert.assertEquals(repositoryUrl, taskDataRetrieved.getRepositoryUrl());

		Assert.assertEquals(TargetProcessEntityKind.TASK.toString(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.EntityKind.getKey()).getValue());
		Assert.assertEquals(String.valueOf(taskDTO.getID()), taskDataRetrieved.getTaskId());
		Assert.assertEquals(taskDTO.getName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Name.getKey()).getValue());
		Assert.assertEquals(taskDTO.getDescription(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Description.getKey()).getValue());
		Assert.assertEquals(taskDTO.getPriorityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Priority.getKey()).getValue());

		TaskAttribute stateAttribute = taskDataRetrieved.getRoot().getAttribute(TargetProcessAttribute.State.getKey());
		Assert.assertEquals(String.valueOf(taskDTO.getEntityStateID()), stateAttribute.getValue());
		Assert.assertEquals(1, stateAttribute.getOptions().size());
		Assert.assertTrue(stateAttribute.getOptions().containsValue("Open"));

		Assert.assertEquals(taskDTO.getPriorityName(),
				taskDataRetrieved.getRoot().getAttribute(TargetProcessAttribute.Priority.getKey()).getValue());

		List<TaskAttribute> commentAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_COMMENT);

		Assert.assertEquals(2, commentAttributes.size());

		List<TaskAttribute> attachAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_ATTACHMENT);

		Assert.assertEquals(1, attachAttributes.size());

		Assert.assertNotNull(taskDataRetrieved.getRoot().getMappedAttribute(TaskAttribute.COMMENT_NEW));

		verify(fileServiceStubMock);
		verify(taskServiceStubMock);
		verify(generalUserServiceStubMock);
	}	
	

	@Test
	public void testWillCorrectlyPopulateTaskDataFromTPTaskDTO() throws RemoteException {
		expect(taskServiceStubMock.getByID(isA(TaskServiceStub.GetByID.class))).andReturn(null);
		expect(taskServiceStubMock.retrieveAttachmentsForTask((isA(TaskServiceStub.RetrieveAttachmentsForTask.class))))
				.andReturn(null);
		expect(userStoryServiceStubMock.getByID(isA(UserStoryServiceStub.GetByID.class))).andReturn(null);
		expect(taskServiceStubMock.retrieveCommentsForTask(isA(TaskServiceStub.RetrieveCommentsForTask.class)))
				.andReturn(null);
		expect(generalUserServiceStubMock.getByID(isA(GeneralUserServiceStub.GetByID.class))).andReturn(null).times(6);
		// expect(entityServiceStubMock.ret(isA(UserStoryServiceStub.GetByID.class))).andReturn(null);
		expect(fileServiceStubMock.getFileSize(isA(GetFileSize.class))).andReturn(null);

		replay(fileServiceStubMock);
		replay(generalUserServiceStubMock);
		replay(taskServiceStubMock);
		replay(userStoryServiceStubMock);

		TaskDTO taskDTO = factory.getContext().getTaskList().get(0);
		entityCollector.accept(factory.getContext().getMyAssignments().getAssignables().getAssignableSimpleDTO()[2],
				taskRepository);

		Assert.assertEquals(1, entityCollector.getTaskDataList().size());
		TaskData taskDataRetrieved = entityCollector.getTaskDataList().get(0);
		Assert.assertEquals(connectorKind, taskDataRetrieved.getConnectorKind());
		Assert.assertEquals(repositoryUrl, taskDataRetrieved.getRepositoryUrl());

		Assert.assertEquals(TargetProcessEntityKind.TASK.toString(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.EntityKind.getKey()).getValue());
		Assert.assertEquals(String.valueOf(taskDTO.getID()), taskDataRetrieved.getTaskId());
		Assert.assertEquals(taskDTO.getName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Name.getKey()).getValue());
		Assert.assertEquals(taskDTO.getDescription(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Description.getKey()).getValue());
		Assert.assertEquals(taskDTO.getPriorityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Priority.getKey()).getValue());

		TaskAttribute stateAttribute = taskDataRetrieved.getRoot().getAttribute(TargetProcessAttribute.State.getKey());
		Assert.assertEquals(String.valueOf(taskDTO.getEntityStateID()), stateAttribute.getValue());
		Assert.assertEquals(1, stateAttribute.getOptions().size());
		Assert.assertTrue(stateAttribute.getOptions().containsValue("Open"));

		Assert.assertEquals(factory.getContext().getUserStoryById(taskDTO.getUserStoryID()).getPriorityName(),
				taskDataRetrieved.getRoot().getAttribute(TargetProcessAttribute.Priority.getKey()).getValue());

		List<TaskAttribute> commentAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_COMMENT);

		Assert.assertEquals(2, commentAttributes.size());

		List<TaskAttribute> attachAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_ATTACHMENT);

		Assert.assertEquals(1, attachAttributes.size());

		Assert.assertNotNull(taskDataRetrieved.getRoot().getMappedAttribute(TaskAttribute.COMMENT_NEW));

		verify(fileServiceStubMock);
		verify(taskServiceStubMock);
		verify(generalUserServiceStubMock);
	}

	@Test
	public void testWillCorrectlyPopulateTaskDataFromUserStoryToDoDTO() throws RemoteException {
		replay(fileServiceStubMock);
		replay(generalUserServiceStubMock);
		replay(taskServiceStubMock);
		replay(userStoryServiceStubMock);

		MyAssignmentsToDo toDoList = factory.getContext().getMyToDoList();
		AssignableToDoDTO userStoryDTO = toDoList.getAssignableEntities().getAssignableToDoDTO()[1];
		
		entityCollector.accept(userStoryDTO,toDoList,taskRepository);

		Assert.assertEquals(1, entityCollector.getTaskDataList().size());
		TaskData taskDataRetrieved = entityCollector.getTaskDataList().get(0);
		Assert.assertEquals(connectorKind, taskDataRetrieved.getConnectorKind());
		Assert.assertEquals(repositoryUrl, taskDataRetrieved.getRepositoryUrl());

		Assert.assertEquals(TargetProcessEntityKind.USERSTORY.toString(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.EntityKind.getKey()).getValue());
		Assert.assertEquals(String.valueOf(userStoryDTO.getID()), taskDataRetrieved.getTaskId());
		Assert.assertEquals(userStoryDTO.getName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Name.getKey()).getValue());
		Assert.assertEquals(userStoryDTO.getDescription(), taskDataRetrieved.getRoot()
				.getAttribute(TargetProcessAttribute.Description.getKey()).getValue());
		Assert.assertEquals(userStoryDTO.getPriorityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Priority.getKey()).getValue());

		TaskAttribute stateAttribute = taskDataRetrieved.getRoot().getAttribute(TargetProcessAttribute.State.getKey());
		Assert.assertEquals(String.valueOf(userStoryDTO.getEntityStateID()), stateAttribute.getValue());
		Assert.assertEquals(2, stateAttribute.getOptions().size());
		Assert.assertTrue(stateAttribute.getOptions().containsValue("Open"));
		Assert.assertTrue(stateAttribute.getOptions().containsValue("Done"));

		Assert.assertEquals(userStoryDTO.getPriorityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Priority.getKey()).getValue());

		List<TaskAttribute> commentAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_COMMENT);
		List<TaskAttribute> attachAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_ATTACHMENT);

		Assert.assertNotNull(taskDataRetrieved.getRoot().getMappedAttribute(TaskAttribute.COMMENT_NEW));

		Assert.assertEquals(1, attachAttributes.size());
		Assert.assertEquals(2, commentAttributes.size());

		verify(userStoryServiceStubMock);
		verify(generalUserServiceStubMock);
	}
	
	@Test
	public void testWillCorrectlyPopulateTaskDataFromUserStoryDTO() throws RemoteException {
		expect(userStoryServiceStubMock.getByID(isA(UserStoryServiceStub.GetByID.class))).andReturn(null);
		expect(
				userStoryServiceStubMock
						.retrieveCommentsForUserStory(isA(UserStoryServiceStub.RetrieveCommentsForUserStory.class)))
				.andReturn(null);
		expect(fileServiceStubMock.getFileSize(isA(GetFileSize.class))).andReturn(null);

		replay(fileServiceStubMock);
		expect(
				userStoryServiceStubMock
						.retrieveAttachmentsForUserStory(isA(UserStoryServiceStub.RetrieveAttachmentsForUserStory.class)))
				.andReturn(null);
		expect(generalUserServiceStubMock.getByID(isA(GeneralUserServiceStub.GetByID.class))).andReturn(null).times(6);

		replay(generalUserServiceStubMock);
		replay(userStoryServiceStubMock);

		UserStoryDTO userStoryDTO = factory.getContext().getUserStoryList().get(0);
		entityCollector.accept(factory.getContext().getMyAssignments().getAssignables().getAssignableSimpleDTO()[1],
				taskRepository);

		Assert.assertEquals(1, entityCollector.getTaskDataList().size());
		TaskData taskDataRetrieved = entityCollector.getTaskDataList().get(0);
		Assert.assertEquals(connectorKind, taskDataRetrieved.getConnectorKind());
		Assert.assertEquals(repositoryUrl, taskDataRetrieved.getRepositoryUrl());

		Assert.assertEquals(TargetProcessEntityKind.USERSTORY.toString(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.EntityKind.getKey()).getValue());
		Assert.assertEquals(String.valueOf(userStoryDTO.getID()), taskDataRetrieved.getTaskId());
		Assert.assertEquals(userStoryDTO.getName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Name.getKey()).getValue());
		Assert.assertEquals(userStoryDTO.getDescription(), taskDataRetrieved.getRoot()
				.getAttribute(TargetProcessAttribute.Description.getKey()).getValue());
		Assert.assertEquals(userStoryDTO.getPriorityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Priority.getKey()).getValue());

		TaskAttribute stateAttribute = taskDataRetrieved.getRoot().getAttribute(TargetProcessAttribute.State.getKey());
		Assert.assertEquals(String.valueOf(userStoryDTO.getEntityStateID()), stateAttribute.getValue());
		Assert.assertEquals(2, stateAttribute.getOptions().size());
		Assert.assertTrue(stateAttribute.getOptions().containsValue("Open"));
		Assert.assertTrue(stateAttribute.getOptions().containsValue("Done"));

		Assert.assertEquals(userStoryDTO.getPriorityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Priority.getKey()).getValue());

		List<TaskAttribute> commentAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_COMMENT);
		List<TaskAttribute> attachAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_ATTACHMENT);

		Assert.assertNotNull(taskDataRetrieved.getRoot().getMappedAttribute(TaskAttribute.COMMENT_NEW));

		Assert.assertEquals(1, attachAttributes.size());
		Assert.assertEquals(2, commentAttributes.size());

		verify(userStoryServiceStubMock);
		verify(generalUserServiceStubMock);
	}

	@Test
	public void testWillCorrectlyPopulateTaskDataFromRequestDTO() throws RemoteException {
		expect(requestServiceStubMock.getByID(isA(RequestServiceStub.GetByID.class))).andReturn(null);
		expect(
				requestServiceStubMock
						.retrieveCommentsForRequest(isA(RequestServiceStub.RetrieveCommentsForRequest.class)))
				.andReturn(null);
		expect(generalUserServiceStubMock.getByID(isA(GeneralUserServiceStub.GetByID.class))).andReturn(null).times(6);

		expect(
				requestServiceStubMock
						.retrieveAttachmentsForRequest(isA(RequestServiceStub.RetrieveAttachmentsForRequest.class)))
				.andReturn(null);

		expect(assignableServiceStubMock.retrieveTeamsForAssignable(isA(RetrieveTeamsForAssignable.class))).andReturn(
				null);
		expect(fileServiceStubMock.getFileSize(isA(GetFileSize.class))).andReturn(null);

		replay(fileServiceStubMock);
		replay(requestServiceStubMock);
		replay(generalUserServiceStubMock);
		replay(assignableServiceStubMock);

		RequestDTO requestDTO = factory.getContext().getRequestList().get(0);
		entityCollector.accept(factory.getContext().getMyAssignments().getAssignables().getAssignableSimpleDTO()[3],
				taskRepository);

		Assert.assertEquals(1, entityCollector.getTaskDataList().size());
		TaskData taskDataRetrieved = entityCollector.getTaskDataList().get(0);
		Assert.assertEquals(connectorKind, taskDataRetrieved.getConnectorKind());
		Assert.assertEquals(repositoryUrl, taskDataRetrieved.getRepositoryUrl());
		Assert.assertEquals(String.valueOf(requestDTO.getID()), taskDataRetrieved.getTaskId());

		Assert.assertEquals(TargetProcessEntityKind.REQUEST.toString(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.EntityKind.getKey()).getValue());
		Assert.assertEquals(requestDTO.getName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Name.getKey()).getValue());
		Assert.assertEquals(requestDTO.getDescription(), taskDataRetrieved.getRoot()
				.getAttribute(TargetProcessAttribute.Description.getKey()).getValue());

		TaskAttribute stateAttribute = taskDataRetrieved.getRoot().getAttribute(TargetProcessAttribute.State.getKey());
		Assert.assertEquals(String.valueOf(requestDTO.getEntityStateID()), stateAttribute.getValue());
		Assert.assertEquals(1, stateAttribute.getOptions().size());
		Assert.assertTrue(stateAttribute.getOptions().containsValue("Open"));
		Assert.assertEquals(requestDTO.getPriorityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Priority.getKey()).getValue());

		Assert.assertEquals(requestDTO.getProjectName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.ProjectName.getKey()).getValue());
		Assert.assertEquals("Vasya Pupkin", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Owner.getKey()).getValue());
		Assert.assertEquals("DEV. Truhtanov S.", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.AssignedPeople.getKey()).getValue());
		Assert.assertEquals("dev. Linich M.", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.AssignedPeople2.getKey()).getValue());
		Assert.assertEquals("4 h", taskDataRetrieved.getRoot().getAttribute(TargetProcessAttribute.TimeSpent.getKey())
				.getValue());
		Assert.assertEquals("2 h", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.TimeRemaining.getKey()).getValue());
		Assert.assertEquals(requestDTO.getPriorityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Priority.getKey()).getValue());

		List<TaskAttribute> commentAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_COMMENT);
		List<TaskAttribute> attachAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_ATTACHMENT);

		Assert.assertNotNull(taskDataRetrieved.getRoot().getMappedAttribute(TaskAttribute.COMMENT_NEW));

		Assert.assertEquals(1, attachAttributes.size());
		Assert.assertEquals(2, commentAttributes.size());

		verify(requestServiceStubMock);
		verify(generalUserServiceStubMock);
		verify(assignableServiceStubMock);
	}
	
	@Test
	public void testWillCorrectlyPopulateTaskDataFromRequestToDoDTO() throws RemoteException {
		replay(fileServiceStubMock);
		replay(generalUserServiceStubMock);
		replay(taskServiceStubMock);
		replay(userStoryServiceStubMock);
		replay(requestServiceStubMock);
		replay(assignableServiceStubMock);
		

		MyAssignmentsToDo toDoList = factory.getContext().getMyToDoList();
		AssignableToDoDTO requestDTO = toDoList.getAssignableEntities().getAssignableToDoDTO()[3];
		
		entityCollector.accept(requestDTO,toDoList,taskRepository);
		
		Assert.assertEquals(1, entityCollector.getTaskDataList().size());
		TaskData taskDataRetrieved = entityCollector.getTaskDataList().get(0);
		Assert.assertEquals(connectorKind, taskDataRetrieved.getConnectorKind());
		Assert.assertEquals(repositoryUrl, taskDataRetrieved.getRepositoryUrl());
		Assert.assertEquals(String.valueOf(requestDTO.getID()), taskDataRetrieved.getTaskId());

		Assert.assertEquals(TargetProcessEntityKind.REQUEST.toString(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.EntityKind.getKey()).getValue());
		Assert.assertEquals(requestDTO.getName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Name.getKey()).getValue());
		Assert.assertEquals(requestDTO.getDescription(), taskDataRetrieved.getRoot()
				.getAttribute(TargetProcessAttribute.Description.getKey()).getValue());

		TaskAttribute stateAttribute = taskDataRetrieved.getRoot().getAttribute(TargetProcessAttribute.State.getKey());
		Assert.assertEquals(String.valueOf(requestDTO.getEntityStateID()), stateAttribute.getValue());
		Assert.assertEquals(1, stateAttribute.getOptions().size());
		Assert.assertTrue(stateAttribute.getOptions().containsValue("Open"));
		Assert.assertEquals(requestDTO.getPriorityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Priority.getKey()).getValue());

		Assert.assertEquals(requestDTO.getProjectName (), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.ProjectName.getKey()).getValue());
		Assert.assertEquals("Vasya Pupkin", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Owner.getKey()).getValue());
		Assert.assertEquals("DEV. Truhtanov S.", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.AssignedPeople.getKey()).getValue());
		Assert.assertEquals("dev. Linich M.", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.AssignedPeople2.getKey()).getValue());
		Assert.assertEquals("4 h", taskDataRetrieved.getRoot().getAttribute(TargetProcessAttribute.TimeSpent.getKey())
				.getValue());
		Assert.assertEquals("2 h", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.TimeRemaining.getKey()).getValue());
		Assert.assertEquals(requestDTO.getPriorityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Priority.getKey()).getValue());

		List<TaskAttribute> commentAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_COMMENT);
		List<TaskAttribute> attachAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_ATTACHMENT);

		Assert.assertNotNull(taskDataRetrieved.getRoot().getMappedAttribute(TaskAttribute.COMMENT_NEW));

		Assert.assertEquals(1, attachAttributes.size());
		Assert.assertEquals(2, commentAttributes.size());

		verify(requestServiceStubMock);
		verify(generalUserServiceStubMock);
		verify(assignableServiceStubMock);
	}

	@Test
	public void testWillCorrectlyPopulateTaskDataFromBugDTO() throws RemoteException {
		expect(bugServiceStubMock.getByID(isA(GetByID.class))).andReturn(null);
		expect(bugServiceStubMock.retrieveCommentsForBug(isA(RetrieveCommentsForBug.class))).andReturn(null);
		expect(bugServiceStubMock.retrieveAttachmentsForBug(isA(RetrieveAttachmentsForBug.class))).andReturn(null);
		expect(generalUserServiceStubMock.getByID(isA(GeneralUserServiceStub.GetByID.class))).andReturn(null).times(6);
		expect(assignableServiceStubMock.retrieveTeamsForAssignable(isA(RetrieveTeamsForAssignable.class))).andReturn(
				null);
		expect(fileServiceStubMock.getFileSize(isA(GetFileSize.class))).andReturn(null);

		replay(fileServiceStubMock);
		replay(bugServiceStubMock);
		replay(generalUserServiceStubMock);
		replay(assignableServiceStubMock);

		BugDTO bugDTO = factory.getContext().getBugList().get(0);
		entityCollector.accept(factory.getContext().getMyAssignments().getAssignables().getAssignableSimpleDTO()[0],
				taskRepository);

		Assert.assertEquals(1, entityCollector.getTaskDataList().size());
		TaskData taskDataRetrieved = entityCollector.getTaskDataList().get(0);
		Assert.assertEquals(connectorKind, taskDataRetrieved.getConnectorKind());
		Assert.assertEquals(repositoryUrl, taskDataRetrieved.getRepositoryUrl());
		Assert.assertEquals(String.valueOf(bugDTO.getID()), taskDataRetrieved.getTaskId());

		Assert.assertEquals(TargetProcessEntityKind.BUG.toString(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.EntityKind.getKey()).getValue());
		Assert.assertEquals(bugDTO.getName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Name.getKey()).getValue());
		Assert.assertEquals(bugDTO.getDescription(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Description.getKey()).getValue());

		TaskAttribute stateAttribute = taskDataRetrieved.getRoot().getAttribute(TargetProcessAttribute.State.getKey());
		Assert.assertEquals(String.valueOf(bugDTO.getEntityStateID()), stateAttribute.getValue());
		Assert.assertEquals(2, stateAttribute.getOptions().size());
		Assert.assertTrue(stateAttribute.getOptions().containsValue("Open"));
		Assert.assertTrue(stateAttribute.getOptions().containsValue("Done"));

		Assert.assertEquals(bugDTO.getProjectName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.ProjectName.getKey()).getValue());
		Assert.assertEquals("Vasya Pupkin", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Owner.getKey()).getValue());
		Assert.assertEquals(bugDTO.getPriorityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Priority.getKey()).getValue());

		Assert.assertEquals(bugDTO.getUserStoryName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.UserStory.getKey()).getValue());
		Assert.assertEquals("DEV. Truhtanov S.", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.AssignedPeople.getKey()).getValue());
		Assert.assertEquals("dev. Linich M.", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.AssignedPeople2.getKey()).getValue());
		Assert.assertEquals(bugDTO.getSeverityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Severity.getKey()).getValue());
		Assert.assertEquals("3 h", taskDataRetrieved.getRoot().getAttribute(TargetProcessAttribute.TimeSpent.getKey())
				.getValue());
		Assert.assertEquals("1 h", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.TimeRemaining.getKey()).getValue());
		Assert.assertEquals(bugDTO.getPriorityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Priority.getKey()).getValue());
		// verify comments
		List<TaskAttribute> commentAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_COMMENT);

		List<TaskAttribute> attachAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_ATTACHMENT);

		Assert.assertNotNull(taskDataRetrieved.getRoot().getMappedAttribute(TaskAttribute.COMMENT_NEW));

		Assert.assertEquals(1, attachAttributes.size());
		Assert.assertEquals(2, commentAttributes.size());

		verify(bugServiceStubMock);
		verify(generalUserServiceStubMock);
		verify(assignableServiceStubMock);
	}

	@Test
	public void testWillCorrectlyPopulateTaskDataFromBugToDoDTO() throws RemoteException {		

		MyAssignmentsToDo toDoList = factory.getContext().getMyToDoList();
		AssignableToDoDTO bugDTO = toDoList.getAssignableEntities().getAssignableToDoDTO()[0];
		
		entityCollector.accept(bugDTO,toDoList,taskRepository);		

		Assert.assertEquals(1, entityCollector.getTaskDataList().size());
		TaskData taskDataRetrieved = entityCollector.getTaskDataList().get(0);
		Assert.assertEquals(connectorKind, taskDataRetrieved.getConnectorKind());
		Assert.assertEquals(repositoryUrl, taskDataRetrieved.getRepositoryUrl());
		Assert.assertEquals(String.valueOf(bugDTO.getID()), taskDataRetrieved.getTaskId());

		Assert.assertEquals(TargetProcessEntityKind.BUG.toString(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.EntityKind.getKey()).getValue());
		Assert.assertEquals(bugDTO.getName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Name.getKey()).getValue());
		Assert.assertEquals(bugDTO.getDescription(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Description.getKey()).getValue());

		TaskAttribute stateAttribute = taskDataRetrieved.getRoot().getAttribute(TargetProcessAttribute.State.getKey());
		Assert.assertEquals(String.valueOf(bugDTO.getEntityStateID()), stateAttribute.getValue());
		Assert.assertEquals(2, stateAttribute.getOptions().size());
		Assert.assertTrue(stateAttribute.getOptions().containsValue("Open"));
		Assert.assertTrue(stateAttribute.getOptions().containsValue("Done"));

		Assert.assertEquals(bugDTO.getProjectName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.ProjectName.getKey()).getValue());
		Assert.assertEquals("Vasya Pupkin", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Owner.getKey()).getValue());
		Assert.assertEquals(bugDTO.getPriorityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Priority.getKey()).getValue());

		Assert.assertEquals(bugDTO.getUserStoryName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.UserStory.getKey()).getValue());
		Assert.assertEquals("DEV. Truhtanov S.", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.AssignedPeople.getKey()).getValue());
		Assert.assertEquals("dev. Linich M.", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.AssignedPeople2.getKey()).getValue());
		Assert.assertEquals(bugDTO.getSeverityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Severity.getKey()).getValue());
		Assert.assertEquals("3 h", taskDataRetrieved.getRoot().getAttribute(TargetProcessAttribute.TimeSpent.getKey())
				.getValue());
		Assert.assertEquals("1 h", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.TimeRemaining.getKey()).getValue());
		Assert.assertEquals(bugDTO.getPriorityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Priority.getKey()).getValue());
		// verify comments
		List<TaskAttribute> commentAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_COMMENT);

		List<TaskAttribute> attachAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_ATTACHMENT);

		Assert.assertNotNull(taskDataRetrieved.getRoot().getMappedAttribute(TaskAttribute.COMMENT_NEW));

		Assert.assertEquals(1, attachAttributes.size());
		Assert.assertEquals(2, commentAttributes.size());
	}
	
	@Test
	public void testWillCorrectlyPopulateTaskDataFromCompletedBugToDoDTO() throws RemoteException {		
		
		BugDTO bugDTO = factory.getContext().addClosedBug();
		MyAssignmentsToDo toDoList = factory.getContext().CreateMyAssignmentsToDoFromBug(bugDTO);
		
		entityCollector.accept(toDoList.getAssignableEntities().getAssignableToDoDTO()[0],toDoList,taskRepository);		

		Assert.assertEquals(1, entityCollector.getTaskDataList().size());
		TaskData taskDataRetrieved = entityCollector.getTaskDataList().get(0);
		Assert.assertEquals(connectorKind, taskDataRetrieved.getConnectorKind());
		Assert.assertEquals(repositoryUrl, taskDataRetrieved.getRepositoryUrl());
		Assert.assertEquals(String.valueOf(bugDTO.getID()), taskDataRetrieved.getTaskId());

		Assert.assertEquals(TargetProcessEntityKind.BUG.toString(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.EntityKind.getKey()).getValue());
		Assert.assertEquals(bugDTO.getName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Name.getKey()).getValue());
		Assert.assertEquals(bugDTO.getDescription(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Description.getKey()).getValue());

		TaskAttribute stateAttribute = taskDataRetrieved.getRoot().getAttribute(TargetProcessAttribute.State.getKey());
		Assert.assertEquals(String.valueOf(bugDTO.getEntityStateID()), stateAttribute.getValue());
		Assert.assertEquals(3, stateAttribute.getOptions().size());
		Assert.assertTrue(stateAttribute.getOptions().containsValue("Open"));
		Assert.assertTrue(stateAttribute.getOptions().containsValue("Done"));

		Assert.assertEquals(bugDTO.getProjectName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.ProjectName.getKey()).getValue());
		Assert.assertEquals("Vasya Pupkin", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Owner.getKey()).getValue());
		Assert.assertEquals(bugDTO.getPriorityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Priority.getKey()).getValue());

		Assert.assertEquals(bugDTO.getUserStoryName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.UserStory.getKey()).getValue());
		Assert.assertEquals("DEV. Truhtanov S.", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.AssignedPeople.getKey()).getValue());
		  
		Assert.assertEquals("DEV. Truhtanov S.", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.AssignedPeople.getKey()).getValue());
		Assert.assertEquals("dev. Linich M.", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.AssignedPeople2.getKey()).getValue());
		Assert.assertEquals(bugDTO.getSeverityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Severity.getKey()).getValue());
		Assert.assertEquals("3 h", taskDataRetrieved.getRoot().getAttribute(TargetProcessAttribute.TimeSpent.getKey())
				.getValue());
		Assert.assertEquals("1 h", taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.TimeRemaining.getKey()).getValue());
		Assert.assertEquals(bugDTO.getPriorityName(), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.Priority.getKey()).getValue());
		
		Assert.assertEquals(Long.toString(bugDTO.getEndDate().getTime().getTime()), taskDataRetrieved.getRoot().getAttribute(
				TargetProcessAttribute.EndDate.getKey()).getValue());		
		
		// verify comments
		List<TaskAttribute> commentAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_COMMENT);

		List<TaskAttribute> attachAttributes = taskDataRetrieved.getAttributeMapper().getAttributesByType(
				taskDataRetrieved, TaskAttribute.TYPE_ATTACHMENT);

		Assert.assertNotNull(taskDataRetrieved.getRoot().getMappedAttribute(TaskAttribute.COMMENT_NEW));

		Assert.assertEquals(1, attachAttributes.size());
		Assert.assertEquals(2, commentAttributes.size());
	}
	
	@Test
	public void testWillCorrectlyPopulateTaskDataFromBugDTOWithNullDescription() throws RemoteException {
		expect(bugServiceStubMock.getByID(isA(GetByID.class))).andReturn(null);
		expect(bugServiceStubMock.retrieveCommentsForBug(isA(RetrieveCommentsForBug.class))).andReturn(null);
		expect(bugServiceStubMock.retrieveAttachmentsForBug(isA(RetrieveAttachmentsForBug.class))).andReturn(null);
		expect(generalUserServiceStubMock.getByID(isA(GeneralUserServiceStub.GetByID.class))).andReturn(null).times(6);
		expect(assignableServiceStubMock.retrieveTeamsForAssignable(isA(RetrieveTeamsForAssignable.class))).andReturn(
				null);
		expect(fileServiceStubMock.getFileSize(isA(GetFileSize.class))).andReturn(null);

		replay(fileServiceStubMock);
		replay(bugServiceStubMock);
		replay(generalUserServiceStubMock);
		replay(assignableServiceStubMock);

		BugDTO bugDTO = factory.getContext().getBugList().get(0);
		bugDTO.setDescription(null);
		entityCollector.accept(factory.getContext().getMyAssignments().getAssignables().getAssignableSimpleDTO()[0],
				taskRepository);

		Assert.assertEquals(1, entityCollector.getTaskDataList().size());
		TaskData taskDataRetrieved = entityCollector.getTaskDataList().get(0);
		Assert.assertEquals(connectorKind, taskDataRetrieved.getConnectorKind());
		Assert.assertEquals(repositoryUrl, taskDataRetrieved.getRepositoryUrl());
		Assert.assertEquals(String.valueOf(bugDTO.getID()), taskDataRetrieved.getTaskId());
		Assert.assertEquals("", taskDataRetrieved.getRoot().getAttribute(TargetProcessAttribute.Description.getKey())
				.getValue());

		Assert.assertNotNull(taskDataRetrieved.getRoot().getMappedAttribute(TaskAttribute.COMMENT_NEW));

		verify(bugServiceStubMock);
		verify(generalUserServiceStubMock);
		verify(assignableServiceStubMock);
	}
}
