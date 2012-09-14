package org.eclipse.mylyn.targetprocess.core;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.mylyn.commons.net.AuthenticationCredentials;
import org.eclipse.mylyn.commons.net.AuthenticationType;
import org.eclipse.mylyn.targetprocess.modules.TargetProcessCredentials;
import org.eclipse.mylyn.tasks.core.TaskRepository;

public class TargetProcessCredentialsFactory {

	public static TargetProcessCredentials createTargetProcessCredentials(TaskRepository repository) {
		AuthenticationCredentials credentials = repository.getCredentials(AuthenticationType.REPOSITORY);
		boolean isWindowsAuthentication = repository.getProperty(TargetProcessCorePlugin.IS_WINDOWS_AUTHENTICATION_KEY) != null;

		try {
			return createTargetProcessCredentials(new URL(repository.getRepositoryUrl()), credentials,
					isWindowsAuthentication);
		} catch (MalformedURLException e) {
			TargetProcessCorePlugin.log(e);
		}

		return null;
	}

	public static TargetProcessCredentials createTargetProcessCredentials(URL repositoryUrl,
			AuthenticationCredentials credentials, boolean isWindowsAuthentication) throws MalformedURLException {
		return new TargetProcessCredentials(repositoryUrl, credentials.getUserName(), credentials.getPassword(),
				isWindowsAuthentication);

	}
}
