package org.eclipse.mylyn.targetprocess.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.mylyn.targetprocess.modules.IServiceFactory;
import org.eclipse.mylyn.targetprocess.modules.ServiceFactory;
import org.osgi.framework.BundleContext;

public class TargetProcessCorePlugin extends Plugin {

	public static final String CONNECTOR_KIND = "targetprocess"; //$NON-NLS-1$
	
	public static final String ID_PLUGIN = "targetprocess plugin";

	private static final String ERROR_INCOMPATIBLE_CONFIGURATION = "Reset TargetProcess repository configuration cache due to format change"; //$NON-NLS-1$;

	private static final String ERROR_DELETING_CONFIGURATION = "Error removing corrupt repository configuration file."; //$NON-NLS-1$

	public static final String IS_WINDOWS_AUTHENTICATION_KEY = "IsWindowsAuthentication";
	
	private static TargetProcessCorePlugin INSTANCE;

	private static boolean cacheFileRead = false;

	private static File repositoryConfigurationFile = null;

	private static Map<String, RepositoryConfiguration> repositoryConfigurations = new HashMap<String, RepositoryConfiguration>();

	private IServiceFactory serviceFactory;

	public TargetProcessCorePlugin()
	{
		super();
		INSTANCE = this;
	}
	
	public static TargetProcessCorePlugin getDefault()
	{
		return INSTANCE;
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		INSTANCE = this;
		serviceFactory = new ServiceFactory();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		INSTANCE = null;
		super.stop(context);
	}

	public static void addRepositoryConfiguration(RepositoryConfiguration config) {
		if (config != null) {
			readRepositoryConfigurationFile();
			synchronized (repositoryConfigurations) {
				internalAddConfiguration(config);
			}
		}
	}
	
	private static void internalAddConfiguration(RepositoryConfiguration config) {
		repositoryConfigurations.remove(config.getRepositoryUrl());
		repositoryConfigurations.put(config.getRepositoryUrl(), config);		
	}

	public static RepositoryConfiguration getRepositoryConfiguration(String repositoryUrl) {
		readRepositoryConfigurationFile();

		return repositoryConfigurations.get(repositoryUrl);
	}	
	
	public static synchronized void readRepositoryConfigurationFile() {

		// IPath configFile = getProductConfigurationCachePath();
		if (cacheFileRead || repositoryConfigurationFile == null || !repositoryConfigurationFile.exists()) {
			return;
		}

		synchronized (repositoryConfigurations) {
			ObjectInputStream in = null;
			try {
				in = new ObjectInputStream(new FileInputStream(repositoryConfigurationFile));
				int size = in.readInt();
				for (int nX = 0; nX < size; nX++) {
					RepositoryConfiguration item = (RepositoryConfiguration) in.readObject();
					if (item != null) {
						repositoryConfigurations.put(item.getRepositoryUrl(), item);
					}
				}
			} catch (Exception e) {
				log(new Status(IStatus.INFO, TargetProcessCorePlugin.ID_PLUGIN, ERROR_INCOMPATIBLE_CONFIGURATION));
				try {
					if (in != null) {
						in.close();
					}
					if (repositoryConfigurationFile != null && repositoryConfigurationFile.exists()) {
						if (repositoryConfigurationFile.delete()) {
							// successfully deleted
						} else {
							log(new Status(IStatus.ERROR, TargetProcessCorePlugin.ID_PLUGIN, 0,
									ERROR_DELETING_CONFIGURATION, e));
						}
					}

				} catch (Exception ex) {
					log(new Status(IStatus.ERROR, TargetProcessCorePlugin.ID_PLUGIN, 0, ERROR_DELETING_CONFIGURATION, e));
				}
			} finally {
				cacheFileRead = true;
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						// ignore
					}
				}
			}
		}
	}

	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}

	/**
	 * Convenience method for logging exceptions to the plugin log
	 * 
	 * @param e
	 *            the exception to log
	 */
	public static void log(Exception e) {
		String message = e.getMessage();
		if (e.getMessage() == null) {
			message = e.getClass().toString();
		}
		log(new Status(IStatus.ERROR, TargetProcessCorePlugin.ID_PLUGIN, 0, message, e));
	}

	public IServiceFactory getServiceFactory() {
		return serviceFactory;
	}

}
