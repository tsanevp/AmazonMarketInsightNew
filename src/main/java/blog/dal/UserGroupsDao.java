package blog.dal;

import blog.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		String insertUserGroup = "INSERT INTO UserGroups(GroupName,Created,CategoryId) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUserGroup, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, userGroup.getGroupName());
			insertStmt.setTimestamp(2, new Timestamp(userGroup.getCreated().getTime()));
			insertStmt.setInt(3, userGroup.getCategoryId());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int groupId = -1;
			
			if(resultKey.next()) {
				groupId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			
			userGroup.setGroupId(groupId);
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
		String selectUserGroup = "SELECT GroupId,GroupName,Created,CategoryId FROM UserGroups WHERE GroupId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUserGroup);
			selectStmt.setInt(1, userGroupId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultUserGroupId = results.getInt("GroupId");
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

	public List<UserGroups> getAllUserGroups() throws SQLException {
		List<UserGroups> userGroups = new ArrayList<>();
		String selectAllUserGroups = "SELECT * FROM UserGroups;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAllUserGroups);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int resultUserGroupId = results.getInt("GroupId");
				String groupName = results.getString("GroupName");
				Date created = new Date(results.getTimestamp("Created").getTime());
				int categoryId = results.getInt("CategoryId");

				userGroups.add(new UserGroups(resultUserGroupId, groupName, created, categoryId));
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
		return userGroups;
	}
	
	public UserGroups updateName(UserGroups userGroup, String newName) throws SQLException {
		String updateUserGroup = "UPDATE UserGroups SET GroupName=? WHERE GroupId=?;";
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

	public boolean delete(int groupId) throws SQLException {
		String deleteUserGroup = "DELETE FROM UserGroups WHERE GroupId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUserGroup);
			deleteStmt.setInt(1, groupId);

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
