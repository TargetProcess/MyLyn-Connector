package org.eclipse.mylyn.targetprocess.modules.services.tpstubs;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IBugServiceStub;

public class TPBugServiceStub extends BugServiceStub implements IBugServiceStub{

	public TPBugServiceStub(ConfigurationContext ctx, String url) throws AxisFault {
		super(ctx, url);
	}

}
