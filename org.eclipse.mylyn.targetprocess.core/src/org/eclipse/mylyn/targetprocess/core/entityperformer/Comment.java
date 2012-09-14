package org.eclipse.mylyn.targetprocess.core.entityperformer;

import java.util.Calendar;

public class Comment {

	private String description;
	private int ownerID;
	private Calendar createDate;	
	
	public Comment(String description, int ownerID, Calendar createDate) {
		this.setDescription(description);
		this.setOwnerID(ownerID);
		this.setCreateDate(createDate);		
	}

	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	public Calendar getCreateDate() {
		return createDate;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}

	public int getOwnerID() {
		return ownerID;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
