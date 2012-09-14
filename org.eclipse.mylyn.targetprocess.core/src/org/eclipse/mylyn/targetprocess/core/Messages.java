package org.eclipse.mylyn.targetprocess.core;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.mylyn.targetprocess.core.messages"; //$NON-NLS-1$

	static {
		// load message values from bundle file
		reloadMessages();
	}

	public static void reloadMessages() {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	public static String TargetProcessAttribute_used_by_search_engine_id;
	public static String TargetProcessAttribute_used_by_search_engine_name;
	public static String TargetProcessAttribute_used_by_search_engine_desc;
	public static String TargetProcessAttribute_used_by_search_engine_state;
	public static String TargetProcessAttribute_used_by_search_engine_project;
	public static String TargetProcessAttribute_used_by_search_engine_owner;
	public static String TargetProcessAttribute_used_by_search_engine_userStory;
	public static String TargetProcessAttribute_used_by_search_engine_assignedPeople;
	public static String TargetProcessAttribute_used_by_search_engine_severity;
	public static String TargetProcessAttribute_used_by_search_engine_timeSpent;
	public static String TargetProcessAttribute_used_by_search_engine_timeRemaining;
	public static String TargetProcessAttribute_used_by_search_engine_creationDate;

	public static String TargetProcessStatus_repositoryLoginFailure;
	public static String TargetProcessStatus_repositoryNotFound;
	public static String TargetProcessStatus_errorIo;
	public static String TargetProcessStatus_errorRepository;
	public static String TargetProcessStatus_errorInternal;
	public static String TargetProcessStatus_operationCancelled;
	public static String TargetProcessStatus_repositoryCollision;
	public static String TargetProcessStatus_repositoryCommentRequired;

	public static String TargetProcessRepositoryConnector_Unrecognized_response_from_server;
	public static String TargetProcessRepositoryConnector_Check_repository_configuration;
	public static String TargetProcessRepositoryConnector_checking_for_changed_tasks;
	public static String TargetProcessRepositoryConnector_Query_for_changed_tasks;
	public static String TargetProcessRepositoryConnector_running_query;
	public static String TargetProcessTaskDataHandler_Receiving_tasks;
	public static String TargetProcessAttribute_used_by_search_engine_entityKind;
	public static String TargetProcessAttribute_used_by_search_engine_numericPriority;
	public static String TargetProcessClient_Server_Connect_to_server_failed;
	public static String TargetProcessRepositoryConnector_Getting_changed_tasks;
	public static String TargetProcessTaskDataHandler_Submitting_task;
	public static String TargetProcessClient_Entity_was_deleted;
	public static String TargetProcessClient_Entity_was_deleted_while_gettaskdata;
	public static String TargetProcessClient_Entity_access_denied;
	public static String TargetProcessClient_Entity_cannot_be_retrieved_while_gettaskdata;
	public static String TargetProcessClientValidation_Bug_name_is_empty;
	public static String TargetProcessClientValidation_UserStory_name_is_empty;
	public static String TargetProcessClientValidation_Task_name_is_empty;
	public static String TargetProcessClientValidation_Request_name_is_empty;
	public static String TargetProcessTaskAttachmentHandler_Getting_attachment;
	public static String TargetProcessTaskAttachmentHandler_Sending_attachment;
	public static String TargetProcessTaskAttachmentHandler_unable_to_submit_attachment;
	public static String TargetProcessAttribute_ATTACH_ID;
	public static String TargetProcessAttribute_attachment;
	public static String TargetProcessAttribute_new_comment;
	public static String TargetProcessTaskAttachmentHandler_unable_to_retrieve_attachment;
	public static String TargetProcessAttribute_Task_Key;
	public static String EmptyMessage;
	public static String TargetProcessEntityPerformer_Default_Comment_On_Change_State;
}
