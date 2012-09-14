package org.eclipse.mylyn.targetprocess.modules;

import java.net.URL;

import org.eclipse.core.runtime.Assert;

public class TargetProcessCredentials {

	private URL repositoryUrl;
	private String userName;
	private String password;
	private boolean isWindowsAuthentication;

	public TargetProcessCredentials(URL repositoryUrl, String userName, String password, boolean isWindowsAuthentication) {
		Assert.isNotNull(repositoryUrl);
		this.repositoryUrl = repositoryUrl;
		this.userName = userName == null ? "" : userName;
		this.password = password == null ? "" : password;
		this.isWindowsAuthentication = isWindowsAuthentication;
	}

	public URL getRepositoryUrl() {
		return repositoryUrl;
	}

	public String getUserName() {
		if (!isWindowsAuthentication()) {
			return userName;
		} else {
			int slashIndex = userName.indexOf('\\');
			if (slashIndex != -1) {
				return userName.substring(slashIndex + 1, userName.length());
			}
			return userName;
		}
	}

	public String getPassword() {
		return password;
	}

	public boolean isWindowsAuthentication() {
		return isWindowsAuthentication;
	}

	public String getDomain() {
		int slashIndex = userName.indexOf('\\');
		if (isWindowsAuthentication() && slashIndex != -1) {
			return userName.substring(0, slashIndex);
		}

		return "";
	}

	public String getOriginalUserName() {
		return userName;
	}

	@Override
	public boolean equals(java.lang.Object arg) {
		if (TargetProcessCredentials.class.isInstance(arg)) {
			TargetProcessCredentials targetProcessCredentials = (TargetProcessCredentials) arg;
			return repositoryUrl.toString().equals(targetProcessCredentials.repositoryUrl.toString())
					&& userName.equals(targetProcessCredentials.userName)
					&& password.equals(targetProcessCredentials.password)
					&& isWindowsAuthentication == targetProcessCredentials.isWindowsAuthentication;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return (repositoryUrl.toString() + " " + userName.toString() + " " + password.toString() + String.valueOf(isWindowsAuthentication)).hashCode();
	}

}
