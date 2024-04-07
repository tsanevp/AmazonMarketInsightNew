package blog.dal;

import blog.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Data access object (DAO) class to interact with the underlying Achievements
 * table in your MySQL instance. This is used to store {@link Achievements} into
 * your MySQL instance and retrieve {@link Achievements} from MySQL instance.
 */
public class AchievementsDao {
	protected ConnectionManager connectionManager;
	private static AchievementsDao instance = null;

	protected AchievementsDao() {
		connectionManager = new ConnectionManager();
	}

	public static AchievementsDao getInstance() {
		if (instance == null) {
			instance = new AchievementsDao();
		}
		return instance;
	}

	/**
	 * Save the Achievements instance by storing it in your MySQL instance. This
	 * runs a INSERT statement.
	 */
	public Achievements create(Achievements achievement) throws SQLException {
		String insertAchievement = "INSERT INTO Achievements(AchievementId,Name,Created) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAchievement);

			insertStmt.setInt(1, achievement.getAchievementId());
			insertStmt.setString(2, achievement.getName());
			insertStmt.setTimestamp(3, new Timestamp(achievement.getCreated().getTime()));
			insertStmt.executeUpdate();

			return achievement;
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
	 * Get the Achievements record by fetching it from your MySQL instance. This
	 * runs a SELECT statement and returns a single Achievements instance.
	 */
	public Achievements getAchievementFromId(int achievementId) throws SQLException {
		String selectAchievement = "SELECT AchievementId,Name,Created FROM Achievements WHERE AchievementId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAchievement);
			selectStmt.setInt(1, achievementId);

			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultAchievementId = results.getInt("AchievementId");
				String name = results.getString("Name");
				Date created = new Date(results.getTimestamp("Created").getTime());
				return new Achievements(resultAchievementId, name, created);
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
	 * Update the LastName of the Achievements instance. This runs a UPDATE
	 * statement.
	 */
	public Achievements updateName(Achievements achievement, String newName) throws SQLException {
		String updateAchievement = "UPDATE Achievements SET Name=? WHERE AchievementId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAchievement);
			updateStmt.setString(1, newName);
			updateStmt.setInt(2, achievement.getAchievementId());
			updateStmt.executeUpdate();

			// Update the achievement param before returning to the caller.
			achievement.setName(newName);
			return achievement;
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
	 * Delete the Achievements instance. This runs a DELETE statement.
	 */
	public Achievements delete(Achievements achievement) throws SQLException {
		String deleteAchievement = "DELETE FROM Achievements WHERE AchievementId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAchievement);
			deleteStmt.setInt(1, achievement.getAchievementId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Achievements instance.
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
