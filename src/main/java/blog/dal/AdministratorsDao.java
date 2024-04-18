package blog.dal;

import blog.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object (DAO) class to interact with the underlying Administrators
 * table in your MySQL instance. This is used to store {@link Administrators}
 * into your MySQL instance and retrieve {@link Administrators} from MySQL
 * instance.
 */
public class AdministratorsDao extends PersonsDao {
	private static AdministratorsDao instance = null;

	protected AdministratorsDao() {
		super();
	}

	public static AdministratorsDao getInstance() {
		if (instance == null) {
			instance = new AdministratorsDao();
		}
		return instance;
	}

	public Administrators create(Administrators administrator) throws SQLException {
		// Insert into the superclass table first.
		create(new Persons(administrator.getUserName(), administrator.getPassword(), administrator.getFirstName(),
				administrator.getLastName(), administrator.getEmail(), administrator.getPhoneNumber()));

		String insertAdministrator = "INSERT INTO Administrators(UserName,CanEditPosts,CanDeletePosts) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAdministrator);
			insertStmt.setString(1, administrator.getUserName());
			insertStmt.setBoolean(2, administrator.isCanEditPosts());
			insertStmt.setBoolean(3, administrator.isCanDeletePosts());
			insertStmt.executeUpdate();
			return administrator;
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

	public Administrators getAdministratorFromUserName(String userName) throws SQLException {
		// To build an Administrator object, we need the Persons record, too.
		String selectAdministrator = "SELECT Administrators.UserName AS UserName, Password, FirstName, LastName, Email, PhoneNumber, CanEditPosts, CanDeletePosts "
				+ "FROM Administrators INNER JOIN Persons " + "ON Administrators.UserName = Persons.UserName "
				+ "WHERE Administrators.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAdministrator);
			selectStmt.setString(1, userName);

			results = selectStmt.executeQuery();
			if (results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phoneNumber = results.getString("PhoneNumber");
				boolean canEditPosts = results.getBoolean("CanEditPosts");
				boolean canDeletePosts = results.getBoolean("CanDeletePosts");

				return new Administrators(resultUserName, password, firstName, lastName, email, phoneNumber,
						canEditPosts, canDeletePosts);
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

	public boolean isAdmin(String userName) throws SQLException {
		// To build an Administrator object, we need the Persons record, too.
		String selectAdministrator = "SELECT * "
				+ "FROM Administrators INNER JOIN Persons ON Administrators.UserName = Persons.UserName "
				+ "WHERE Administrators.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAdministrator);
			selectStmt.setString(1, userName);

			results = selectStmt.executeQuery();
			if (results.next()) {
				return true;
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
		return false;
	}

	/**
	 * Update the LastName of the Administrators instance. This runs a UPDATE
	 * statement.
	 */
	public Administrators updateEditPermissions(Administrators administrator, boolean newEditPermission)
			throws SQLException {
		String updateAdministrator = "UPDATE Administrators SET CanEditPosts=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAdministrator);
			updateStmt.setBoolean(1, newEditPermission);
			updateStmt.setString(2, administrator.getUserName());
			updateStmt.executeUpdate();

			// Update the person param before returning to the caller.
			administrator.setCanEditPosts(newEditPermission);
			return administrator;
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

	/**
	 * Delete the Administrators instance. This runs a DELETE statement.
	 */
	public boolean delete(String username) throws SQLException {
		String deleteAdministrator = "DELETE FROM Administrators WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAdministrator);
			deleteStmt.setString(1, username);
			deleteStmt.executeUpdate();

			super.delete(username);

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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
