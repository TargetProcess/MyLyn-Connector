package org.eclipse.mylyn.targetprocess.core.tests;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashSet;

import junit.framework.Assert;

import org.eclipse.mylyn.targetprocess.core.Messages;
import org.eclipse.mylyn.targetprocess.core.TargetProcessAttribute;
import org.eclipse.mylyn.targetprocess.core.TargetProcessAttributeMapper;
import org.eclipse.mylyn.targetprocess.core.TargetProcessEntityKind;
import org.eclipse.mylyn.targetprocess.core.entityperformer.EntityPerformerFactory;
import org.eclipse.mylyn.targetprocess.core.entityperformer.EntityValidationException;
import org.eclipse.mylyn.targetprocess.core.entityperformer.EntityWasDeletedException;
import org.eclipse.mylyn.targetprocess.core.entityperformer.IPerformer;
import org.eclipse.mylyn.targetprocess.modules.TargetProcessCredentials;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.CommentServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.BugDTO;
import org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.EntityStateDTO;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RequestDTO;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.TaskDTO;
import org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.UserStoryDTO;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IBugServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.ICommentServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IGeneralUserServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IRequestServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.ITaskServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IUserStoryServiceStub;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class EntityUpdateTest {

	private MockServiceFactory factory;
	private TaskRepository taskRepository;
	private IUserStoryServiceStub userStoryServiceStubMock;
	private IBugServiceStub bugServiceStubMock;
	private ITaskServiceStub taskServiceStubMock;
	private IRequestServiceStub requestServiceStubMock;
	private ICommentServiceStub commentServiceStubMock;
	private TargetProcessCredentials targetProcessCredentials;
	private EntityPerformerFactory entityPerformerFactory;
	private String connectorKind;
	private String repositoryUrl;
	private IGeneralUserServiceStub generalUserServiceStubMock;
	private HashSet<TaskAttribute> changedAttributes;

	
	@Before
	public void setUp() throws MalformedURLException {
		factory = new MockServiceFactory();
		connectorKind = "targetprocess";
		repositoryUrl = "http://localhost";
		taskRepository = new TaskRepository(connectorKind, repositoryUrl);
		taskRepository.setAuthenticationCredentials("userName", "password");
		factory.getBugServiceStub();
		factory.getGeneralUserServiceStub();
		factory.getAssignableServiceStub();
		userStoryServiceStubMock = factory.getUserStoryServiceStub();
		bugServiceStubMock = createMock(IBugServiceStub.class);
		userStoryServiceStubMock = createMock(IUserStoryServiceStub.class);
		taskServiceStubMock = createMock(ITaskServiceStub.class);
		requestServiceStubMock = createMock(IRequestServiceStub.class);
		commentServiceStubMock = createMock(ICommentServiceStub.class);
		generalUserServiceStubMock = createMock(IGeneralUserServiceStub.class);

		targetProcessCredentials = new TargetProcessCredentials(new URL(repositoryUrl), "login", "password", false);

		factory.setMock(generalUserServiceStubMock);
		factory.setMock(bugServiceStubMock);
		factory.setMock(userStoryServiceStubMock);
		factory.setMock(taskServiceStubMock);
		factory.setMock(commentServiceStubMock);
		factory.setMock(requestServiceStubMock);

		entityPerformerFactory = new EntityPerformerFactory(targetProcessCredentials);

		TaskData taskData = new TaskData(new TargetProcessAttributeMapper(taskRepository), connectorKind,
				repositoryUrl, "0");

		changedAttributes = new HashSet<TaskAttribute>();
		TaskAttribute descriptionAttribute = taskData.getRoot().createAttribute(
				TargetProcessAttribute.Description.getKey());
		TaskAttribute stateAttribute = taskData.getRoot().createAttribute(
				TargetProcessAttribute.State.getKey());
		changedAttributes.add(descriptionAttribute);
		changedAttributes.add(stateAttribute);
	}

	@Test
	public void testUpdateBug() throws RemoteException, EntityValidationException, EntityWasDeletedException {
		expect(bugServiceStubMock.getByID(isA(BugServiceStub.GetByID.class))).andReturn(null);

		expect(bugServiceStubMock.update(isA(BugServiceStub.Update.class))).andReturn(null);

		replay(bugServiceStubMock);

		String description = "New\n description";
		String name = "New name";
		String entityStateName = "Done";

		BugDTO bugDTO = factory.getContext().getBugList().get(0);

		EntityStateDTO entityState = factory.getContext().retrieveEntityStateBy(
				EntityPerformerFactory.TP_BUSINESS_OBJECTS_BUG, entityStateName);
		TaskData taskData = createTaskData(name, description, "", bugDTO.getID(), entityState.getID(),
				TargetProcessEntityKind.BUG);

		IPerformer performer = entityPerformerFactory.createPerformer(factory, taskData);

		performer.update(taskData, changedAttributes);

		Assert.assertEquals(description, bugDTO.getDescription());
		Assert.assertEquals(name, bugDTO.getName());

		Assert.assertEquals(entityState.getID(), bugDTO.getEntityStateID());
		Assert.assertEquals(Messages.TargetProcessEntityPerformer_Default_Comment_On_Change_State, bugDTO.getCommentOnChangingState());

		verify(bugServiceStubMock);
	}

	private TaskData createTaskData(String name, String description, String newComment, int entityId,
			int entityStateId, TargetProcessEntityKind targetProcessEntityKind) {
		TaskData taskData = new TaskData(new TargetProcessAttributeMapper(taskRepository), connectorKind,
				repositoryUrl, String.valueOf(entityId));

		addAttribute(newComment, taskData, TargetProcessAttribute.NewComment);
		addAttribute(name, taskData, TargetProcessAttribute.Name);
		addAttribute(description, taskData, TargetProcessAttribute.Description);
		addAttribute(String.valueOf(entityStateId), taskData, TargetProcessAttribute.State);

		addAttribute(targetProcessEntityKind.toString(), taskData, TargetProcessAttribute.EntityKind);
		return taskData;
	}

	@Test
	public void testAddCommentToBug() throws RemoteException, EntityValidationException, EntityWasDeletedException {
		expect(bugServiceStubMock.getByID(isA(BugServiceStub.GetByID.class))).andReturn(null);
		expect(bugServiceStubMock.update(isA(BugServiceStub.Update.class))).andReturn(null);
		expect(commentServiceStubMock.create(isA(CommentServiceStub.Create.class))).andReturn(null);

		replay(bugServiceStubMock);
		replay(commentServiceStubMock);

		BugDTO bugDTO = factory.getContext().getBugList().get(0);

		String description = "New\n description";
		String name = "New name";

		String entityStateName = "Done";
		EntityStateDTO entityState = factory.getContext().retrieveEntityStateBy(
				EntityPerformerFactory.TP_BUSINESS_OBJECTS_BUG, entityStateName);

		TaskData taskData = createTaskData(name, description, "comment", bugDTO.getID(), entityState.getID(),
				TargetProcessEntityKind.BUG);
		IPerformer performer = entityPerformerFactory.createPerformer(factory, taskData);

		performer.update(taskData, changedAttributes);

		Assert.assertEquals(description, bugDTO.getDescription());
		Assert.assertEquals(name, bugDTO.getName());

		Assert.assertEquals(entityState.getID(), bugDTO.getEntityStateID());
		Assert.assertEquals(Messages.TargetProcessEntityPerformer_Default_Comment_On_Change_State, bugDTO.getCommentOnChangingState());

		verify(bugServiceStubMock);
		verify(commentServiceStubMock);
	}

	@Test
	public void testUpdateUserStory() throws RemoteException, EntityValidationException, EntityWasDeletedException {
		expect(userStoryServiceStubMock.getByID(isA(UserStoryServiceStub.GetByID.class))).andReturn(null);
		expect(userStoryServiceStubMock.update(isA(UserStoryServiceStub.Update.class))).andReturn(null);

		replay(userStoryServiceStubMock);

		UserStoryDTO userStoryDTO = factory.getContext().getUserStoryList().get(0);

		String description = "New user story\n description";
		String name = "New user story name";

		String entityStateName = "Done";
		EntityStateDTO entityState = factory.getContext().retrieveEntityStateBy(
				EntityPerformerFactory.TP_BUSINESS_OBJECTS_USER_STORY, entityStateName);

		TaskData taskData = createTaskData(name, description, "", userStoryDTO.getID(), entityState.getID(),
				TargetProcessEntityKind.USERSTORY);

		IPerformer performer = entityPerformerFactory.createPerformer(factory, taskData);
		performer.update(taskData, changedAttributes);

		Assert.assertEquals(description, userStoryDTO.getDescription());
		Assert.assertEquals(name, userStoryDTO.getName());

		Assert.assertEquals(entityState.getID(), userStoryDTO.getEntityStateID());
		Assert.assertEquals(Messages.TargetProcessEntityPerformer_Default_Comment_On_Change_State, userStoryDTO.getCommentOnChangingState());

		verify(userStoryServiceStubMock);
	}

	@Test
	public void testAddCommentToUserStory() throws RemoteException, EntityValidationException,
			EntityWasDeletedException {
		expect(userStoryServiceStubMock.getByID(isA(UserStoryServiceStub.GetByID.class))).andReturn(null);
		expect(userStoryServiceStubMock.update(isA(UserStoryServiceStub.Update.class))).andReturn(null);
		expect(commentServiceStubMock.create(isA(CommentServiceStub.Create.class))).andReturn(null);

		replay(userStoryServiceStubMock);
		replay(commentServiceStubMock);

		UserStoryDTO userStoryDTO = factory.getContext().getUserStoryList().get(0);

		String description = "New user story\n description";
		String name = "New user story name";

		String entityStateName = "Done";
		EntityStateDTO entityState = factory.getContext().retrieveEntityStateBy(
				EntityPerformerFactory.TP_BUSINESS_OBJECTS_USER_STORY, entityStateName);

		TaskData taskData = createTaskData(name, description, "comment", userStoryDTO.getID(), entityState.getID(),
				TargetProcessEntityKind.USERSTORY);

		IPerformer performer = entityPerformerFactory.createPerformer(factory, taskData);
		performer.update(taskData, changedAttributes);

		Assert.assertEquals(description, userStoryDTO.getDescription());
		Assert.assertEquals(name, userStoryDTO.getName());

		Assert.assertEquals(entityState.getID(), userStoryDTO.getEntityStateID());
		Assert.assertEquals(Messages.TargetProcessEntityPerformer_Default_Comment_On_Change_State, userStoryDTO.getCommentOnChangingState());

		verify(userStoryServiceStubMock);
		verify(commentServiceStubMock);
	}

	@Test
	public void testUpdateTask() throws RemoteException, EntityValidationException, EntityWasDeletedException {
		expect(taskServiceStubMock.getByID(isA(TaskServiceStub.GetByID.class))).andReturn(null);
		expect(taskServiceStubMock.update(isA(TaskServiceStub.Update.class))).andReturn(null);

		replay(taskServiceStubMock);

		TaskDTO taskDTO = factory.getContext().getTaskList().get(0);

		String description = "New task\n description";
		String name = "New task name";
		String entityStateName = "Open";

		EntityStateDTO entityState = factory.getContext().retrieveEntityStateBy(
				EntityPerformerFactory.TP_BUSINESS_OBJECTS_TASK, entityStateName);

		TaskData taskData = createTaskData(name, description, "", taskDTO.getID(), entityState.getID(),
				TargetProcessEntityKind.TASK);

		IPerformer performer = entityPerformerFactory.createPerformer(factory, taskData);
		performer.update(taskData, changedAttributes);

		Assert.assertEquals(description, taskDTO.getDescription());
		Assert.assertEquals(name, taskDTO.getName());

		Assert.assertEquals(entityState.getID(), taskDTO.getEntityStateID());
		Assert.assertEquals(Messages.TargetProcessEntityPerformer_Default_Comment_On_Change_State, taskDTO.getCommentOnChangingState());

		verify(taskServiceStubMock);
	}

	@Test
	public void testAddCommentToTask() throws RemoteException, EntityValidationException, EntityWasDeletedException {
		expect(taskServiceStubMock.getByID(isA(TaskServiceStub.GetByID.class))).andReturn(null);
		expect(taskServiceStubMock.update(isA(TaskServiceStub.Update.class))).andReturn(null);
		expect(commentServiceStubMock.create(isA(CommentServiceStub.Create.class))).andReturn(null);

		replay(taskServiceStubMock);
		replay(commentServiceStubMock);

		TaskDTO taskDTO = factory.getContext().getTaskList().get(0);

		String description = "New task\n description";
		String name = "New task name";

		String entityStateName = "Open";
		EntityStateDTO entityState = factory.getContext().retrieveEntityStateBy(
				EntityPerformerFactory.TP_BUSINESS_OBJECTS_TASK, entityStateName);

		TaskData taskData = createTaskData(name, description, "comment", taskDTO.getID(), entityState.getID(),
				TargetProcessEntityKind.TASK);

		IPerformer performer = entityPerformerFactory.createPerformer(factory, taskData);
		performer.update(taskData, changedAttributes);

		Assert.assertEquals(description, taskDTO.getDescription());
		Assert.assertEquals(name, taskDTO.getName());

		Assert.assertEquals(entityState.getID(), taskDTO.getEntityStateID());
		Assert.assertEquals(Messages.TargetProcessEntityPerformer_Default_Comment_On_Change_State, taskDTO.getCommentOnChangingState());

		verify(taskServiceStubMock);
		verify(commentServiceStubMock);
	}

	@Test
	public void testUpdateRequest() throws RemoteException, EntityValidationException, EntityWasDeletedException {
		expect(requestServiceStubMock.getByID(isA(RequestServiceStub.GetByID.class))).andReturn(null);
		expect(requestServiceStubMock.update(isA(RequestServiceStub.Update.class))).andReturn(null);

		replay(requestServiceStubMock);

		RequestDTO requestDTO = factory.getContext().getRequestList().get(0);

		String description = "New request\n description";
		String name = "New request name";

		String entityStateName = "Open";
		EntityStateDTO entityState = factory.getContext().retrieveEntityStateBy(
				EntityPerformerFactory.TP_BUSINESS_OBJECTS_REQUEST, entityStateName);

		TaskData taskData = createTaskData(name, description, "", requestDTO.getID(), entityState.getID(),
				TargetProcessEntityKind.REQUEST);
		IPerformer performer = entityPerformerFactory.createPerformer(factory, taskData);
		performer.update(taskData, changedAttributes);

		Assert.assertEquals(description, requestDTO.getDescription());
		Assert.assertEquals(name, requestDTO.getName());

		Assert.assertEquals(entityState.getID(), requestDTO.getEntityStateID());
		Assert.assertEquals(Messages.TargetProcessEntityPerformer_Default_Comment_On_Change_State, requestDTO.getCommentOnChangingState());

		verify(requestServiceStubMock);
	}

	@Test
	public void testAddCommentToRequest() throws RemoteException, EntityValidationException, EntityWasDeletedException {
		expect(requestServiceStubMock.getByID(isA(RequestServiceStub.GetByID.class))).andReturn(null);
		expect(requestServiceStubMock.update(isA(RequestServiceStub.Update.class))).andReturn(null);
		expect(commentServiceStubMock.create(isA(CommentServiceStub.Create.class))).andReturn(null);

		replay(requestServiceStubMock);
		replay(commentServiceStubMock);

		RequestDTO requestDTO = factory.getContext().getRequestList().get(0);

		String description = "New request\n description";
		String name = "New request name";

		String entityStateName = "Open";
		EntityStateDTO entityState = factory.getContext().retrieveEntityStateBy(
				EntityPerformerFactory.TP_BUSINESS_OBJECTS_REQUEST, entityStateName);

		TaskData taskData = createTaskData(name, description, "comment", requestDTO.getID(), entityState.getID(),
				TargetProcessEntityKind.REQUEST);

		addAttribute(TargetProcessEntityKind.REQUEST.toString(), taskData, TargetProcessAttribute.EntityKind);

		IPerformer performer = entityPerformerFactory.createPerformer(factory, taskData);
		performer.update(taskData, changedAttributes);

		Assert.assertEquals(description, requestDTO.getDescription());
		Assert.assertEquals(name, requestDTO.getName());

		Assert.assertEquals(entityState.getID(), requestDTO.getEntityStateID());
		Assert.assertEquals(Messages.TargetProcessEntityPerformer_Default_Comment_On_Change_State, requestDTO.getCommentOnChangingState());

		verify(requestServiceStubMock);
		verify(commentServiceStubMock);
	}

	private TaskAttribute addAttribute(String value, TaskData taskData, TargetProcessAttribute targetProcessAttribute) {
		TaskAttribute taskAttribute = taskData.getRoot().createAttribute(targetProcessAttribute.getKey());
		taskAttribute.getMetaData().defaults().setReadOnly(targetProcessAttribute.isReadOnly()).setLabel(
				targetProcessAttribute.toString()).setType(targetProcessAttribute.getType()).setReadOnly(
				targetProcessAttribute.isReadOnly());

		taskAttribute.setValue(value);
		return taskAttribute;
	}

	@Test
	public void willNotUpdateBugButAddNewCommentIfNothingHasChanged() throws RemoteException,
			EntityValidationException, EntityWasDeletedException {
		expect(bugServiceStubMock.getByID(isA(BugServiceStub.GetByID.class))).andReturn(null);
		expect(commentServiceStubMock.create(isA(CommentServiceStub.Create.class))).andReturn(null);
		replay(bugServiceStubMock);
		replay(commentServiceStubMock);

		BugDTO bugDTO = factory.getContext().getBugList().get(0);

		String entityStateName = "Open";
		EntityStateDTO entityState = factory.getContext().retrieveEntityStateBy(
				EntityPerformerFactory.TP_BUSINESS_OBJECTS_BUG, entityStateName);
		TaskData taskData = createTaskData("name", "description", "newComment", bugDTO.getID(), entityState.getID(),
				TargetProcessEntityKind.BUG);

		IPerformer performer = entityPerformerFactory.createPerformer(factory, taskData);

		changedAttributes.clear();
		String oldName = bugDTO.getName();
		String oldDescription = bugDTO.getDescription();

		performer.update(taskData, changedAttributes);

		Assert.assertEquals(oldDescription, bugDTO.getDescription());
		Assert.assertEquals(oldName, bugDTO.getName());

		Assert.assertEquals(entityState.getID(), bugDTO.getEntityStateID());

		verify(bugServiceStubMock);
		verify(commentServiceStubMock);
	}
}
