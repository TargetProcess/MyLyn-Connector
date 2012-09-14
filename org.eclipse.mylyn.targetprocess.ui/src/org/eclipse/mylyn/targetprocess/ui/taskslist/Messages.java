package org.eclipse.mylyn.targetprocess.ui.taskslist;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.mylyn.targetprocess.ui.taskslist.messages"; //$NON-NLS-1$	

	

	
	
	static {
		// load message values from bundle file
		reloadMessages();
	}

	public static void reloadMessages() {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
	public static String TargetProcessRepositorySettingsPage_Url_;
	
	public static  String TargetProcessRepositorySettingsPage_Authentication_;

	public static String TargetProcessRepositorySettingsPage_TargetProcessAuthentication_;

	public static String TargetProcessRepositorySettingsPage_IntegratedWindowsAuthentication_;

	public static String TargetProcessRepositorySettingsPage_Login_;

	public static String TargetProcessRepositorySettingsPage_Password_;
	
	public static String TargetProcessRepositorySettingsPage_CheckConnection_;

	public static String TargetProcessQueryTypeWizardPage_Choose_query_type;

	public static String TargetProcessQueryTypeWizardPage_Select_from_the_available_query_types;

	public static String TargetProcessRepositorySettingsPage_Server_URL_is_invalid;

	public static String TargetProcessRepositorySettingsPage_Validating_server_settings;

	public static String TargetProcessRepositorySettingsPage_Label_;

	public static String TargetProcessRepositorySettingsPage_EmptyFieldMessage_;
	
	public static String TargetProcessRepositorySettingsPage_Server_Connect_to_server_failed;

	public static String TargetProcessConnectorUi_Bug;

	public static String TargetProcessConnectorUi_Story;

	public static String TargetProcessConnectorUi_Task;
	
	public static String TargetProcessConnectorUi_Request;

	public static String TargetProcessRepositorySettingsPage_QueryTitle_;

	public static String TargetProcessQueryWizard_Description_;

	public static String TargetProcessRepositorySettingsPage_EmptyUrl_;

	public static String TargetProcessRepositorySettingsPage_EmptyLogin_;

	public static String TargetProcessRepositorySettingsPage_EmptyPassword_;

	public static String TargetProcessRepositorySettingsPage_Enter_a_valid_server_url;

	public static String TargetProcessUi__In_reply_to_comment_0_;

	public static String TargetProcessUi__In_reply_to_X_comment_X_;

	public static String TargetProcessUi__In_reply_to_comment_X_;

	public static String AbstractRepositorySettingsPage_Enter_a_user_id_Message0;
}

