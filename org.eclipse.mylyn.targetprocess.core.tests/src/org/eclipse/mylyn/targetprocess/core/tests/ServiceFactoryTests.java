package org.eclipse.mylyn.targetprocess.core.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.axis2.AxisFault;
import org.eclipse.mylyn.targetprocess.modules.ServiceFactory;
import org.eclipse.mylyn.targetprocess.modules.TargetProcessCredentials;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IAssignableServiceStub;
import org.junit.Test;
import org.junit.Assert;

public class ServiceFactoryTests {

	@Test
	public void willHashServicesIfCredentialsAreTheSame() throws MalformedURLException, AxisFault
	{
		TargetProcessCredentials targetProcessCredentials = new TargetProcessCredentials(new URL("http://test"), "userName", "password", false);
		ServiceFactory serviceFactory = new ServiceFactory();
		
		IAssignableServiceStub assignableServiceStub1 = serviceFactory.getAssignableServiceStub(targetProcessCredentials);
		IAssignableServiceStub assignableServiceStub2 = serviceFactory.getAssignableServiceStub(targetProcessCredentials);
		
		Assert.assertEquals(assignableServiceStub1, assignableServiceStub2);
	}
	
	@Test
	public void willNotHashServicesifCredentialsAreNotTheSame() throws MalformedURLException, AxisFault
	{
		TargetProcessCredentials targetProcessCredentials1 = new TargetProcessCredentials(new URL("http://test"), "userName1", "password1", false);
		TargetProcessCredentials targetProcessCredentials2 = new TargetProcessCredentials(new URL("http://test"), "userName2", "password2", false);
		ServiceFactory serviceFactory = new ServiceFactory();
		
		IAssignableServiceStub assignableServiceStub1 = serviceFactory.getAssignableServiceStub(targetProcessCredentials1);
		IAssignableServiceStub assignableServiceStub2 = serviceFactory.getAssignableServiceStub(targetProcessCredentials2);
		
		Assert.assertNotSame(assignableServiceStub1, assignableServiceStub2);
	}
	
}
