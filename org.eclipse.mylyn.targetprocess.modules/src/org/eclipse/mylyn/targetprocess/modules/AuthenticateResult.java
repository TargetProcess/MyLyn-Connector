package org.eclipse.mylyn.targetprocess.modules;

public class AuthenticateResult {

	private AuthenticateStatus authenticateStatus;
	private String message;

	public AuthenticateResult(AuthenticateStatus authenticateStatus,
			String message) {
		this(authenticateStatus);
		this.message = message;
	}

	public AuthenticateResult(AuthenticateStatus status) {
		this.authenticateStatus = status;
	}

	public AuthenticateStatus getStatus() {
		return authenticateStatus;
	}	
	
	public String getMessage()
	{
		return message;
	}
	

}
