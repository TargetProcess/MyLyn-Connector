package org.eclipse.mylyn.targetprocess.ui.action;

import org.eclipse.osgi.util.NLS;

public class Messages {

	private static final String BUNDLE_NAME = "org.eclipse.mylyn.targetprocess.ui.action.messages"; //$NON-NLS-1$

	static {
		// load message values from bundle file
		reloadMessages();
	}

	public static void reloadMessages() {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
	public static String UpdateAttachmentJob_update_attachment;
	public static String UpdateAttachmentJob_update_attachments;

}
