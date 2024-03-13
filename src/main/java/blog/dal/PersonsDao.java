package blog.dal;

import blog.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object (DAO) class to interact with the underlying Persons table in your MySQL
 * instance. This is used to store {@link Persons} into your MySQL instance and retrieve 
 * {@link Persons} from MySQL instance.
 */
public class PersonsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static PersonsDao instance = null;
	protected PersonsDao() {
		connectionManager = new ConnectionManager();
	}
	public static PersonsDao getInstance() {
		if(instance == null) {
			instance = new PersonsDao();
		}
		return instance;
	}

	/**
	 * Save the Persons instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Persons create(Persons person) throws SQLException {
		String insertPerson = "INSERT INTO Persons(UserName,Password,FirstName,LastName,Email,PhoneNumber) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPerson);

			insertStmt.setString(1, person.getUserName());
			insertStmt.setString(2, person.getPassword());
			insertStmt.setString(3, person.getFirstName());
			insertStmt.setString(4, person.getLastName());
			insertStmt.setString(5, person.getEmail());
			insertStmt.setString(6, person.getPhoneNumber());
			insertStmt.executeUpdate();
			
			return person;
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

	/**
	 * Get the Persons record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Persons instance.
	 */
	public Persons getPersonFromUserName(String userName) throws SQLException {
		String selectPerson = "SELECT UserName,Password,FirstName,LastName,Email,PhoneNumber FROM Persons WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setString(1, userName);

			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phoneNumber = results.getString("PhoneNumber");
				return new Persons(resultUserName, password, firstName, lastName, email, phoneNumber);
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
	
	/**
	 * Update the LastName of the Persons instance.
	 * This runs a UPDATE statement.
	 */
	public Persons updateFirstName(Persons person, String newFirstName) throws SQLException {
		String updatePerson = "UPDATE Persons SET FirstName=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newFirstName);
			updateStmt.setString(2, person.getUserName());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			person.setFirstName(newFirstName);
			return person;
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
	 * Delete the Persons instance.
	 * This runs a DELETE statement.
	 */
	public Persons delete(Persons person) throws SQLException {
		String deletePerson = "DELETE FROM Persons WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePerson);
			deleteStmt.setString(1, person.getUserName());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
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
