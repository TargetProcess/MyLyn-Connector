package org.eclipse.mylyn.targetprocess.core;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.mylyn.commons.net.AbstractWebLocation;
import org.eclipse.mylyn.commons.net.AuthenticationCredentials;
import org.eclipse.mylyn.commons.net.AuthenticationType;
import org.eclipse.mylyn.commons.net.UnsupportedRequestException;
import org.eclipse.mylyn.tasks.core.IRepositoryListener;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.TaskRepositoryLocationFactory;

public class TargetProcessClientManager implements IRepositoryListener {
	protected static TaskRepositoryLocationFactory taskRepositoryLocationFactory = new TaskRepositoryLocationFactory();
	private final Map<String, TargetProcessClient> clientByUrl = new HashMap<String, TargetProcessClient>();

	public TargetProcessClientManager() {
	}

	public TargetProcessClient getClient(TaskRepository taskRepository, IProgressMonitor monitor) throws CoreException {
		TargetProcessClient client;
		synchronized (clientByUrl) {
			client = clientByUrl.get(taskRepository.getRepositoryUrl());
			if (client == null) {
				try {
					AbstractWebLocation location = taskRepositoryLocationFactory.createWebLocation(taskRepository);
					client = createClient(location, taskRepository, monitor);
				} catch (MalformedURLException e) {
					throw new CoreException( new Status(IStatus.ERROR, TargetProcessCorePlugin.ID_PLUGIN,
							"Malformed Repository Url", e)); //$NON-NLS-1$
				}
				clientByUrl.put(taskRepository.getRepositoryUrl(), client);
			}
			RepositoryConfiguration config = TargetProcessCorePlugin.getRepositoryConfiguration(taskRepository.getUrl());
			client.setRepositoryConfiguration(config);
		}
		return client;
	}

	protected TargetProcessClient createClient(AbstractWebLocation location, TaskRepository taskRepository, IProgressMonitor monitor) throws MalformedURLException {
		 if (location.getCredentials(AuthenticationType.REPOSITORY).getPassword().length() == 0) {
			 try {
				 location.requestCredentials(AuthenticationType.REPOSITORY, "Input password", monitor);
			 }
			 catch(UnsupportedRequestException  e) {
			 }
		 }
		return TargetProcessClientFactory.createClient(location, taskRepository);
	}

	public void repositoryAdded(TaskRepository repository) {
		// make sure there is no stale client still in the cache, bug #149939
		removeClient(repository);
	}

	public void repositoryRemoved(TaskRepository repository) {
		removeClient(repository);
	}

	private void removeClient(TaskRepository repository) {
		synchronized (clientByUrl) {
			clientByUrl.remove(repository.getRepositoryUrl());
		}
	}

	public void repositorySettingsChanged(TaskRepository repository) {
		removeClient(repository);
	}

	public void repositoryUrlChanged(TaskRepository repository, String oldUrl) {
		// ignore
	}

}
