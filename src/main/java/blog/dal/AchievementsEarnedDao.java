package blog.dal;

import blog.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Data access object (DAO) class to interact with the underlying AchievementsEarned table in your MySQL
 * instance. This is used to store {@link AchievementsEarned} into your MySQL instance and retrieve 
 * {@link AchievementsEarned} from MySQL instance.
 */
public class AchievementsEarnedDao {
	protected ConnectionManager connectionManager;
	private static AchievementsEarnedDao instance = null;
	
	protected AchievementsEarnedDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static AchievementsEarnedDao getInstance() {
		if(instance == null) {
			instance = new AchievementsEarnedDao();
		}
		return instance;
	}

	/**
	 * Save the AchievementsEarned instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public AchievementsEarned create(AchievementsEarned achievementEarned) throws SQLException {
		String insertAchievementEarned = "INSERT INTO AchievementsEarned(AchievementEarnedId,DateEarned,UserName,AchievementId) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAchievementEarned);

			insertStmt.setInt(1, achievementEarned.getAchievementEarnedId());
			insertStmt.setTimestamp(2, new Timestamp(achievementEarned.getDateEarned().getTime()));
			insertStmt.setString(2, achievementEarned.getUserName());
			insertStmt.setInt(2, achievementEarned.getAchievementId());

			insertStmt.executeUpdate();
			
			return achievementEarned;
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
	 * Get the AchievementsEarned record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single AchievementsEarned instance.
	 */
	public AchievementsEarned getAchievementFromId(int achievementEarnedId) throws SQLException {
		String selectAchievementEarned = "SELECT AchievementEarnedId,DateEarned,UserName,AchievementId FROM AchievementsEarned WHERE AchievementEarnedId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAchievementEarned);
			selectStmt.setInt(1, achievementEarnedId);

			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultAchievementEarnedId = results.getInt("AchievementEarnedId");
				Date dateEarned = new Date(results.getTimestamp("DateEarned").getTime());
				String userName = results.getString("UserName");
				int achievementId = results.getInt("AchievementId");

				return new AchievementsEarned(resultAchievementEarnedId, dateEarned, userName, achievementId);
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
	 * Delete the AchievementsEarned instance.
	 * This runs a DELETE statement.
	 */
	public AchievementsEarned delete(AchievementsEarned achievementEarned) throws SQLException {
		String deleteAchievementEarned = "DELETE FROM AchievementsEarned WHERE AchievementEarnedId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAchievementEarned);
			deleteStmt.setInt(1, achievementEarned.getAchievementId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the AchievementsEarned instance.
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
