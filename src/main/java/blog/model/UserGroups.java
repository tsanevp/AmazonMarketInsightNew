package blog.model;

import java.util.Date;

public class UserGroups {
	protected int groupId;
	protected String groupName;
	protected Date created;
	protected int categoryId;
	
	public UserGroups(int groupId, String groupName, Date created, int categoryId) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.created = created;
		this.categoryId = categoryId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
