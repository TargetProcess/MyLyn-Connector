package org.eclipse.mylyn.targetprocess.modules.services.tpstubs;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IAssignableServiceStub;

public class TPAssignableServiceStub extends AssignableServiceStub implements IAssignableServiceStub {

	public TPAssignableServiceStub(ConfigurationContext configurationContext,
			String targetEndpoint) throws AxisFault {
		super(configurationContext, targetEndpoint);
		// TODO Auto-generated constructor stub
	}

}
