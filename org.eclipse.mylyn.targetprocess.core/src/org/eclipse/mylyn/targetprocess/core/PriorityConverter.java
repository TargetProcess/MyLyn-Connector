package org.eclipse.mylyn.targetprocess.core;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.mylyn.targetprocess.modules.services.PriorityServiceStub.ArrayOfPriorityDTO;
import org.eclipse.mylyn.targetprocess.modules.services.PriorityServiceStub.PriorityDTO;
import org.eclipse.mylyn.targetprocess.modules.services.PriorityServiceStub.RetrieveAll;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IPriorityServiceStub;
import org.eclipse.mylyn.tasks.core.ITask.PriorityLevel;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskData;

public class PriorityConverter {

	protected static Map<String, RepositoryPriority> repositoryPrioritiesMap = new ConcurrentHashMap<String, RepositoryPriority>();
	private final String repositoryUrl;

	public PriorityConverter(IPriorityServiceStub priorityService, String repositoryUrl) throws RemoteException {
		this.repositoryUrl = repositoryUrl;

		if (!repositoryPrioritiesMap.containsKey(repositoryUrl)) {
			RepositoryPriority repositoryPriority = new RepositoryPriority(priorityService);
			repositoryPrioritiesMap.put(repositoryUrl, repositoryPriority);
		}
	}

	public PriorityLevel getMylynPriorityFromTaskData(TaskData taskData) {
		RepositoryPriority repositoryPriority = repositoryPrioritiesMap.get(repositoryUrl);

		TaskAttribute entityKindAttribute = taskData.getRoot().getAttribute(TargetProcessAttribute.EntityKind.getKey());
		TaskAttribute priorityNameAttribute = taskData.getRoot().getAttribute(TargetProcessAttribute.Priority.getKey());

		String entityKind = entityKindAttribute.getValue();
		if (isEntityKind(entityKind, TargetProcessEntityKind.USERSTORY)
				|| isEntityKind(entityKind, TargetProcessEntityKind.TASK)) {
			return getPriority(priorityNameAttribute.getValue(), repositoryPriority.getUserStoryPriorities());
		} else if (isEntityKind(entityKind, TargetProcessEntityKind.REQUEST)) {
			return getPriority(priorityNameAttribute.getValue(), repositoryPriority.getRequestPriorities());
		}

		return getPriority(priorityNameAttribute.getValue(), repositoryPriority.getBugPriorities());
	}

	private boolean isEntityKind(String entityKind, TargetProcessEntityKind expected) {
		return expected.toString().equals(entityKind);
	}

	private PriorityLevel getPriority(String priorityName, List<PriorityDTO> priorityList) {
		int priorityNumber = 1;
		for (PriorityDTO priority : priorityList) {
			if (priority.getName().equals(priorityName)) {
				break;
			}

			priorityNumber++;
		}

		return PriorityLevel.fromLevel(priorityNumber);
	}

	private class RepositoryPriority {
		private final List<PriorityDTO> bugPriorities = new ArrayList<PriorityDTO>();
		private final List<PriorityDTO> userStoryPriorities = new ArrayList<PriorityDTO>();
		private final List<PriorityDTO> requestPriorities = new ArrayList<PriorityDTO>();

		public RepositoryPriority(IPriorityServiceStub priorityService) throws RemoteException {
			addPriorities(priorityService);
		}

		public List<PriorityDTO> getUserStoryPriorities() {
			return userStoryPriorities;
		}

		public List<PriorityDTO> getBugPriorities() {
			return bugPriorities;
		}

		public List<PriorityDTO> getRequestPriorities() {
			return requestPriorities;
		}

		private void addPriorities(IPriorityServiceStub priorityService) throws RemoteException {
			RetrieveAll retrieveAll = new RetrieveAll();
			ArrayOfPriorityDTO priorities = priorityService.retrieveAll(retrieveAll).getRetrieveAllResult();

			PriorityDTO[] assignableSimpleDTOs = priorities.getPriorityDTO();
			if (assignableSimpleDTOs == null) {
				assignableSimpleDTOs = new PriorityDTO[] {};
			}

			Arrays.sort(assignableSimpleDTOs, new Comparator<PriorityDTO>() {
				@Override
				public int compare(PriorityDTO arg0, PriorityDTO arg1) {
					if (arg0.getImportance() > arg1.getImportance()) {
						return 1;
					} else if (arg0.getImportance() == arg1.getImportance()) {
						return 0;
					} else {
						return -1;
					}
				}
			});

			for (PriorityDTO priority : assignableSimpleDTOs) {
				String entityTypeName = priority.getEntityTypeName();
				if (entityTypeName.equals("Tp.BusinessObjects.Bug")) {
					bugPriorities.add(priority);
				} else if (entityTypeName.equals("Tp.BusinessObjects.UserStory")) {
					userStoryPriorities.add(priority);
				} else if (entityTypeName.equals("Tp.BusinessObjects.Request")) {
					requestPriorities.add(priority);
				}
			}
		}
	}
}
