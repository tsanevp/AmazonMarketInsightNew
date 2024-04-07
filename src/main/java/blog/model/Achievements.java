package blog.model;

import java.util.Date;

public class Achievements {
	protected int achievementId;
	protected String name;
	protected Date created;

	public Achievements(int achievementId, String name, Date created) {
		this.achievementId = achievementId;
		this.name = name;
		this.created = created;
	}

	public int getAchievementId() {
		return achievementId;
	}

	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}