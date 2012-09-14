package org.eclipse.mylyn.targetprocess.modules.services.tpstubs;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.ITaskServiceStub;

public class TPTaskServiceStub extends TaskServiceStub implements ITaskServiceStub {
	
	public TPTaskServiceStub(ConfigurationContext configurationContext,
			String targetEndpoint) throws AxisFault {
		super(configurationContext, targetEndpoint);
	}	
}
