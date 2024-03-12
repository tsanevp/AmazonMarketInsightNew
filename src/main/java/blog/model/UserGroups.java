package blog.model;

import java.util.Date;

public class UserGroups {
	protected int groupId;
	protected String groupName;
	protected Roles role;
	protected Date joinDate;
	protected String userName;
	protected int categoryId;

	public enum Roles {
		OWNER, ADMIN, MEMBER
	};
	
	public UserGroups(int groupId, String groupName, Roles role, Date joinDate, String userName, int categoryId) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.role = role;
		this.joinDate = joinDate;
		this.userName = userName;
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

	public Roles getRole() {
		return role;
	}

	public void setCuisine(Roles role) {
		this.role = role;
	}
	
	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}	
}
