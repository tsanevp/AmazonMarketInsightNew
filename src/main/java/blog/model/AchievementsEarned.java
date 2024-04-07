package blog.model;

import java.util.Date;

public class AchievementsEarned {
	protected int achievementEarnedId;
	protected Date dateEarned;
	protected String userName;
	protected int achievementId;

	public AchievementsEarned(int achievementEarnedId, Date dateEarned, String userName, int achievementId) {
		this.achievementEarnedId = achievementEarnedId;
		this.dateEarned = dateEarned;
		this.userName = userName;
		this.achievementId = achievementId;
	}

	public int getAchievementEarnedId() {
		return achievementEarnedId;
	}

	public void setAchievementEarnedId(int achievementEarnedId) {
		this.achievementEarnedId = achievementEarnedId;
	}

	public Date getDateEarned() {
		return dateEarned;
	}

	public void setDateEarned(Date dateEarned) {
		this.dateEarned = dateEarned;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAchievementId() {
		return achievementId;
	}

	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}
}