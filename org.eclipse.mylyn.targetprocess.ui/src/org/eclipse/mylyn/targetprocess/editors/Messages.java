package org.eclipse.mylyn.targetprocess.editors;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

private static final String BUNDLE_NAME = "org.eclipse.mylyn.targetprocess.editors"; //$NON-NLS-1$
	
	
	static {
		// load message values from bundle file
		reloadMessages();
	}

	public static void reloadMessages() {
		//NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
	public static String TaskEditorDescriptionPart_Description_ ="Description";
	public static String TaskEditorRichTextPart_Maximize = "Maximize";
	public static String TaskEditorDescriptionPart_Edit_ = "Edit HTML";

}
