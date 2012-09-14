package org.eclipse.mylyn.targetprocess.ui.taskslist;

import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.wizards.RepositoryQueryWizard;

public class TargetProcessRepositoryQueryWizard extends RepositoryQueryWizard {
	
	public TargetProcessRepositoryQueryWizard(TaskRepository repository) {
		super(repository);
	}

	@Override
	public boolean canFinish() {
		return true;
	}

}
