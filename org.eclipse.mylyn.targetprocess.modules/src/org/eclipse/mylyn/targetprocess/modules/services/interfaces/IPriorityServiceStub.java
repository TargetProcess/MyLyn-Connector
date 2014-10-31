package org.eclipse.mylyn.targetprocess.modules.services.interfaces;

import java.rmi.RemoteException;

import org.eclipse.mylyn.targetprocess.modules.services.PriorityServiceStub;

public interface IPriorityServiceStub {
    public PriorityServiceStub.RetrieveAllResponse retrieveAll(PriorityServiceStub.RetrieveAll retrieveAll)
            throws RemoteException;

}
