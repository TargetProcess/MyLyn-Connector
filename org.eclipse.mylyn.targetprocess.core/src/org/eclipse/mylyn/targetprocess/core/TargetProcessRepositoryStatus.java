package org.eclipse.mylyn.targetprocess.core;

import org.eclipse.mylyn.tasks.core.RepositoryStatus;
import org.eclipse.osgi.util.NLS;

public class TargetProcessRepositoryStatus extends RepositoryStatus {

	public static final int ENTITY_NOT_FOUND = 1000;

	public static final int ENTITY_NOT_FOUND_WHILE_GETTASKDATA = 1001;

	public static final int ENTITY_ACCESS_DENIED = 1002;

	public static final int ENTITY_ACCESS_DENIED_WHILE_GETTASKDATA = 1003;

	public static final int ENTITY_VALIDATION_EXCEPTION_BASE = 1004;

	public static final int ENTITY_VALIDATION_EXCEPTION = 1005;

	private final String errorMessage;

	private String repositoryUrl = ""; //$NON-NLS-1$

	public TargetProcessRepositoryStatus(int severity, String pluginId, int code, String errorMessage) {
		super(severity, pluginId, code, "MylynStatus", null); //$NON-NLS-1$
		this.errorMessage = errorMessage;
	}

	public TargetProcessRepositoryStatus(int severity, String pluginId, int code, String repositoryUrl,
			String errorMessage) {
		super(severity, pluginId, code, "MylynStatus", null); //$NON-NLS-1$
		this.errorMessage = errorMessage;
		this.repositoryUrl = repositoryUrl;
	}

	public TargetProcessRepositoryStatus(int severity, String pluginId, int code, String repositoryUrl, Throwable e) {
		super(severity, pluginId, code, repositoryUrl, e);

		this.repositoryUrl = repositoryUrl;
		errorMessage = e.getMessage();
	}

	/**
	 * Returns the message that is relevant to the code of this status.
	 */
	@Override
	public String getMessage() {
		switch (getCode()) {
		case RepositoryStatus.ERROR_NETWORK:
			return NLS.bind(Messages.TargetProcessClient_Server_Connect_to_server_failed, getRepositoryUrl(),
					errorMessage);
		case TargetProcessRepositoryStatus.ENTITY_NOT_FOUND:
			return NLS.bind(Messages.TargetProcessClient_Entity_was_deleted, errorMessage);
		case TargetProcessRepositoryStatus.ENTITY_NOT_FOUND_WHILE_GETTASKDATA:
			return NLS.bind(Messages.TargetProcessClient_Entity_was_deleted_while_gettaskdata, getRepositoryUrl(), errorMessage);
		case TargetProcessRepositoryStatus.ENTITY_ACCESS_DENIED:
			return NLS.bind(Messages.TargetProcessClient_Entity_access_denied, getRepositoryUrl(), errorMessage);
		case TargetProcessRepositoryStatus.ENTITY_ACCESS_DENIED_WHILE_GETTASKDATA:
			return NLS.bind(Messages.TargetProcessClient_Entity_cannot_be_retrieved_while_gettaskdata, getRepositoryUrl(), errorMessage);
		case TargetProcessRepositoryStatus.ENTITY_VALIDATION_EXCEPTION_BASE:
			String exceptionType = "Tp.Integration.Common.EntityValidationException";
			int i = errorMessage.indexOf(exceptionType) + exceptionType.length();
			return errorMessage.substring(i, errorMessage.indexOf("\n", i));
		case TargetProcessRepositoryStatus.ENTITY_VALIDATION_EXCEPTION:
			return errorMessage;
		}

		if (errorMessage != null) {
			return errorMessage;
		} else if (getException() != null) {
			String message = getException().getMessage();
			if (message != null) {
				return message;
			} else {
				return getException().toString();
			}
		}
		return "Unknown"; //$NON-NLS-1$
	}

	@Override
	public String getRepositoryUrl() {
		return repositoryUrl;
	}

	public void setRepositoryUrl(String repositoryUrl) {
		this.repositoryUrl = repositoryUrl;
	}

}
