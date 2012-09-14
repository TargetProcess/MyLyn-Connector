package org.eclipse.mylyn.targetprocess.modules;

import org.eclipse.mylyn.targetprocess.modules.AuthenticateResult;

public class TPAuthenticationException extends Exception {

	private static final long serialVersionUID = -4392819489130811703L;
	private AuthenticateResult authenticateResult;
	
	public TPAuthenticationException(AuthenticateResult authenticateResult)
	{
		this.authenticateResult = authenticateResult;
	}

	public AuthenticateResult getAuthenticateResult() {
		return authenticateResult;
	}

}
