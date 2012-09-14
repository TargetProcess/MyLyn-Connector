package org.eclipse.mylyn.targetprocess.modules;

import java.io.IOException;
import java.util.HashMap;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class PWCBHandler implements CallbackHandler {

	private static HashMap<String, String> passwordHashMap = new HashMap<String, String>();

	public static HashMap<String, String> getPasswordHashMap() {
		return passwordHashMap;
	}

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (Callback callback : callbacks) {
			WSPasswordCallback pwcb = (WSPasswordCallback) callback;

			if (passwordHashMap.containsKey(pwcb.getIdentifer())) {
				pwcb.setPassword(passwordHashMap.get(pwcb.getIdentifer()));
			} else {
				throw new UnsupportedCallbackException(callback,
						"Internal client error. Password for specified identifier could not be found.");
			}
		}
	}
}
