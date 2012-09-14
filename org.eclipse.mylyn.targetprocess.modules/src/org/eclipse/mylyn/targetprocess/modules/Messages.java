package org.eclipse.mylyn.targetprocess.modules;


import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	
	private static final String BUNDLE_NAME = "org.eclipse.mylyn.targetprocess.modules"; //$NON-NLS-1$
	
	static {
		// load message values from bundle file
		reloadMessages();
	}

	public static void reloadMessages() {
		//NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	public static String TargetProcessStatus_generalFailure = "dsfsd";	
}
