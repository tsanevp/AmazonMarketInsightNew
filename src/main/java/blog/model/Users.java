package blog.model;

import java.util.Date;

/**
 * BlogUsers is a simple, plain old java objects (POJO). Well, almost (it
 * extends {@link Persons}).
 */
public class Users extends Persons {
	protected Date dob;
	protected boolean subscribed;

	public Users(String userName, String password, String firstName, String lastName, String email, String phoneNumber,
			Date dob, boolean subscribed) {
		super(userName, password, firstName, lastName, email, phoneNumber);
		this.dob = dob;
		this.subscribed = subscribed;
	}

	public Users(String userName, String password, String firstName, String lastName, String email, String phoneNumber,
			Date dob) {
		super(userName, password, firstName, lastName, email, phoneNumber);
		this.dob = dob;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public boolean isSubscribed() {
		return subscribed;
	}

	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}
}
