package org.eclipse.mylyn.targetprocess.modules.services.tpstubs;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IMyAssignmentsServiceStub;

public class TPMyAssignmentsServiceStub extends MyAssignmentsServiceStub
		implements IMyAssignmentsServiceStub {

	public TPMyAssignmentsServiceStub(
			ConfigurationContext configurationContext, String targetEndpoint)
			throws AxisFault {
		super(configurationContext, targetEndpoint);
	}

}
