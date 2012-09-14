package org.eclipse.mylyn.targetprocess.core.tests;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.mylyn.commons.net.AuthenticationCredentials;
import org.eclipse.mylyn.commons.net.AuthenticationType;
import org.eclipse.mylyn.internal.tasks.core.TaskRepositoryLocation;
import org.eclipse.mylyn.targetprocess.core.TargetProcessClient;
import org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.GeneralDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.GetMyToDoList;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IMyAssignmentsServiceStub;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttributeMapper;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.eclipse.mylyn.tasks.core.data.TaskDataCollector;

@SuppressWarnings("restriction")
public class TargetProcessClientTests extends TestCase {

	private final String connectorKind = "connectorKind";
	private final String repositoryUrl = "http://repositoryUrl";
	private TaskRepository taskRepository;
	private TargetProcessClient targetProcessClient;
	private IProgressMonitor monitorMock;
	private IMyAssignmentsServiceStub myAssignmentsServiceStubMock;
	private MockServiceFactory factory;

	@Override
	protected void setUp() throws MalformedURLException {
		factory = new MockServiceFactory();
		taskRepository = new TaskRepository(connectorKind, repositoryUrl);
		AuthenticationCredentials credentials = new AuthenticationCredentials("userName", "password");
		taskRepository.setCredentials(AuthenticationType.REPOSITORY, credentials, true);

		try {
			targetProcessClient = new TargetProcessClient(new TaskRepositoryLocation(taskRepository), credentials,
					factory, false);
		} catch (MalformedURLException e) {
			throw e;
		}

		monitorMock = createMock(IProgressMonitor.class);
		myAssignmentsServiceStubMock = createMock(IMyAssignmentsServiceStub.class);
		factory.setMock(myAssignmentsServiceStubMock);
	}

	public void testWillCorrectlyHandleIfMyAssignableListIsNull() throws RemoteException {
		
		factory.getContext().cleanMyToDoList();
		
		expect(myAssignmentsServiceStubMock.getMyToDoList(isA(GetMyToDoList.class))).andReturn(null);
		replay(myAssignmentsServiceStubMock);

		final ArrayList<TaskData> retrievedData = new ArrayList<TaskData>();
		TaskDataCollector collector = new TaskDataCollector() {

			@Override
			public void accept(TaskData taskData) {
				retrievedData.add(taskData);
			}
		};

		targetProcessClient.performQuery(taskRepository, null, collector, null, monitorMock);

		Assert.assertEquals(0, retrievedData.size());
		verify(myAssignmentsServiceStubMock);
		
	}
	
	public void testWillPerformGetTODOListQuery() throws RemoteException {
		expect(myAssignmentsServiceStubMock.getMyToDoList(isA(GetMyToDoList.class))).andReturn(null);
		replay(myAssignmentsServiceStubMock);

		final ArrayList<TaskData> retrievedData = new ArrayList<TaskData>();
		TaskDataCollector collector = new TaskDataCollector() {

			@Override
			public void accept(TaskData taskData) {
				retrievedData.add(taskData);
			}
		};

		targetProcessClient.performQuery(taskRepository, null, collector, null, monitorMock);

		Assert.assertEquals(4, retrievedData.size());
		verify(myAssignmentsServiceStubMock);
	}
	
	public void testWillGetTaskDataForBug() throws CoreException
	{
		GeneralDTO generalDTO = factory.getContext().getGeneralDTOList().get(0);
		HashSet<String> taskIds = new HashSet<String>();
		taskIds.add(String.valueOf(generalDTO.getID()));
		
		final ArrayList<TaskData> retrievedData = new ArrayList<TaskData>();
		TaskDataCollector collector = new TaskDataCollector() {

			@Override
			public void accept(TaskData taskData) {
				retrievedData.add(taskData);
			}
		};
		
		targetProcessClient.getTaskData(taskIds, collector, new TaskAttributeMapper(taskRepository), monitorMock);
		
		Assert.assertEquals(1, retrievedData.size());
	}
}
