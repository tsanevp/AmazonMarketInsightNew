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

/**
 * Data access object (DAO) class to interact with the underlying PostComments
 * table in your MySQL instance. This is used to store {@link PostComments} into
 * your MySQL instance and retrieve {@link PostComments} from MySQL instance.
 */
public class PostCommentsDao {
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static PostCommentsDao instance = null;

	protected PostCommentsDao() {
		connectionManager = new ConnectionManager();
	}

	public static PostCommentsDao getInstance() {
		if (instance == null) {
			instance = new PostCommentsDao();
		}
		return instance;
	}

	/**
	 * Save the PostComments instance by storing it in your MySQL instance. This
	 * runs a INSERT statement.
	 */
	public PostComments create(PostComments postComment) throws SQLException {
		String insertPostComment = "INSERT INTO PostComments(Created,Comment,UpVotes,DownVotes,UserName,PostId) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPostComment, Statement.RETURN_GENERATED_KEYS);

			insertStmt.setTimestamp(1, new Timestamp(postComment.getCreated().getTime()));
			insertStmt.setString(2, postComment.getComment());
			insertStmt.setInt(3, postComment.getUpVotes());
			insertStmt.setInt(4, postComment.getDownVotes());
			insertStmt.setString(5, postComment.getUserName());
			insertStmt.setInt(6, postComment.getPostId());
			insertStmt.executeUpdate();

			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int commentId = -1;
			
			if(resultKey.next()) {
				commentId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			
			postComment.setPostCommentId(commentId);
			return postComment;
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
	 * Get the PostComments record by fetching it from your MySQL instance. This
	 * runs a SELECT statement and returns a single PostComments instance.
	 */
	public PostComments getPostCommentFromPostCommentId(int postCommentId) throws SQLException {
		String selectPostComment = "SELECT PostCommentId,Created,Comment,UpVotes,DownVotes,UserName,PostId FROM PostComments WHERE PostCommentId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPostComment);
			selectStmt.setInt(1, postCommentId);

			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultPostCommentId = results.getInt("PostCommentId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				String comment = results.getString("Comment");
				int upVotes = results.getInt("UpVotes");
				int downVotes = results.getInt("DownVotes");
				String userName = results.getString("UserName");
				int postId = results.getInt("PostId");

				return new PostComments(resultPostCommentId, created, comment, upVotes, downVotes, userName, postId);
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
	 * Get the Post's comments records by fetching it from your MySQL instance. This runs a
	 * SELECT statement and returns a list of comments for single Posts instance.
	 */
	public List<PostComments> getCommentsFromPostId(int postId) throws SQLException {
		List<PostComments> postComments = new ArrayList<>();

		String selectComments = "SELECT PostCommentId,Created,Comment,UpVotes,DownVotes,UserName,PostId FROM PostComments WHERE PostId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComments);
			selectStmt.setInt(1, postId);

			results = selectStmt.executeQuery();
			while (results.next()) {
				int postCommentId = results.getInt("PostCommentId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				String comment = results.getString("Comment");
				int upVotes = results.getInt("UpVotes");
				int downVotes = results.getInt("DownVotes");
				String userName = results.getString("UserName");
				int resultPostId = results.getInt("PostId");

				postComments.add(new PostComments(postCommentId, created, comment, upVotes, downVotes, userName, resultPostId));
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
		return postComments;
	}
	
	/**
	 * Get the Post's comments records by fetching it from your MySQL instance. This runs a
	 * SELECT statement and returns a list of comments for single Posts instance.
	 */
	public List<PostComments> getCommentsFromUserName(String username) throws SQLException {
		List<PostComments> postComments = new ArrayList<>();

		String selectComments = "SELECT PostCommentId,Created,Comment,UpVotes,DownVotes,UserName,PostId FROM PostComments WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComments);
			selectStmt.setString(1, username);

			results = selectStmt.executeQuery();
			while (results.next()) {
				int postCommentId = results.getInt("PostCommentId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				String comment = results.getString("Comment");
				int upVotes = results.getInt("UpVotes");
				int downVotes = results.getInt("DownVotes");
				String userName = results.getString("UserName");
				int resultPostId = results.getInt("PostId");

				postComments.add(new PostComments(postCommentId, created, comment, upVotes, downVotes, userName, resultPostId));
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
		return postComments;
	}
	
	/**
	 * Update the LastName of the PostComments instance. This runs a UPDATE
	 * statement.
	 */
	public PostComments updateComment(PostComments postComment, String newComment) throws SQLException {
		String updatePost = "UPDATE PostComments SET Comment=? WHERE PostCommentId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePost);
			updateStmt.setString(1, newComment);
			updateStmt.setInt(2, postComment.getPostCommentId());
			updateStmt.executeUpdate();

			// Update the postComment param before returning to the caller.
			postComment.setComment(newComment);
			return postComment;
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
	 * Delete the PostComments instance. This runs a DELETE statement.
	 */
	public PostComments delete(int commentId) throws SQLException {
		String deletePost = "DELETE FROM PostComments WHERE PostCommentId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePost);
			deleteStmt.setInt(1, commentId);
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the PostComments instance.
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
