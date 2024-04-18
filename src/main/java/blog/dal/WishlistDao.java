package blog.dal;

import blog.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object (DAO) class to interact with the underlying Wishlist table
 * in your MySQL instance. This is used to store {@link Wishlist} into your
 * MySQL instance and retrieve {@link Wishlist} from MySQL instance.
 */
public class WishlistDao {
	protected ConnectionManager connectionManager;
	private static WishlistDao instance = null;

	protected WishlistDao() {
		connectionManager = new ConnectionManager();
	}

	public static WishlistDao getInstance() {
		if (instance == null) {
			instance = new WishlistDao();
		}
		return instance;
	}

	/**
	 * Save the Wishlist instance by storing it in your MySQL instance. This runs a
	 * INSERT statement.
	 */
	public Wishlist create(Wishlist wishlist) throws SQLException {
		String insertWishlist = "INSERT INTO Wishlist(WishListId,UserName,ProductId) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertWishlist);

			insertStmt.setInt(1, wishlist.getWishListId());
			insertStmt.setString(2, wishlist.getUserName());
			insertStmt.setString(3, wishlist.getProductId());
			insertStmt.executeUpdate();

			return wishlist;
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
	 * Get the Wishlist record by fetching it from your MySQL instance. This runs a
	 * SELECT statement and returns a single Wishlist instance.
	 */
	public Wishlist getWishlistFromId(int wishlistId) throws SQLException {
		String selectWishlist = "SELECT WishListId,UserName,ProductId FROM Wishlist WHERE WishListId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWishlist);
			selectStmt.setInt(1, wishlistId);

			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultWishlistId = results.getInt("WishListId");
				String userName = results.getString("UserName");
				String productId = results.getString("ProductId");

				return new Wishlist(resultWishlistId, userName, productId);
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
	 * Get the Wishlist record by fetching it from your MySQL instance. This runs a
	 * SELECT statement and returns a single Wishlist instance.
	 */
	public List<Wishlist> getWishlistFromUserName(String userName) throws SQLException {
		List<Wishlist> wishlistItems = new ArrayList<Wishlist>();
		String selectWishlist = "SELECT WishListId,UserName,ProductId FROM Wishlist WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWishlist);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int wishlistId = results.getInt("WishListId");
				String resultsUserName = results.getString("UserName");
				String productId = results.getString("ProductId");

				wishlistItems.add(new Wishlist(wishlistId, resultsUserName, productId));
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

		return wishlistItems;
	}

	/**
	 * Delete the Wishlist instance. This runs a DELETE statement.
	 */
	public Wishlist delete(Wishlist wishlist) throws SQLException {
		String deleteWishlist = "DELETE FROM Wishlist WHERE WishListId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteWishlist);
			deleteStmt.setInt(1, wishlist.getWishListId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Wishlist instance.
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
