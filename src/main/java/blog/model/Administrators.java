package blog.model;

/**
 * Administrators is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Persons}).
 */
public class Administrators extends Persons {
	protected boolean canEditPosts;
	protected boolean canDeletePosts;
	
	public Administrators(String userName, String password, String firstName, String lastName, String email,
			String phoneNumber, boolean canEditPosts, boolean canDeletePosts) {
		super(userName, password, firstName, lastName, email, phoneNumber);
		this.canEditPosts = canEditPosts;
		this.canDeletePosts = canDeletePosts;
	}

	public boolean isCanEditPosts() {
		return canEditPosts;
	}

	public void setCanEditPosts(boolean canEditPosts) {
		this.canEditPosts = canEditPosts;
	}

	public boolean isCanDeletePosts() {
		return canDeletePosts;
	}

	public void setCanDeletePosts(boolean canDeletePosts) {
		this.canDeletePosts = canDeletePosts;
	}
	
	
}
