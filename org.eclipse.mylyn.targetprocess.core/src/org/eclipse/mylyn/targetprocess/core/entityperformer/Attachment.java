package org.eclipse.mylyn.targetprocess.core.entityperformer;

import java.rmi.RemoteException;
import java.util.Date;

import org.eclipse.mylyn.targetprocess.modules.services.FileServiceStub.GetFileSize;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IFileServiceStub;

public class Attachment {
	private String originalFileName;
	private String description;
	private int fileId;
	private Long fileSize;
	private IFileServiceStub fileServiceStub;
	private String uniqueFileName;
	private Date creationDate;
	private int ownerId;
	private int ID;

	public Attachment(IFileServiceStub fileServiceStub, String originalFileName, String description, int fileId,
			String uniqueFileName, Date creationDate, int ownerId, int attachmentID) {

		setUniqueFileName(uniqueFileName);
		this.fileServiceStub = fileServiceStub;
		setOriginalFileName(originalFileName);
		setDescription(description);
		setFileId(fileId);
		setFileSize(fileSize);
		setCreationDate(creationDate);
		setOwnerId(ownerId);
		setID(attachmentID);
	}

	public void setID(int attachmentID) {
		ID = attachmentID;
	}

	public int getID() {
		return ID;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public int getFileId() {
		return fileId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setOriginalFileName(String generalName) {
		originalFileName = generalName;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public Long getFileSize() {
		GetFileSize getFileSize = new GetFileSize();
		getFileSize.setFileName(getUniqueFileName());
		try {
			return fileServiceStub.getFileSize(getFileSize).getGetFileSizeResult();
		} catch (RemoteException e) {
			return new Long(0);
		}
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public void setUniqueFileName(String uniqueFileName) {
		this.uniqueFileName = uniqueFileName;
	}

	public String getUniqueFileName() {
		return uniqueFileName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getOwnerID() {
		return getOwnerId();
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getOwnerId() {
		return ownerId;
	}

}
