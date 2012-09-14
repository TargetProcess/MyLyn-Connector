package org.eclipse.mylyn.targetprocess.core;

import java.io.Serializable;

public class RepositoryConfiguration implements Serializable {

	private static final long serialVersionUID = 7818778352173447605L;
	private String repositoryUrl = "<unknown>"; //$NON-NLS-1$ ;

	public String getRepositoryUrl() {
		// TODO Auto-generated method stub
		return repositoryUrl;
	}
	
	public void setRepositoryUrl(String repositoryUrl) {
		this.repositoryUrl = repositoryUrl;
	}

}
