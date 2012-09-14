package org.eclipse.mylyn.targetprocess.core;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.eclipse.mylyn.targetprocess.core.entityperformer.EntityPerformerFactory;
import org.eclipse.mylyn.targetprocess.core.entityperformer.IPerformer;
import org.eclipse.mylyn.targetprocess.modules.IServiceFactory;
import org.eclipse.mylyn.targetprocess.modules.TargetProcessCredentials;
import org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.GeneralDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AssignableSimpleDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.AssignableToDoDTO;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.MyAssignmentsToDo;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.eclipse.mylyn.tasks.core.data.TaskDataCollector;

public class EntityCollector {

	private ArrayList<TaskData> taskDataList;
	private TargetProcessCredentials targetProcessCredentials;
	private IServiceFactory serviceFactory;

	public EntityCollector(IServiceFactory serviceFactory, TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		this.targetProcessCredentials = targetProcessCredentials;
		taskDataList = new ArrayList<TaskData>();
		this.serviceFactory = serviceFactory;
	}

	public ArrayList<TaskData> getTaskDataList() {
		return taskDataList;
	}

	public void exportTo(TaskDataCollector resultCollector) {
		for (TaskData taskData : taskDataList) {
			resultCollector.accept(taskData);
		}
	}

	public TaskData accept(AssignableSimpleDTO assignableSimpleDTO, TaskRepository repository) throws RemoteException {
		TaskData taskData = acceptEntity(repository, assignableSimpleDTO.get_entityTypeName(), assignableSimpleDTO
				.getID());
		taskDataList.add(taskData);
		return taskData;
	}

	public TaskData accept(GeneralDTO generalDTO, TaskRepository repository) throws RemoteException {
		return acceptEntity(repository, generalDTO.getEntityTypeName(), generalDTO.getID());
	}

	private TaskData acceptEntity(TaskRepository repository, String entityTypeName, int entityID)
			throws RemoteException {

		EntityPerformerFactory entityPerformerFactory = new EntityPerformerFactory(targetProcessCredentials);
		IPerformer performer = entityPerformerFactory.createPerformer(serviceFactory, entityTypeName);
		return performer.createTaskDataBy(entityID, repository);
	}

	public TaskData accept(AssignableToDoDTO assignable, MyAssignmentsToDo toDoList, TaskRepository taskRepository)
			throws RemoteException {

		EntityPerformerFactory entityPerformerFactory = new EntityPerformerFactory(targetProcessCredentials);
		IPerformer performer = entityPerformerFactory.createPerformer(serviceFactory, assignable.getEntityTypeName());		
		TaskData taskData = performer.createTaskDataBy(assignable, toDoList, taskRepository);
		if(taskData != null)
			taskDataList.add(taskData);
		return taskData;
	}
}
