package org.eclipse.mylyn.targetprocess.modules.services.tpstubs;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.eclipse.mylyn.targetprocess.modules.services.PriorityServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IPriorityServiceStub;

public class TPPriorityServiceStub extends PriorityServiceStub implements IPriorityServiceStub{
	
	public TPPriorityServiceStub(ConfigurationContext configurationContext,
			String targetEndpoint) throws AxisFault {
		super(configurationContext, targetEndpoint);
	}

}
