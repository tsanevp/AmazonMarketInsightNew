package blog.dal;

import blog.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class UserGroupsDao {
	protected ConnectionManager connectionManager;

	private static UserGroupsDao instance = null;

	protected UserGroupsDao() {
		connectionManager = new ConnectionManager();
	}

	public static UserGroupsDao getInstance() {
		if (instance == null) {
			instance = new UserGroupsDao();
		}
		return instance;
	}

	public UserGroups create(UserGroups userGroup) throws SQLException {
		String insertUserGroup = "INSERT INTO UserGroups(UserGroupId,GroupName,Created,CategoryId) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUserGroup);
			insertStmt.setInt(1, userGroup.getGroupId());
			insertStmt.setString(2, userGroup.getGroupName());
			insertStmt.setTimestamp(3, new Timestamp(userGroup.getCreated().getTime()));
			insertStmt.setInt(4, userGroup.getCategoryId());

			insertStmt.executeUpdate();
			return userGroup;
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

	public UserGroups getUserGroupById(int userGroupId) throws SQLException {
		String selectUserGroup = "SELECT UserGroupId,GroupName,Created,CategoryId FROM UserGroups WHERE UserGroupId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUserGroup);
			selectStmt.setInt(1, userGroupId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultUserGroupId = results.getInt("UserGroupId");
				String groupName = results.getString("GroupName");
				Date created = new Date(results.getTimestamp("Created").getTime());
				int categoryId = results.getInt("CategoryId");
				
				return new UserGroups(resultUserGroupId, groupName, created, categoryId);
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
	
	public UserGroups updateName(UserGroups userGroup, String newName) throws SQLException {
		String updateUserGroup = "UPDATE UserGroups SET GroupName=? WHERE UserGroupId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUserGroup);
			updateStmt.setString(1, newName);
			updateStmt.setInt(2, userGroup.getGroupId());

			updateStmt.executeUpdate();
			
			userGroup.setGroupName(newName);
			return userGroup;
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

	public boolean delete(String userGroupId) throws SQLException {
		String deleteUserGroup = "DELETE FROM UserGroups WHERE UserGroupId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUserGroup);
			deleteStmt.setString(1, userGroupId);

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
