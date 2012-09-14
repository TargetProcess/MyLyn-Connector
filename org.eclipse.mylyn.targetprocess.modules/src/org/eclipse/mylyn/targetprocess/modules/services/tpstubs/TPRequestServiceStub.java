package org.eclipse.mylyn.targetprocess.modules.services.tpstubs;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IRequestServiceStub;

public class TPRequestServiceStub extends RequestServiceStub implements IRequestServiceStub {
	
	public TPRequestServiceStub(ConfigurationContext configurationContext,
			String targetEndpoint) throws AxisFault {
		super(configurationContext, targetEndpoint);		
	}
}
	
    
