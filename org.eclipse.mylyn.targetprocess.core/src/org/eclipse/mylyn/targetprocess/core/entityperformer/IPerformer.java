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

public interface IPerformer {

	TaskData createTaskDataBy(int entityID, TaskRepository taskRepository) throws RemoteException;

	void update(TaskData taskData, Set<TaskAttribute> changedAttributes) throws RemoteException, EntityValidationException, EntityWasDeletedException;

	ArrayList<Comment> getComments(int entityId) throws RemoteException;

	ArrayList<Attachment> getAttachments(int entityId) throws RemoteException;

	String getEntityTypeName();

	TargetProcessEntityKind getEntityKind();

	boolean isSupportTaskData(TaskData taskData);

	TaskData createTaskDataBy(AssignableToDoDTO assignable, MyAssignmentsToDo toDoList, TaskRepository taskRepository);
}
