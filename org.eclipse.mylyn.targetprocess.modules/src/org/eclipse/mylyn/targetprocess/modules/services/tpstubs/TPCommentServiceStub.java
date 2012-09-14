package org.eclipse.mylyn.targetprocess.modules.services.tpstubs;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.eclipse.mylyn.targetprocess.modules.services.CommentServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.ICommentServiceStub;

public class TPCommentServiceStub extends CommentServiceStub implements ICommentServiceStub {

	public TPCommentServiceStub(ConfigurationContext configurationContext, String targetEndpoint) throws AxisFault {
		super(configurationContext, targetEndpoint);
		// TODO Auto-generated constructor stub
	}

}
