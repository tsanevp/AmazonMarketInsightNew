package blog.model;

import java.util.Date;

public class Posts {
	protected int postId;
	protected Date created;
	protected String review;
	protected double rating;
	protected int numInteractions;
	protected boolean active;
	protected int upVotes;
	protected int downVotes;
	protected int shares;
	protected String userName;
	protected String productId;
	
	public Posts(int postId, Date created, String review, double rating, int numInteractions, boolean active,
			int upVotes, int downVotes, int shares, String userName, String productId) {
		this.postId = postId;
		this.created = created;
		this.review = review;
		this.rating = rating;
		this.numInteractions = numInteractions;
		this.active = active;
		this.upVotes = upVotes;
		this.downVotes = downVotes;
		this.shares = shares;
		this.userName = userName;
		this.productId = productId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getNumInteractions() {
		return numInteractions;
	}

	public void setNumInteractions(int numInteractions) {
		this.numInteractions = numInteractions;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getUpVotes() {
		return upVotes;
	}

	public void setUpVotes(int upVotes) {
		this.upVotes = upVotes;
	}

	public int getDownVotes() {
		return downVotes;
	}

	public void setDownVotes(int downVotes) {
		this.downVotes = downVotes;
	}

	public int getShares() {
		return shares;
	}

	public void setShares(int shares) {
		this.shares = shares;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
}