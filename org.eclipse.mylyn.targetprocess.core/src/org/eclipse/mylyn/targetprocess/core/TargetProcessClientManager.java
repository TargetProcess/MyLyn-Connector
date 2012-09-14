package org.eclipse.mylyn.targetprocess.core;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.mylyn.tasks.core.IRepositoryListener;
import org.eclipse.mylyn.tasks.core.TaskRepository;

public class TargetProcessClientManager implements IRepositoryListener {

	private final Map<String, TargetProcessClient> clientByUrl = new HashMap<String, TargetProcessClient>();

	public TargetProcessClientManager() {
	}

	public TargetProcessClient getClient(TaskRepository taskRepository, IProgressMonitor monitor) throws CoreException {
		TargetProcessClient client;
		synchronized (clientByUrl) {
			client = clientByUrl.get(taskRepository.getRepositoryUrl());
			if (client == null) {
				try {
					client = createClient(taskRepository);
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

	protected TargetProcessClient createClient(TaskRepository taskRepository) throws MalformedURLException {
		return TargetProcessClientFactory.createClient(taskRepository);
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
