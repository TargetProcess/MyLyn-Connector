package org.eclipse.mylyn.targetprocess.modules.services.tpstubs;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.eclipse.mylyn.targetprocess.modules.services.FileServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IFileServiceStub;

public class TPFileServiceStub extends FileServiceStub implements IFileServiceStub {

	public TPFileServiceStub(ConfigurationContext configurationContext, String targetEndpoint) throws AxisFault {
		super(configurationContext, targetEndpoint);
		// TODO Auto-generated constructor stub
	}

}
