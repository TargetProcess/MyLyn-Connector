package org.eclipse.mylyn.targetprocess.modules.services.tpstubs;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IEntityStateServiceStub;

public class TPEntityStateServiceStub extends EntityStateServiceStub implements IEntityStateServiceStub {

	public TPEntityStateServiceStub(ConfigurationContext configurationContext, String targetEndpoint) throws AxisFault {
		super(configurationContext, targetEndpoint);
		// TODO Auto-generated constructor stub
	}

}
