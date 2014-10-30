package org.eclipse.mylyn.targetprocess.modules.services.interfaces;

import java.rmi.RemoteException;

import org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub;

public interface IAssignableServiceStub {

    public AssignableServiceStub.RetrieveTeamsForAssignableResponse retrieveTeamsForAssignable(
            AssignableServiceStub.RetrieveTeamsForAssignable retrieveTeamsForAssignable) throws RemoteException;

}
