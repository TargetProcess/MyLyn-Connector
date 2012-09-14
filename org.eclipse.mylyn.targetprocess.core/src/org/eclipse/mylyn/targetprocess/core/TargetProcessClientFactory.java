package org.eclipse.mylyn.targetprocess.core;

import java.net.MalformedURLException;

import org.eclipse.mylyn.commons.net.AbstractWebLocation;
import org.eclipse.mylyn.commons.net.AuthenticationCredentials;
import org.eclipse.mylyn.commons.net.AuthenticationType;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.TaskRepositoryLocationFactory;

public class TargetProcessClientFactory {

	protected static TaskRepositoryLocationFactory taskRepositoryLocationFactory = new TaskRepositoryLocationFactory();

	public static TargetProcessClient createClient(TaskRepository taskRepository)
			throws MalformedURLException {
		AbstractWebLocation location = taskRepositoryLocationFactory
				.createWebLocation(taskRepository);
		AuthenticationCredentials credentials = taskRepository
				.getCredentials(AuthenticationType.REPOSITORY);

		TargetProcessClient client = new TargetProcessClient(location,
				credentials, TargetProcessCorePlugin.getDefault().getServiceFactory(), taskRepository
						.getProperty(TargetProcessCorePlugin.IS_WINDOWS_AUTHENTICATION_KEY) != null);
		return client;
	}
}
