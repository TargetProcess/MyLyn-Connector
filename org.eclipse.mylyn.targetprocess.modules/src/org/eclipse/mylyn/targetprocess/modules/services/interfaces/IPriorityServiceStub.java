package org.eclipse.mylyn.targetprocess.modules.services.interfaces;

import org.eclipse.mylyn.targetprocess.modules.services.PriorityServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.PriorityServiceStub.RetrieveAll;
import org.eclipse.mylyn.targetprocess.modules.services.PriorityServiceStub.RetrieveAllResponse;

public interface IPriorityServiceStub {
    public  org.eclipse.mylyn.targetprocess.modules.services.PriorityServiceStub.RetrieveAllResponse retrieveAll(
            	org.eclipse.mylyn.targetprocess.modules.services.PriorityServiceStub.RetrieveAll retrieveAll)
    	throws java.rmi.RemoteException;

}
