package org.eclipse.mylyn.targetprocess.core;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.eclipse.mylyn.targetprocess.modules.TargetProcessCredentials;
import org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.EntityStateDTO;
import org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.RetrieveAll;
import org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.RetrieveAllResponse;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IEntityStateServiceStub;

public class EntityStateManager {

	private EntityStateDTO[] entityStateDtos;
	private static HashMap<String, EntityStateDTO[]> entityStates;

	public EntityStateManager(IEntityStateServiceStub entityStateServiceStub,
			TargetProcessCredentials targetProcessCredentials) throws RemoteException {
		if (entityStates == null) {
			entityStates = new HashMap<String, EntityStateDTO[]>();
		}

		String url = targetProcessCredentials.getRepositoryUrl().toString();
		if (!entityStates.containsKey(url)) {
			RetrieveAllResponse response = entityStateServiceStub.retrieveAll(new RetrieveAll());
			entityStateDtos = response.getRetrieveAllResult().getEntityStateDTO();
			entityStates.put(url, entityStateDtos);
		} else {
			entityStateDtos = entityStates.get(url);
		}
	}

	public EntityStateDTO[] retrieveEntityStatesFor(int currentEntityStateID) {
		ArrayList<EntityStateDTO> entityStateListWithGivenEntityType = new ArrayList<EntityStateDTO>();
		EntityStateDTO currentEntityStateDTO = findEntityStateById(currentEntityStateID);
		entityStateListWithGivenEntityType.add(currentEntityStateDTO);

		String nextStates = currentEntityStateDTO.getNextStates();
		String[] nextEntityStateIds = StringUtils.isBlank(nextStates) ? new String[0] : nextStates.split(",");

		for (String entityStateId : nextEntityStateIds) {
			entityStateListWithGivenEntityType.add(findEntityStateById(Integer.parseInt(entityStateId)));
		}

		EntityStateDTO[] entityStateDTOs = new EntityStateDTO[entityStateListWithGivenEntityType.size()];
		return entityStateListWithGivenEntityType.toArray(entityStateDTOs);
	}

	private EntityStateDTO findEntityStateById(int currentEntityStateID) {
		for (EntityStateDTO entityStateDTO : entityStateDtos) {
			if (entityStateDTO.getEntityStateID() == currentEntityStateID) {
				return entityStateDTO;
			}
		}

		return null;
	}

	public static void clean() {
		entityStates = null;
	}
}
