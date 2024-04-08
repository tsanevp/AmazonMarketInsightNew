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
 * Data access object (DAO) class to interact with the underlying CreditCards
 * table in your MySQL instance. This is used to store {@link CreditCards} into
 * your MySQL instance and retrieve {@link CreditCards} from MySQL instance.
 */
public class CreditCardsDao {
	protected ConnectionManager connectionManager;
	private static CreditCardsDao instance = null;

	protected CreditCardsDao() {
		connectionManager = new ConnectionManager();
	}

	public static CreditCardsDao getInstance() {
		if (instance == null) {
			instance = new CreditCardsDao();
		}
		return instance;
	}

	/**
	 * Save the CreditCards instance by storing it in your MySQL instance. This runs
	 * a INSERT statement.
	 */
	public CreditCards create(CreditCards creditCard) throws SQLException {
		String insertCreditCard = "INSERT INTO CreditCards(CardNumber,Expiration,UserName) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCreditCard);

			insertStmt.setLong(1, creditCard.getCardNumber());
			insertStmt.setTimestamp(2, new Timestamp(creditCard.getExpiration().getTime()));
			insertStmt.setString(3, creditCard.getUserName());
			insertStmt.executeUpdate();

			return creditCard;
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

	/**
	 * Get the CreditCards record by fetching it from your MySQL instance. This runs
	 * a SELECT statement and returns a single CreditCards instance.
	 */
	public CreditCards getCreditCardFromId(long cardNumber) throws SQLException {
		String selectCreditCard = "SELECT CardNumber,Expiration,UserName FROM CreditCards WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCard);
			selectStmt.setLong(1, cardNumber);

			results = selectStmt.executeQuery();
			if (results.next()) {
				long resultCardNumber = results.getLong("CardNumber");
				Date expiration = new Date(results.getTimestamp("Expiration").getTime());
				String userName = results.getString("UserName");

				return new CreditCards(resultCardNumber, expiration, userName);
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

	/**
	 * Gets all CreditCards who have the same UserName. This runs a SELECT statement
	 * and returns a list of matching CreditCards.
	 * 
	 * @param userName - The userName to query for.
	 * @return - A list of matching CreditCards.
	 * @throws SQLException is thrown if an error occurs while retrieving those
	 *                      CreditCards from MySQL.
	 */
	public List<CreditCards> getCreditCardsByUserName(String userName) throws SQLException {
		List<CreditCards> creditCards = new ArrayList<CreditCards>();
		String selectCreditCards = "SELECT CardNumber,Expiration,UserName FROM CreditCards WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCards);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			while (results.next()) {
				long cardNumber = results.getLong("CardNumber");
				Date expiration = new Date(results.getTimestamp("Expiration").getTime());
				String resultsUserName = results.getString("UserName");

				creditCards.add(new CreditCards(cardNumber, expiration, resultsUserName));
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
		return creditCards;
	}

	/**
	 * Update the LastName of the CreditCards instance. This runs a UPDATE
	 * statement.
	 */
	public CreditCards updateExpiration(CreditCards creditCard, Date newExpiration) throws SQLException {
		String updateCreditCard = "UPDATE CreditCards SET Expiration=? WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCreditCard);
			updateStmt.setTimestamp(1, new Timestamp(newExpiration.getTime()));
			updateStmt.setLong(2, creditCard.getCardNumber());
			updateStmt.executeUpdate();

			// Update the creditCard param before returning to the caller.
			creditCard.setExpiration(newExpiration);
			return creditCard;
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
	 * Delete the CreditCards instance. This runs a DELETE statement.
	 */
	public CreditCards delete(CreditCards creditCard) throws SQLException {
		String deleteCreditCard = "DELETE FROM CreditCards WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCreditCard);
			deleteStmt.setLong(1, creditCard.getCardNumber());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the CreditCards instance.
			return null;
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
