package org.eclipse.mylyn.targetprocess.modules;

import java.rmi.RemoteException;
import org.eclipse.mylyn.targetprocess.modules.services.AuthenticationServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.AuthenticationServiceStub.Authenticate;

public class Authenticator {
	
	private IServiceFactory serviceFactory;

	public Authenticator(IServiceFactory serviceFactory)
	{
		this.serviceFactory = serviceFactory;
	}

	public AuthenticateResult authenticate(TargetProcessCredentials targetProcessCredentials) throws TPAuthenticationException {
		try {
			AuthenticationServiceStub authenticationServiceStub = serviceFactory.getAuthentificationServiceStub(targetProcessCredentials);		
			authenticationServiceStub.authenticate(new Authenticate());
		}
		catch (RemoteException e) {
			throw new TPAuthenticationException(getFailed()); 
		}
		return new AuthenticateResult(AuthenticateStatus.Succeed);
	}
	
	protected AuthenticateResult getFailed()
	{
		AuthenticateResult authenticateResult = new AuthenticateResult(AuthenticateStatus.Failed, Messages.TargetProcessStatus_generalFailure);
		return authenticateResult;
	}
}
