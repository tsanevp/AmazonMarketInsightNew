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

/**
 * Data access object (DAO) class to interact with the underlying Users table in your
 * MySQL instance. This is used to store {@link Users} into your MySQL instance and 
 * retrieve {@link Users} from MySQL instance.
 */
public class UsersDao extends PersonsDao {
	private static UsersDao instance = null;
	
	protected UsersDao() {
		super();
	}
	
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}

	public Users create(Users user) throws SQLException {
		// Insert into the superclass table first.
		create(new Persons(user.getUserName(), user.getPassword(), user.getFirstName(),
				user.getLastName(), user.getEmail(), user.getPhoneNumber()));


		String insertUser = "INSERT INTO Users(UserName,DoB,Subscribed) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			insertStmt.setString(1, user.getUserName());
			insertStmt.setTimestamp(2, new Timestamp(user.getDob().getTime()));
			insertStmt.setBoolean(3, user.isSubscribed());
			insertStmt.executeUpdate();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	public Users getUserFromUserName(String userName) throws SQLException {
		// To build an BlogUser object, we need the Persons record, too.
		String selectUser =
			"SELECT Users.UserName AS UserName, Password, FirstName, LastName, Email, PhoneNumber, DoB, Subscribed " +
			"FROM Users INNER JOIN Persons " +
				"ON Users.UserName = Persons.UserName " +
			"WHERE Users.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phoneNumber = results.getString("PhoneNumber");
				Date dob = new Date(results.getTimestamp("DoB").getTime());
				boolean subscribed = results.getBoolean("Subscribed");
				return new Users(resultUserName, password, firstName, lastName, email, phoneNumber, dob, subscribed);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public List<Users> getAllUsers() throws SQLException {
		List<Users> users = new ArrayList<Users>();

		String selectUser =
			"SELECT Users.UserName AS UserName, Password, FirstName, LastName, Email, PhoneNumber, DoB, Subscribed " +
			"FROM Users INNER JOIN Persons " +
				"ON Users.UserName = Persons.UserName;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String userName = results.getString("UserName");
				String password = results.getString("Password");
				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phoneNumber = results.getString("PhoneNumber");
				Date dob = new Date(results.getTimestamp("DoB").getTime());
				boolean subscribed = results.getBoolean("Subscribed");
				users.add(new Users(userName, password, resultFirstName, lastName, email, phoneNumber, dob, subscribed));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return users;
	}
	
	public List<Users> getUsersFromFirstName(String firstName) throws SQLException {
		List<Users> users = new ArrayList<Users>();

		String selectUser =
			"SELECT Users.UserName AS UserName, Password, FirstName, LastName, Email, PhoneNumber, DoB, Subscribed " +
			"FROM Users INNER JOIN Persons " +
				"ON Users.UserName = Persons.UserName " +
			"WHERE Persons.FirstName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, firstName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String userName = results.getString("UserName");
				String password = results.getString("Password");
				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phoneNumber = results.getString("PhoneNumber");
				Date dob = new Date(results.getTimestamp("DoB").getTime());
				boolean subscribed = results.getBoolean("Subscribed");
				users.add(new Users(userName, password, resultFirstName, lastName, email, phoneNumber, dob, subscribed));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return users;
	}


	public List<Users> getUserFromSubscribed(boolean isSubscribed) throws SQLException {
		List<Users> users = new ArrayList<Users>();

		String selectUser =
			"SELECT Users.UserName AS UserName, Password, FirstName, LastName, Email, PhoneNumber, DoB, Subscribed " +
			"FROM Users INNER JOIN Persons " +
				"ON Users.UserName = Persons.UserName " +
			"WHERE Users.Subscribed=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setBoolean(1, isSubscribed);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phoneNumber = results.getString("PhoneNumber");
				Date dob = new Date(results.getTimestamp("DoB").getTime());
				boolean subscribed = results.getBoolean("Subscribed");
				users.add(new Users(resultUserName, password, firstName, lastName, email, phoneNumber, dob, subscribed));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return users;
	}
	
	
	/**
	 * Update the LastName of the Users instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateSubsciption(Users user, boolean isSubscribed) throws SQLException {
		String updateUser = "UPDATE Users SET Subscribed=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUser);
			updateStmt.setBoolean(1, isSubscribed);
			updateStmt.setString(2, user.getUserName());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			user.setSubscribed(isSubscribed);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	/**
	 * Delete the Users instance.
	 * This runs a DELETE statement.
	 */
	public Users delete(Users user) throws SQLException {
		String deleteBlogUser = "DELETE FROM Users WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBlogUser);
			deleteStmt.setString(1, user.getUserName());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserName=" + user.getUserName());
			}

			super.delete(user);

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
