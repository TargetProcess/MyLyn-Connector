package org.eclipse.mylyn.targetprocess.ui;

import org.eclipse.mylyn.targetprocess.core.ITargetProcessConstants;
import org.eclipse.mylyn.targetprocess.core.TargetProcessCorePlugin;
import org.eclipse.mylyn.targetprocess.core.TargetProcessRepositoryConnector;
import org.eclipse.mylyn.tasks.ui.TasksUi;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class TargetProcessUiPlugin extends AbstractUIPlugin {

	public static final String ID_PLUGIN = "org.eclipse.mylyn.targetprocess.ui"; //$NON-NLS-1$

	private static TargetProcessUiPlugin instance;

	public static TargetProcessUiPlugin getDefault() {
		return instance;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);

		org.eclipse.mylyn.internal.tasks.ui.wizards.Messages.AbstractRepositorySettingsPage_Validate_Settings = "Check connection";

		getPreferenceStore().setDefault(ITargetProcessConstants.MAX_RESULTS, 100);

		TargetProcessRepositoryConnector targetProcessConnector = (TargetProcessRepositoryConnector) TasksUi
				.getRepositoryManager().getRepositoryConnector(TargetProcessCorePlugin.CONNECTOR_KIND);

		TasksUi.getRepositoryManager().addListener(targetProcessConnector.getClientManager());

		instance = this;
	}
}
