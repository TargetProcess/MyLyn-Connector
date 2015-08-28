package org.eclipse.mylyn.targetprocess.core;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.mylyn.commons.net.Policy;
import org.eclipse.mylyn.targetprocess.core.entityperformer.EntityValidationException;
import org.eclipse.mylyn.targetprocess.core.entityperformer.EntityWasDeletedException;
import org.eclipse.mylyn.tasks.core.ITaskMapping;
import org.eclipse.mylyn.tasks.core.RepositoryResponse;
import org.eclipse.mylyn.tasks.core.RepositoryStatus;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskDataHandler;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskAttributeMapper;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.eclipse.mylyn.tasks.core.data.TaskDataCollector;

public class TargetProcessTaskDataHandler extends AbstractTaskDataHandler {

	private TargetProcessRepositoryConnector connector;

	public TargetProcessTaskDataHandler(TargetProcessRepositoryConnector connector) {
		this.connector = connector;
	}

	@Override
	public TaskAttributeMapper getAttributeMapper(TaskRepository taskRepository) {
		return new TargetProcessAttributeMapper(taskRepository);
	}

	@Override
	public boolean initializeTaskData(TaskRepository repository, TaskData data, ITaskMapping initializationData,
			IProgressMonitor monitor) throws CoreException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public RepositoryResponse postTaskData(TaskRepository repository, TaskData taskData,
			Set<TaskAttribute> changedAttributes, IProgressMonitor monitor) throws CoreException {
		monitor = Policy.monitorFor(monitor);
		try {
			monitor.beginTask(Messages.TargetProcessTaskDataHandler_Submitting_task, IProgressMonitor.UNKNOWN);
			TargetProcessClient client = connector.getClientManager().getClient(repository, monitor);
			try {
				return client.postTaskData(taskData, changedAttributes, monitor);
			} catch (RemoteException e) {
				if (e.getMessage().contains("Tp.Integration.Common.EntityValidationException")) {
					throw new CoreException(new TargetProcessRepositoryStatus(IStatus.ERROR,
							TargetProcessCorePlugin.ID_PLUGIN,
							TargetProcessRepositoryStatus.ENTITY_VALIDATION_EXCEPTION_BASE, repository
									.getRepositoryUrl(), e));
				}
				if (e.getMessage().contains("Tp.Integration.Common.AccessDeniedException")) {
					throw new CoreException(new TargetProcessRepositoryStatus(IStatus.ERROR,
							TargetProcessCorePlugin.ID_PLUGIN, TargetProcessRepositoryStatus.ENTITY_ACCESS_DENIED,
							repository.getRepositoryUrl(), e));
				}
				throw new CoreException(new TargetProcessRepositoryStatus(IStatus.ERROR,
						TargetProcessCorePlugin.ID_PLUGIN, RepositoryStatus.ERROR_NETWORK, repository
								.getRepositoryUrl(), e));
			} catch (EntityValidationException e) {
				throw new CoreException(new TargetProcessRepositoryStatus(IStatus.ERROR,
						TargetProcessCorePlugin.ID_PLUGIN, TargetProcessRepositoryStatus.ENTITY_VALIDATION_EXCEPTION,
						repository.getRepositoryUrl(), e));

			} catch (EntityWasDeletedException e) {
				throw new CoreException(new TargetProcessRepositoryStatus(IStatus.ERROR,
						TargetProcessCorePlugin.ID_PLUGIN, TargetProcessRepositoryStatus.ENTITY_NOT_FOUND,
						repository.getRepositoryUrl(), e));
			}
		} finally {
			monitor.done();
		}
	}

	public TaskData getTaskData(TaskRepository repository, String taskId, IProgressMonitor monitor)
			throws CoreException {
		Set<String> taskIds = new HashSet<String>();
		taskIds.add(taskId);
		final TaskData[] retrievedData = new TaskData[1];
		TaskDataCollector collector = new TaskDataCollector() {

			@Override
			public void accept(TaskData taskData) {
				retrievedData[0] = taskData;
			}
		};
		getMultiTaskData(repository, taskIds, collector, monitor);

		if (retrievedData[0] == null) {
			throw new CoreException(new Status(IStatus.ERROR, TargetProcessCorePlugin.ID_PLUGIN,
					"Task data could not be retrieved. Please re-synchronize task")); //$NON-NLS-1$
		}
		return retrievedData[0];
	}

	@Override
	public void getMultiTaskData(final TaskRepository repository, Set<String> taskIds,
			final TaskDataCollector collector, IProgressMonitor monitor) throws CoreException {

		monitor = Policy.monitorFor(monitor);

		try {
			monitor.beginTask(Messages.TargetProcessTaskDataHandler_Receiving_tasks, taskIds.size());
			TargetProcessClient client = connector.getClientManager().getClient(repository, monitor);
			final CoreException[] collectionException = new CoreException[1];

			class CollectorWrapper extends TaskDataCollector {

				private final IProgressMonitor monitor2;

				private final TaskDataCollector collector;

				public CollectorWrapper(TaskDataCollector collector, IProgressMonitor monitor2) {
					this.collector = collector;
					this.monitor2 = monitor2;
				}

				@Override
				public void accept(TaskData taskData) {
					try {
						initializeTaskData(repository, taskData, null, new SubProgressMonitor(monitor2, 1));
					} catch (CoreException e) {
						if (collectionException[0] == null) {
							collectionException[0] = e;
						}
					}
					collector.accept(taskData);
					monitor2.worked(1);
				}
			}

			TaskDataCollector collector2 = new CollectorWrapper(collector, monitor);

			client.getTaskData(taskIds, collector2, getAttributeMapper(repository), monitor);

			if (collectionException[0] != null) {
				throw collectionException[0];
			}
		} finally {
			monitor.done();
		}
	}

	public void postUpdateAttachment(TaskRepository taskRepository, TaskAttribute taskAttribute, String string,
			IProgressMonitor monitor) throws CoreException {
		// TODO Auto-generated method stub
	}
}
