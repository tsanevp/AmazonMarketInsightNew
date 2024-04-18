package blog.dal;

import blog.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupMembersDao {
	protected ConnectionManager connectionManager;

	private static GroupMembersDao instance = null;

	protected GroupMembersDao() {
		connectionManager = new ConnectionManager();
	}

	public static GroupMembersDao getInstance() {
		if (instance == null) {
			instance = new GroupMembersDao();
		}
		return instance;
	}

	public GroupMembers create(GroupMembers groupMember) throws SQLException {
		String insertGroupMember = "INSERT INTO GroupMembers(GroupId,UserName,Role,JoinDate) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertGroupMember);
			insertStmt.setInt(1, groupMember.getGroupId());
			insertStmt.setString(2, groupMember.getUserName());
			insertStmt.setString(3, groupMember.getRole().name());
			insertStmt.setTimestamp(4, new Timestamp(groupMember.getJoinDate().getTime()));

			insertStmt.executeUpdate();
			return groupMember;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	public GroupMembers getGroupMemberById(int groupId, String userName) throws SQLException {
		String selectGroupMember = "SELECT GroupId,UserName,Role,JoinDate FROM GroupMembers WHERE GroupId=? AND UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGroupMember);
			selectStmt.setInt(1, groupId);
			selectStmt.setString(2, userName);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultGroupId = results.getInt("GroupId");
				String resultUserName = results.getString("UserName");
				String role = results.getString("Role");
				Date joinDate = new Date(results.getTimestamp("JoinDate").getTime());

				return new GroupMembers(resultGroupId, resultUserName, GroupMembers.Roles.valueOf(role.toUpperCase()),
						joinDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public List<GroupMembers> getAllMembersByGroupId(int groupId) throws SQLException {
		List<GroupMembers> groupMembers = new ArrayList<>();
		
		String selectGroupMembers = "SELECT * FROM GroupMembers WHERE GroupId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGroupMembers);
			selectStmt.setInt(1, groupId);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int resultGroupId = results.getInt("GroupId");
				String userName = results.getString("UserName");
				String role = results.getString("Role");
				Date joinDate = new Date(results.getTimestamp("JoinDate").getTime());

				groupMembers.add(new GroupMembers(resultGroupId, userName, GroupMembers.Roles.valueOf(role.toUpperCase()),
						joinDate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return groupMembers;
	}

	public boolean isUserMemberOfGroup(String username, int groupId) throws SQLException {
		String selectGroupMember = "SELECT * FROM GroupMembers WHERE GroupId=? AND UserName=? AND Role!='Owner';";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGroupMember);
			selectStmt.setInt(1, groupId);
			selectStmt.setString(2, username);
			results = selectStmt.executeQuery();

			if (results.next()) {
				return true;
			}
			
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
	}
	
	public boolean isUserOwnerOfGroup(String username, int groupId) throws SQLException {
		String selectGroupMember = "SELECT * FROM GroupMembers WHERE GroupId=? AND UserName=? AND Role='Owner';";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGroupMember);
			selectStmt.setInt(1, groupId);
			selectStmt.setString(2, username);
			results = selectStmt.executeQuery();

			if (results.next()) {
				return true;
			}
			
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
	}
	
	public GroupMembers updateRole(GroupMembers groupMember, GroupMembers.Roles newRole) throws SQLException {
		String updateGroupMember = "UPDATE GroupMembers SET Role=? WHERE GroupId=? AND UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateGroupMember);
			updateStmt.setString(1, newRole.name());
			updateStmt.setInt(2, groupMember.getGroupId());
			updateStmt.setString(3, groupMember.getUserName());

			updateStmt.executeUpdate();

			groupMember.setRole(newRole);
			return groupMember;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	public boolean delete(GroupMembers groupMember) throws SQLException {
		String deleteGroupMember = "DELETE FROM GroupMembers WHERE GroupId=? AND UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteGroupMember);
			deleteStmt.setInt(1, groupMember.getGroupId());
			deleteStmt.setString(2, groupMember.getUserName());

			int affectedRows = deleteStmt.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
