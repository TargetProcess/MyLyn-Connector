package org.eclipse.mylyn.targetprocess.core;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import org.eclipse.mylyn.targetprocess.modules.services.PriorityServiceStub.ArrayOfPriorityDTO;
import org.eclipse.mylyn.targetprocess.modules.services.PriorityServiceStub.PriorityDTO;
import org.eclipse.mylyn.targetprocess.modules.services.PriorityServiceStub.RetrieveAll;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IPriorityServiceStub;
import org.eclipse.mylyn.tasks.core.ITask.PriorityLevel;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskData;

public class PriorityConverter {

	public class RepositoryPriority {
		protected ArrayList<PriorityDTO> bugPriorities = new ArrayList<PriorityDTO>();
		protected ArrayList<PriorityDTO> userStoryPriorities = new ArrayList<PriorityDTO>();
		protected ArrayList<PriorityDTO> requestPriorities = new ArrayList<PriorityDTO>();

		public ArrayList<PriorityDTO> getUserStoryPriorities() {
			return userStoryPriorities;
		}

		public ArrayList<PriorityDTO> getBugPriorities() {
			return bugPriorities;
		}

		public ArrayList<PriorityDTO> getRequestPriorities() {
			return requestPriorities;
		}

		public void addPriorities(IPriorityServiceStub priorityService) throws RemoteException {
			RetrieveAll retrieveAll = new RetrieveAll();
			ArrayOfPriorityDTO priorities = priorityService.retrieveAll(retrieveAll).getRetrieveAllResult();

			bugPriorities = new ArrayList<PriorityDTO>();
			userStoryPriorities = new ArrayList<PriorityDTO>();
			requestPriorities = new ArrayList<PriorityDTO>();

			PriorityDTO[] assignableSimpleDTOs = priorities.getPriorityDTO();
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
				if (priority.getEntityTypeName().equals("Tp.BusinessObjects.Bug")) {
					bugPriorities.add(priority);
				} else if (priority.getEntityTypeName().equals("Tp.BusinessObjects.UserStory")) {
					userStoryPriorities.add(priority);
				} else if (priority.getEntityTypeName().equals("Tp.BusinessObjects.Request")) {
					requestPriorities.add(priority);
				}
			}
		}
	}

	protected static HashMap<String, RepositoryPriority> repositoryPrioritiesMap = new HashMap<String, RepositoryPriority>();
	private String repositoryUrl;

	public PriorityConverter(IPriorityServiceStub priorityService, String repositoryUrl) throws RemoteException {
		this.repositoryUrl = repositoryUrl;

		if (!repositoryPrioritiesMap.containsKey(repositoryUrl)) {
			RepositoryPriority repositoryPriority = new RepositoryPriority();
			repositoryPriority.addPriorities(priorityService);
			repositoryPrioritiesMap.put(repositoryUrl, repositoryPriority);
		}
	}

	public PriorityLevel getMylynPriorityFromTaskData(TaskData taskData) {

		ArrayList<PriorityDTO> bugPriorities = repositoryPrioritiesMap.get(repositoryUrl).getBugPriorities();
		ArrayList<PriorityDTO> userStoryPriorities = repositoryPrioritiesMap.get(repositoryUrl)
				.getUserStoryPriorities();
		ArrayList<PriorityDTO> requestPriorities = repositoryPrioritiesMap.get(repositoryUrl).getRequestPriorities();

		TaskAttribute entityKindAttribute = taskData.getRoot().getAttribute(TargetProcessAttribute.EntityKind.getKey());
		TaskAttribute priorityNameAttribute = taskData.getRoot().getAttribute(TargetProcessAttribute.Priority.getKey());

		if (entityKindAttribute.getValue().equals(TargetProcessEntityKind.USERSTORY.toString())
				|| entityKindAttribute.getValue().equals(TargetProcessEntityKind.TASK.toString())) {
			return getMylynPriorityFromUserStoryPriority(priorityNameAttribute.getValue(), userStoryPriorities);
		} else if (entityKindAttribute.getValue().equals(TargetProcessEntityKind.REQUEST.toString())) {

			return getMylynPriorityFromRequestPriority(priorityNameAttribute.getValue(), requestPriorities);
		}

		return getMylynPriorityFromBugPriority(priorityNameAttribute.getValue(), bugPriorities);
	}

	private PriorityLevel getMylynPriorityFromRequestPriority(String priorityName,
			ArrayList<PriorityDTO> requestPriorities) {
		return getPriority(priorityName, requestPriorities);
	}

	public PriorityLevel getMylynPriorityFromUserStoryPriority(String priorityName,
			ArrayList<PriorityDTO> userStoryPriorities) {
		return getPriority(priorityName, userStoryPriorities);
	}

	public PriorityLevel getMylynPriorityFromBugPriority(String priorityName, ArrayList<PriorityDTO> bugPriorities) {
		return getPriority(priorityName, bugPriorities);
	}

	private PriorityLevel getPriority(String priorityName, ArrayList<PriorityDTO> priorityList) {
		int priorityNumber = 1;
		for (PriorityDTO priority : priorityList) {
			if (priority.getName().equals(priorityName)) {
				break;
			}

			priorityNumber++;
		}

		return PriorityLevel.fromLevel(priorityNumber);
	}

}
