package org.eclipse.mylyn.targetprocess.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.mylyn.commons.net.AuthenticationCredentials;
import org.eclipse.mylyn.commons.net.AuthenticationType;
import org.eclipse.mylyn.targetprocess.modules.IServiceFactory;
import org.eclipse.mylyn.targetprocess.modules.TargetProcessCredentials;
import org.eclipse.mylyn.tasks.core.AbstractRepositoryConnector;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.ITask.PriorityLevel;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskAttachmentHandler;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskDataHandler;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskAttributeMapper;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.eclipse.mylyn.tasks.core.data.TaskDataCollector;
import org.eclipse.mylyn.tasks.core.data.TaskMapper;
import org.eclipse.mylyn.tasks.core.sync.ISynchronizationSession;

public class TargetProcessRepositoryConnector extends AbstractRepositoryConnector {

	private final TargetProcessTaskDataHandler taskDataHandler = new TargetProcessTaskDataHandler(this);
	private static final String CONNECTOR_LABEL = "TargetProcess";
	private final TargetProcessTaskAttachmentHandler attachmentHandler = new TargetProcessTaskAttachmentHandler(this);

	@Override
	public boolean canCreateNewTask(TaskRepository repository) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canCreateTaskFromKey(TaskRepository repository) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getConnectorKind() {
		// TODO Auto-generated method stub
		return TargetProcessCorePlugin.CONNECTOR_KIND;
	}

	@Override
	public String getLabel() {
		return CONNECTOR_LABEL;
	}

	@Override
	public AbstractTaskAttachmentHandler getTaskAttachmentHandler() {
		return attachmentHandler;
	}

	@Override
	public String getRepositoryUrlFromTaskUrl(String taskFullUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaskData getTaskData(TaskRepository taskRepository, String taskId, IProgressMonitor monitor)
			throws CoreException {
		// TODO Auto-generated method stub
		return taskDataHandler.getTaskData(taskRepository, taskId, monitor);
	}

	public String getTaskKindLabel(ITask task) {
		return "tra ta ta";
	}

	@Override
	public String getTaskIdFromTaskUrl(String taskFullUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaskMapper getTaskMapping(final TaskData taskData) {
		return new TaskMapper(taskData) {
			@Override
			public Date getModificationDate() {

				return super.getModificationDate();
			}

			@Override
			public String getTaskKey() {
				TaskAttribute attribute = getTaskData().getRoot().getAttribute(TargetProcessAttribute.ID.getKey());
				if (attribute != null) {
					return attribute.getValue();
				}
				return super.getTaskKey();
			}

			@Override
			public PriorityLevel getPriorityLevel() {
				TaskAttribute attribute = getTaskData().getRoot()
						.getAttribute(TargetProcessAttribute.Priority.getKey());
				TaskRepository taskRepository = getTaskData().getAttributeMapper().getTaskRepository();

				AuthenticationCredentials credentials = taskRepository.getCredentials(AuthenticationType.REPOSITORY);

				if (attribute != null) {
					IServiceFactory serviceFactory = TargetProcessCorePlugin.getDefault().getServiceFactory();
					TargetProcessCredentials targetProcessCredentials = null;
					try {
						targetProcessCredentials = new TargetProcessCredentials(
								new URL(taskData.getRepositoryUrl()),
								credentials.getUserName(),
								credentials.getPassword(),
								taskRepository.getProperty(TargetProcessCorePlugin.IS_WINDOWS_AUTHENTICATION_KEY) != null);

					} catch (MalformedURLException e) {
						e.printStackTrace();
					}

					try {
						return new PriorityConverter(serviceFactory.getPriorityServiceStub(targetProcessCredentials),
								taskData.getRepositoryUrl()).getMylynPriorityFromTaskData(taskData);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
				return PriorityLevel.getDefault();
			}

			@Override
			public String getTaskUrl() {
				return taskData.getRepositoryUrl();
			}
		};
	}

	@Override
	public String getTaskUrl(String repositoryUrl, String taskId) {

		return null;
	}

	@Override
	public boolean hasTaskChanged(TaskRepository taskRepository, ITask task, TaskData taskData) {
		TaskMapper scheme = getTaskMapping(taskData);
		Date repositoryDate = scheme.getModificationDate();
		Date localDate = task.getModificationDate();
		if (repositoryDate != null && repositoryDate.equals(localDate)) {
			return false;
		}
		return true;
	}

	@Override
	public IStatus performQuery(TaskRepository repository, IRepositoryQuery query, TaskDataCollector resultCollector,
			ISynchronizationSession session, IProgressMonitor monitor) {

		try {
			monitor.beginTask(Messages.TargetProcessRepositoryConnector_running_query, IProgressMonitor.UNKNOWN);

			TargetProcessClient client = getClientManager().getClient(repository, new SubProgressMonitor(monitor, 1));

			TaskAttributeMapper mapper = getTaskDataHandler().getAttributeMapper(repository);

			client.performQuery(repository, query, resultCollector, mapper, monitor);
			return Status.OK_STATUS;

		} catch (CoreException e) {
			return e.getStatus();
		} catch (RemoteException e) {
			return new Status(IStatus.ERROR, TargetProcessCorePlugin.ID_PLUGIN, IStatus.ERROR,
					Messages.TargetProcessClient_Server_Connect_to_server_failed + e.toString(), e);
		} catch (Exception e) {
			return new Status(IStatus.ERROR, TargetProcessCorePlugin.ID_PLUGIN, IStatus.ERROR,
					String.format(Messages.TargetProcessStatus_errorInternal), e);
		} finally {
			monitor.done();
		}
	}

	@Override
	public void updateRepositoryConfiguration(TaskRepository taskRepository, IProgressMonitor monitor)
			throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTaskFromTaskData(TaskRepository taskRepository, ITask task, TaskData taskData) {
		TaskMapper scheme = getTaskMapping(taskData);
		scheme.applyTo(task);

		TaskAttribute attributeStatus = taskData.getRoot().getMappedAttribute(TargetProcessAttribute.EndDate.getKey());
		if (attributeStatus != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.getTime().setTime(Long.valueOf(attributeStatus.getValue()));
			task.setCompletionDate(calendar.getTime());
		} else {
			task.setCompletionDate(null);
		}

	}

	public TargetProcessClientManager getClientManager() {
		// TODO Auto-generated method stub
		return new TargetProcessClientManager();
	}

	@Override
	public AbstractTaskDataHandler getTaskDataHandler() {
		return taskDataHandler;
	}
}
