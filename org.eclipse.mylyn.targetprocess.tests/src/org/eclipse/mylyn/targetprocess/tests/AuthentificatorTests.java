package org.eclipse.mylyn.targetprocess.tests;

import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.mylyn.targetprocess.modules.AuthenticateResult;
import org.eclipse.mylyn.targetprocess.modules.AuthenticateStatus;
import org.eclipse.mylyn.targetprocess.modules.Authenticator;
import org.eclipse.mylyn.targetprocess.modules.ServiceFactory;
import org.eclipse.mylyn.targetprocess.modules.TPAuthenticationException;
import org.eclipse.mylyn.targetprocess.modules.TargetProcessCredentials;

public class AuthentificatorTests extends TestCase {

	public void testWillReturnFailedStatusIfHostIsInvalid() {
		TargetProcessCredentials targetProcessCredentials = new TargetProcessCredentials(
				createUrl("http://new-bugzilla/incorrectHost"), "login", "password", false);
		checkAuthenticate(targetProcessCredentials, false);
	}

	public void testWillReturnFailedStatusIfCredentialsAreInvalid() {
		TargetProcessCredentials targetProcessCredentials = new TargetProcessCredentials(
				createUrl("http://new-bugzilla/TP"), "login", "password", false);
		checkAuthenticate(targetProcessCredentials, false);
	}

	public void testWillReturnFailedStatusIfPasswordIsInvalid() {
		TargetProcessCredentials targetProcessCredentials = new TargetProcessCredentials(
				createUrl("http://new-bugzilla/TP"), "admin", "incorrectPassword", false);
		checkAuthenticate(targetProcessCredentials, false);
	}

	public void testWillReturnSucceedStatusIfConnectionSettingsAreValid() {
		TargetProcessCredentials targetProcessCredentials = new TargetProcessCredentials(
				createUrl("http://new-bugzilla/TP"), "admin", "admin", false);
		checkAuthenticate(targetProcessCredentials, true);
	}

	public void testWillReturnSucceedStatusIfConnectionWithNTLMAuthentication() {
		TargetProcessCredentials targetProcessCredentials = new TargetProcessCredentials(
		createUrl("http://192.168.1.234/TPNTLM"), "office\\operator", "trustMIND", true);
		checkAuthenticate(targetProcessCredentials, true);
	}

	private URL createUrl(String repositoryUrl) {
		try {
			return new URL(repositoryUrl);
		} catch (MalformedURLException e) {
			Assert.fail("Incorrect Url");
		}
		return null;
	}

	private void checkAuthenticate(TargetProcessCredentials targetProcessCredentials, boolean shouldSuccess) {
		Authenticator authenticator = new Authenticator(new ServiceFactory());
		AuthenticateResult authenticateResult = null;
		try {
			authenticateResult = authenticator.authenticate(targetProcessCredentials);
		} catch (TPAuthenticationException e) {
			if (!shouldSuccess) {
				Assert.assertEquals(AuthenticateStatus.Failed, e.getAuthenticateResult().getStatus());
				return;
			} else {
				Assert.fail("Authenticate failed");
			}
		}

		if (!shouldSuccess) {
			Assert.fail("Should throw authenticate exception.");
		} else {
			Assert.assertNotNull(authenticateResult);
			Assert.assertEquals(AuthenticateStatus.Succeed, authenticateResult.getStatus());
		}
	}
}
