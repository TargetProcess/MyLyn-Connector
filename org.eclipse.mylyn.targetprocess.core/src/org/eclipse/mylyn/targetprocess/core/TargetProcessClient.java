package org.eclipse.mylyn.targetprocess.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;

import org.apache.commons.httpclient.HttpClient;
import org.eclipse.core.net.proxy.IProxyData;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.mylyn.commons.net.AbstractWebLocation;
import org.eclipse.mylyn.commons.net.AuthenticationCredentials;
import org.eclipse.mylyn.commons.net.Policy;
import org.eclipse.mylyn.commons.net.WebUtil;
import org.eclipse.mylyn.targetprocess.core.entityperformer.EntityPerformerFactory;
import org.eclipse.mylyn.targetprocess.core.entityperformer.EntityValidationException;
import org.eclipse.mylyn.targetprocess.core.entityperformer.EntityWasDeletedException;
import org.eclipse.mylyn.targetprocess.core.entityperformer.IPerformer;
import org.eclipse.mylyn.targetprocess.modules.Authenticator;
import org.eclipse.mylyn.targetprocess.modules.IServiceFactory;
import org.eclipse.mylyn.targetprocess.modules.TPAuthenticationException;
import org.eclipse.mylyn.targetprocess.modules.TargetProcessCredentials;
import org.eclipse.mylyn.targetprocess.modules.services.FileServiceStub.DownloadChunk;
import org.eclipse.mylyn.targetprocess.modules.services.FileServiceStub.DownloadChunkResponse;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.ArrayOfAssignableToDoDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AssignableToDoDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.GetMyAssignmentById;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.GetMyToDoList;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.GetMyToDoListResponse;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.MyAssignmentsToDo;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IFileServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IMyAssignmentsServiceStub;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.RepositoryResponse;
import org.eclipse.mylyn.tasks.core.RepositoryResponse.ResponseKind;
import org.eclipse.mylyn.tasks.core.RepositoryStatus;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskAttachmentSource;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskAttributeMapper;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.eclipse.mylyn.tasks.core.data.TaskDataCollector;

public class TargetProcessClient {

	protected static final String USER_AGENT = "TargetProcessConnector"; //$NON-NLS-1$
	private static final int MAX_RETRIEVED_PER_QUERY = 50;

	private URL repositoryUrl;
	private final HttpClient httpClient = new HttpClient(WebUtil.getConnectionManager());
	private RepositoryConfiguration repositoryConfiguration;
	private AuthenticationCredentials credentials;
	private IServiceFactory serviceFactory;
	private boolean isWindowsAuthentication;

	public TargetProcessClient(AbstractWebLocation location, AuthenticationCredentials credentials,
			IServiceFactory serviceFactory, boolean isWindowsAuthentication) throws MalformedURLException {
		this.serviceFactory = serviceFactory;
		repositoryUrl = new URL(location.getUrl());
		this.credentials = credentials;
		this.isWindowsAuthentication = isWindowsAuthentication;
		location.getProxyForHost(location.getUrl(), IProxyData.HTTP_PROXY_TYPE);
		WebUtil.configureHttpClient(httpClient, USER_AGENT);
	}

	public void validate(IProgressMonitor monitor) throws IOException, CoreException, TPAuthenticationException {
		monitor = Policy.monitorFor(monitor);

		TargetProcessCredentials targetProcessCredentials = TargetProcessCredentialsFactory
				.createTargetProcessCredentials(repositoryUrl, credentials, isWindowsAuthentication);
		Authenticator authenticator = new Authenticator(serviceFactory);
		authenticator.authenticate(targetProcessCredentials);
	}

	public void setRepositoryConfiguration(RepositoryConfiguration repositoryConfiguration) {
		this.repositoryConfiguration = repositoryConfiguration;
	}

	public RepositoryConfiguration getRepositoryConfiguration() {
		return repositoryConfiguration;
	}

	public void performQuery(TaskRepository repository, IRepositoryQuery query, TaskDataCollector resultCollector,
			TaskAttributeMapper mapper, IProgressMonitor monitor) throws RemoteException {
		TargetProcessCredentials targetProcessCredentials = TargetProcessCredentialsFactory
				.createTargetProcessCredentials(repository);

		IMyAssignmentsServiceStub myAssigmentService = serviceFactory
				.getMyAssignmentServiceStub(targetProcessCredentials);

		GetMyToDoList toDoListParam = new GetMyToDoList();
		GetMyToDoListResponse myAssigmentsResponse = myAssigmentService.getMyToDoList(toDoListParam);
		MyAssignmentsToDo myAssignments = myAssigmentsResponse.getGetMyToDoListResult();

		ArrayOfAssignableToDoDTO myAssignables = myAssignments.getAssignableEntities();

		if (myAssignables != null) {
			AssignableToDoDTO[] assignableSimpleDTOs = myAssignables.getAssignableToDoDTO();

			if (assignableSimpleDTOs != null) {
				EntityCollector entityCollector = new EntityCollector(serviceFactory, targetProcessCredentials);

				for (AssignableToDoDTO assignableSimpleDTO : assignableSimpleDTOs) {
					entityCollector.accept(assignableSimpleDTO, myAssignments, repository);
				}

				entityCollector.exportTo(resultCollector);
			}
		}
	}

	private Set<String> getIdsToRetrieve(Set<String> taskIds) {
		Set<String> idsToRetrieve = new HashSet<String>();
		for (String id : taskIds) {
			idsToRetrieve.add(id);
			if (idsToRetrieve.size() >= MAX_RETRIEVED_PER_QUERY) {
				break;
			}
		}
		return idsToRetrieve;
	}

	private MyAssignmentsToDo getMyAssignment(String taskId, TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		IMyAssignmentsServiceStub myAssigmentService = serviceFactory
				.getMyAssignmentServiceStub(targetProcessCredentials);

		GetMyAssignmentById getMyAssignmentById = new GetMyAssignmentById();
		getMyAssignmentById.setId(Integer.parseInt(taskId));

		return myAssigmentService.getMyAssignmentById(getMyAssignmentById).getGetMyAssignmentByIdResult();
	}

	public void getTaskData(Set<String> taskIds, TaskDataCollector collector2, TaskAttributeMapper attributeMapper,
			IProgressMonitor monitor) throws CoreException {
		if (repositoryConfiguration == null) {
			try {
				getRepositoryConfiguration(new SubProgressMonitor(monitor, 1));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TargetProcessCorePlugin.addRepositoryConfiguration(repositoryConfiguration);
		}

		HashMap<String, TaskData> taskDataMap = new HashMap<String, TaskData>();
		// make a copy to modify set
		taskIds = new HashSet<String>(taskIds);
		while (taskIds.size() > 0) {

			try {
				Set<String> idsToRetrieve = getIdsToRetrieve(taskIds);

				if (idsToRetrieve.size() == 0) {
					return;
				}

				for (String taskId : idsToRetrieve) {
					try {
						TaskRepository repository = attributeMapper.getTaskRepository();
						TargetProcessCredentials targetProcessCredentials = TargetProcessCredentialsFactory
								.createTargetProcessCredentials(repository);

						MyAssignmentsToDo result = getMyAssignment(taskId, targetProcessCredentials);

						boolean entityNotFound = true;
						if (result != null) {
							ArrayOfAssignableToDoDTO assignableEntities = result.getAssignableEntities();
							if (assignableEntities != null) {
								AssignableToDoDTO[] assignableToDoDTO = assignableEntities.getAssignableToDoDTO();
								if (assignableToDoDTO != null && assignableToDoDTO.length > 0) {
									entityNotFound = false;

									EntityCollector entityCollector = new EntityCollector(serviceFactory,
											targetProcessCredentials);

									TaskData taskData = entityCollector
											.accept(assignableToDoDTO[0], result, repository);
									taskDataMap.put(taskId, taskData);
									collector2.accept(taskData);
								}
							}
						}

						taskIds.remove(taskId);

						if (entityNotFound) {
							throw new CoreException(new TargetProcessRepositoryStatus(IStatus.ERROR,
									TargetProcessCorePlugin.ID_PLUGIN,
									TargetProcessRepositoryStatus.ENTITY_NOT_FOUND_WHILE_GETTASKDATA,
									repositoryUrl.toString()));
						}
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (RemoteException e) {
						if (e.getMessage().contains("Tp.Integration.Common.EntityNotFoundException")) {
							throw new CoreException(new TargetProcessRepositoryStatus(IStatus.ERROR,
									TargetProcessCorePlugin.ID_PLUGIN,
									TargetProcessRepositoryStatus.ENTITY_NOT_FOUND_WHILE_GETTASKDATA,
									repositoryUrl.toString(), e));
						}
						if (e.getMessage().contains("Tp.Integration.Common.AccessDeniedException")) {
							throw new CoreException(new TargetProcessRepositoryStatus(IStatus.ERROR,
									TargetProcessCorePlugin.ID_PLUGIN,
									TargetProcessRepositoryStatus.ENTITY_ACCESS_DENIED_WHILE_GETTASKDATA,
									repositoryUrl.toString(), e));
						}
						throw new CoreException(new TargetProcessRepositoryStatus(IStatus.ERROR,
								TargetProcessCorePlugin.ID_PLUGIN, RepositoryStatus.ERROR_NETWORK,
								repositoryUrl.toString(), e));
					}

				}

			} finally {
			}
		}
	}

	public RepositoryConfiguration getRepositoryConfiguration(IProgressMonitor monitor) throws IOException,
			CoreException {

		RepositoryConfigurationFactory configFactory = new RepositoryConfigurationFactory();

		repositoryConfiguration = configFactory.getConfiguration();
		if (repositoryConfiguration != null) {
			repositoryConfiguration.setRepositoryUrl(repositoryUrl.toString());
			return repositoryConfiguration;
		}

		return null;
	}

	public RepositoryResponse postTaskData(TaskData taskData, Set<TaskAttribute> changedAttributes,
			IProgressMonitor monitor) throws RemoteException, EntityValidationException, EntityWasDeletedException {

		TargetProcessCredentials targetProcessCredentials = TargetProcessCredentialsFactory
				.createTargetProcessCredentials(taskData.getAttributeMapper().getTaskRepository());

		EntityPerformerFactory entityPerformerFactory = new EntityPerformerFactory(targetProcessCredentials);
		IPerformer performer = entityPerformerFactory.createPerformer(serviceFactory, taskData);
		try {
			performer.update(taskData, changedAttributes);
		} catch (RemoteException e) {
			if (e.getMessage().contains("Tp.Integration.Common.EntityNotFoundException")
					|| e.getMessage().contains("is not found")) {
				throw new EntityWasDeletedException(performer.getEntityKind().getReadableName());
			}
			throw e;
		}

		return new RepositoryResponse(ResponseKind.TASK_UPDATED, taskData.getTaskId());
	}

	public void postAttachment(String taskId, String comment, AbstractTaskAttachmentSource source,
			TaskAttribute attachmentAttribute, IProgressMonitor monitor) throws IOException {
	}

	public void getAttachmentData(TaskRepository repository, String attachmentId, Long attachmentLength,
			OutputStream out, IProgressMonitor monitor) throws IOException {
		TargetProcessCredentials targetProcessCredentials = TargetProcessCredentialsFactory
				.createTargetProcessCredentials(repository);
		IFileServiceStub fileServiceStub = serviceFactory.getFileServiceStub(targetProcessCredentials);
		DownloadChunk downloadChunk = new DownloadChunk();
		downloadChunk.setFileName(attachmentId);
		downloadChunk.setOffset(0);
		downloadChunk.setBufferSize(attachmentLength.intValue());

		DownloadChunkResponse response = fileServiceStub.downloadChunk(downloadChunk);
		DataHandler result = response.getDownloadChunkResult();
		if (result != null) {
			DataSource dataSource = result.getDataSource();
			InputStream resultStream = dataSource.getInputStream();

			byte[] bytes = new byte[attachmentLength.intValue()];
			resultStream.read(bytes);
			out.write(bytes);
		}
	}
}
