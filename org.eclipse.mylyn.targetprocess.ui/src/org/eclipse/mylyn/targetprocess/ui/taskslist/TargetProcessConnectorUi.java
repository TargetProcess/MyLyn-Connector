package org.eclipse.mylyn.targetprocess.ui.taskslist;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.mylyn.targetprocess.core.TargetProcessCorePlugin;
import org.eclipse.mylyn.targetprocess.core.TargetProcessEntityKind;
import org.eclipse.mylyn.targetprocess.ui.TargetProcessImages;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.ITaskComment;
import org.eclipse.mylyn.tasks.core.ITaskMapping;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.AbstractRepositoryConnectorUi;
import org.eclipse.mylyn.tasks.ui.LegendElement;
import org.eclipse.mylyn.tasks.ui.wizards.ITaskRepositoryPage;

public class TargetProcessConnectorUi extends AbstractRepositoryConnectorUi {

	@Override
	public String getConnectorKind() {
		// TODO Auto-generated method stub
		return TargetProcessCorePlugin.CONNECTOR_KIND;
	}

	@Override
	public IWizard getNewTaskWizard(TaskRepository taskRepository, ITaskMapping selection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IWizard getQueryWizard(TaskRepository taskRepository, IRepositoryQuery query) {
		TargetProcessRepositoryQueryWizard wizard = new TargetProcessRepositoryQueryWizard(taskRepository);
		wizard.addPage(new TargetProcessQueryWizardPage(taskRepository));

		return wizard;
	}

	public boolean canFlipToNextPage() {
		return true;
	}

	@Override
	public ITaskRepositoryPage getSettingsPage(TaskRepository taskRepository) {
		return new TargetProcessRepositorySettingsPage(taskRepository);
	}

	@Override
	public String getReplyText(TaskRepository taskRepository, ITask task, ITaskComment taskComment, boolean includeTask) {
		if (taskComment == null) {
			return Messages.TargetProcessUi__In_reply_to_comment_0_;
		} else if (includeTask) {
			return MessageFormat.format(Messages.TargetProcessUi__In_reply_to_X_comment_X_, task.getTaskKey(),
					taskComment.getNumber());
		} else {
			return MessageFormat.format(Messages.TargetProcessUi__In_reply_to_comment_X_, taskComment.getNumber());
		}
	}

	@Override
	public boolean hasSearchPage() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getTaskKindLabel(ITask task) {
		/*
		EntityPerformerFactory performerFactory = new EntityPerformerFactory();
		performerFactory.createPerformer(task);

		if(task.getTaskKind().equals(TargetProcessEntityKind.BUG.getName())) {
			return "Bug";
		} else if(task.getTaskKind().equals(TargetProcessEntityKind.REQUEST.toString())) {
			return "Request";
		} else if(task.getTaskKind().equals(TargetProcessEntityKind.TASK.toString())) {
			return "Task";
		} else if(task.getTaskKind().equals(TargetProcessEntityKind.USERSTORY.toString())) {
			return "User Story";
		}
		*/
		return TargetProcessEntityKind.parseFromString(task.getTaskKind()).getReadableName();
	}

	@Override
	public List<LegendElement> getLegendElements() {
		List<LegendElement> legendItems = new ArrayList<LegendElement>();
		legendItems.add(LegendElement
				.createTask(Messages.TargetProcessConnectorUi_Bug, TargetProcessImages.OVERLAY_BUG));
		legendItems.add(LegendElement.createTask(Messages.TargetProcessConnectorUi_Story,
				TargetProcessImages.OVERLAY_STORY));
		legendItems.add(LegendElement.createTask(Messages.TargetProcessConnectorUi_Task,
				TargetProcessImages.OVERLAY_TASK));
		legendItems.add(LegendElement.createTask(Messages.TargetProcessConnectorUi_Request,
				TargetProcessImages.OVERLAY_REQUEST));
		return legendItems;
	}

	@Override
	public ImageDescriptor getTaskKindOverlay(ITask task) {
		if (TargetProcessCorePlugin.CONNECTOR_KIND.equals(task.getConnectorKind())) {
			if (TargetProcessEntityKind.BUG.toString().equals(task.getTaskKind())) {
				return TargetProcessImages.OVERLAY_BUG;
			} else if (TargetProcessEntityKind.USERSTORY.toString().equals(task.getTaskKind())) {
				return TargetProcessImages.OVERLAY_STORY;
			} else if (TargetProcessEntityKind.TASK.toString().equals(task.getTaskKind())) {
				return TargetProcessImages.OVERLAY_TASK;
			} else if (TargetProcessEntityKind.REQUEST.toString().equals(task.getTaskKind())) {
				return TargetProcessImages.OVERLAY_REQUEST;
			}
		}
		return null;
	}
}
