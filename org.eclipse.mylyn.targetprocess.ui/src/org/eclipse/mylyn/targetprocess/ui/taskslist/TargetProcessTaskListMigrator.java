package org.eclipse.mylyn.targetprocess.ui.taskslist;

import java.util.Set;

import org.eclipse.mylyn.targetprocess.core.TargetProcessCorePlugin;
import org.eclipse.mylyn.tasks.core.AbstractTaskListMigrator;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.ITask;
import org.w3c.dom.Element;

public class TargetProcessTaskListMigrator extends AbstractTaskListMigrator {

	@Override
	public String getConnectorKind() { 
		return TargetProcessCorePlugin.CONNECTOR_KIND;
	}

	@Override
	public Set<String> getQueryElementNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTaskElementName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void migrateQuery(IRepositoryQuery query, Element element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void migrateTask(ITask task, Element element) {
		// TODO Auto-generated method stub

	}

}
