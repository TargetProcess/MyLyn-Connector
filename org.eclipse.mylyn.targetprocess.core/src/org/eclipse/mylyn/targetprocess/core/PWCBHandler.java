package org.eclipse.mylyn.targetprocess.core;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class PWCBHandler implements CallbackHandler {

	private static String _password; 
	@Override
	  public void handle(Callback[] callbacks) throws IOException,
      	UnsupportedCallbackException {
			for (int i = 0; i < callbacks.length; i++) {
				WSPasswordCallback pwcb = (WSPasswordCallback)callbacks[i];
				pwcb.setPassword(_password);
			}
	}
	
	public static void setPassword(String _password) {
		PWCBHandler._password = _password;
	}
	public static String getPassword() {
		return _password;
	}

}
