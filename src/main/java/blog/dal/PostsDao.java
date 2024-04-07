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
 * Data access object (DAO) class to interact with the underlying Posts table in
 * your MySQL instance. This is used to store {@link Posts} into your MySQL
 * instance and retrieve {@link Posts} from MySQL instance.
 */
public class PostsDao {
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static PostsDao instance = null;
	private static ProductsDao instanceProduct = null;

	protected PostsDao() {
		connectionManager = new ConnectionManager();
	}

	public static PostsDao getInstance() {
		if (instance == null && instanceProduct == null) {
			instance = new PostsDao();
			instanceProduct = new ProductsDao();
		}
		return instance;
	}

	/**
	 * Save the Posts instance by storing it in your MySQL instance. This runs a
	 * INSERT statement.
	 */
	public Posts create(Posts post) throws SQLException {
		String insertPost = "INSERT INTO Posts(PostId,Created,Review,Rating,NumInteractions,Active,UpVotes,DownVotes,Shares,UserName,ProductId) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPost);

			insertStmt.setInt(1, post.getPostId());
			insertStmt.setTimestamp(2, new Timestamp(post.getCreated().getTime()));
			insertStmt.setString(3, post.getReview());
			insertStmt.setDouble(4, post.getRating());
			insertStmt.setInt(5, post.getNumInteractions());
			insertStmt.setBoolean(6, post.isActive());
			insertStmt.setInt(7, post.getUpVotes());
			insertStmt.setInt(8, post.getDownVotes());
			insertStmt.setInt(9, post.getShares());
			insertStmt.setString(10, post.getUserName());
			insertStmt.setString(11, post.getProductId());

			insertStmt.executeUpdate();

			return post;
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
	 * Get the Posts record by fetching it from your MySQL instance. This runs a
	 * SELECT statement and returns a single Posts instance.
	 */
	public Posts getPostFromPostId(int postId) throws SQLException {
		String selectPost = "SELECT PostId,Created,Review,Rating,NumInteractions,Active,UpVotes,DownVotes,Shares,UserName,ProductId FROM Posts WHERE PostId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPost);
			selectStmt.setInt(1, postId);

			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultPostId = results.getInt("PostId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				String review = results.getString("Review");
				double rating = results.getDouble("Rating");
				int numInteractions = results.getInt("NumInteractions");
				boolean active = results.getBoolean("Active");
				int upVotes = results.getInt("UpVotes");
				int downVotes = results.getInt("DownVotes");
				int shares = results.getInt("Shares");
				String userName = results.getString("UserName");
				String productId = results.getString("ProductId");

				return new Posts(resultPostId, created, review, rating, numInteractions, active, upVotes, downVotes,
						shares, userName, productId);
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
	 * Get the Posts record by fetching it from your MySQL instance. This runs a
	 * SELECT statement and returns a single Posts instance.
	 */
	public List<Posts> getPostsFromUserName(String userName) throws SQLException {
		List<Posts> posts = new ArrayList<Posts>();

		String selectPost = "SELECT PostId,Created,Review,Rating,NumInteractions,Active,UpVotes,DownVotes,Shares,UserName,ProductId FROM Posts WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPost);
			selectStmt.setString(1, userName);

			results = selectStmt.executeQuery();
			while (results.next()) {
				int postId = results.getInt("PostId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				String review = results.getString("Review");
				double rating = results.getDouble("Rating");
				int numInteractions = results.getInt("NumInteractions");
				boolean active = results.getBoolean("Active");
				int upVotes = results.getInt("UpVotes");
				int downVotes = results.getInt("DownVotes");
				int shares = results.getInt("Shares");
				String resultsUserName = results.getString("UserName");
				String productId = results.getString("ProductId");

				posts.add(new Posts(postId, created, review, rating, numInteractions, active, upVotes, downVotes,
						shares, resultsUserName, productId));
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
		return posts;
	}

	/**
	 * Get the Posts record by fetching it from your MySQL instance. This runs a
	 * SELECT statement and returns a single Posts instance.
	 */
	public List<Posts> getAllPosts() throws SQLException {
		List<Posts> posts = new ArrayList<Posts>();

		String selectPost = "SELECT PostId,Created,Review,Rating,NumInteractions,Active,UpVotes,DownVotes,Shares,UserName,ProductId FROM Posts;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPost);

			results = selectStmt.executeQuery();
			while (results.next()) {
				int postId = results.getInt("PostId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				String review = results.getString("Review");
				double rating = results.getDouble("Rating");
				int numInteractions = results.getInt("NumInteractions");
				boolean active = results.getBoolean("Active");
				int upVotes = results.getInt("UpVotes");
				int downVotes = results.getInt("DownVotes");
				int shares = results.getInt("Shares");
				String resultsUserName = results.getString("UserName");
				String productId = results.getString("ProductId");

				posts.add(new Posts(postId, created, review, rating, numInteractions, active, upVotes, downVotes,
						shares, resultsUserName, productId));
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
		return posts;
	}

	/**
	 * Update the LastName of the Posts instance. This runs a UPDATE statement.
	 */
	public Posts updateReview(Posts post, String newReview) throws SQLException {
		String updatePost = "UPDATE Posts SET Review=? WHERE PostId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePost);
			updateStmt.setString(1, newReview);
			updateStmt.setInt(2, post.getPostId());
			updateStmt.executeUpdate();

			// Update the post param before returning to the caller.
			post.setReview(newReview);
			return post;
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
	 * Delete the Posts instance. This runs a DELETE statement.
	 */
	public Posts delete(Posts post) throws SQLException {
		String deletePost = "DELETE FROM Posts WHERE PostId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePost);
			deleteStmt.setInt(1, post.getPostId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Posts instance.
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
