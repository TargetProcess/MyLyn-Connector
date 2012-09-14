package org.eclipse.mylyn.targetprocess.core.tests;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.mylyn.targetprocess.modules.TargetProcessCredentials;
import org.junit.Test;

public class targetProcessCredentialsTest {

	@Test
	public void willCorrectlyReturnDomainUserName() throws MalformedURLException {
		TargetProcessCredentials targetProcessCredentials = new TargetProcessCredentials(new URL("http:\\localhost"),
				"domain\\username", "password", true);
		
		assertEquals("domain", targetProcessCredentials.getDomain());
		assertEquals("username", targetProcessCredentials.getUserName());
		
		targetProcessCredentials = new TargetProcessCredentials(new URL("http:\\localhost"),
				"username", "password", true);
		
		assertEquals("", targetProcessCredentials.getDomain());
		assertEquals("username", targetProcessCredentials.getUserName());
	}
	
}
