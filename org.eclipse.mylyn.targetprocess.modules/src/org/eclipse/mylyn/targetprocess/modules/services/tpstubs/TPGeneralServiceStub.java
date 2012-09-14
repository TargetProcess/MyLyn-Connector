package org.eclipse.mylyn.targetprocess.modules.services.tpstubs;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IGeneralServiceStub;

public class TPGeneralServiceStub extends GeneralServiceStub implements IGeneralServiceStub {

	public TPGeneralServiceStub(ConfigurationContext configurationContext, String targetEndpoint) throws AxisFault {
		super(configurationContext, targetEndpoint);
		// TODO Auto-generated constructor stub
	}

}
