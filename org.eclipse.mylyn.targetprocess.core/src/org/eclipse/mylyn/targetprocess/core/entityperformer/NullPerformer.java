package org.eclipse.mylyn.targetprocess.core.entityperformer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

import org.eclipse.mylyn.targetprocess.core.TargetProcessEntityKind;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AssignableToDoDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.MyAssignmentsToDo;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskData;

public class NullPerformer implements IPerformer {

	@Override
	public TaskData createTaskDataBy(int entityID, TaskRepository taskRepository) throws RemoteException {
		return null;
	}

	@Override
	public TargetProcessEntityKind getEntityKind() {
		return TargetProcessEntityKind.UNDEFINED;
	}

	@Override
	public String getEntityTypeName() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public boolean isSupportTaskData(TaskData taskData) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(TaskData taskData, Set<TaskAttribute> changedAttributes) throws RemoteException {
	}

	public void addDescriptionAttribute(TaskData taskData, String description) {
	}

	@Override
	public ArrayList<Comment> getComments(int entityId) throws RemoteException {
		return new ArrayList<Comment>();
	}

	@Override
	public ArrayList<Attachment> getAttachments(int entityId) throws RemoteException {
		return new ArrayList<Attachment>();
	}

	@Override
	public TaskData createTaskDataBy(AssignableToDoDTO assignable, MyAssignmentsToDo toDoList,
			TaskRepository taskRepository) {
		// TODO Auto-generated method stub
		return null;
	}	

	
}
