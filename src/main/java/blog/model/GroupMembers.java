package blog.model;

import java.util.Date;

public class GroupMembers {
	protected int groupId;
	protected String userName;
	protected Roles role;
	protected Date joinDate;

	public enum Roles {
		OWNER, ADMIN, MEMBER
	};

	public GroupMembers(int groupId, String userName, Roles role, Date joinDate) {
		this.groupId = groupId;
		this.userName = userName;
		this.role = role;
		this.joinDate = joinDate;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
}
