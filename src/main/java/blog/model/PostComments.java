package blog.model;

import java.util.Date;

public class PostComments {
	protected int postCommentId;
	protected Date created;
	protected String comment;
	protected int upVotes;
	protected int downVotes;
	protected String userName;
	protected String postId;
	
	public PostComments(int postCommentId, Date created, String comment, int upVotes, int downVotes, String userName,
			String postId) {
		this.postCommentId = postCommentId;
		this.created = created;
		this.comment = comment;
		this.upVotes = upVotes;
		this.downVotes = downVotes;
		this.userName = userName;
		this.postId = postId;
	}

	public int getPostCommentId() {
		return postCommentId;
	}

	public void setPostCommentId(int postCommentId) {
		this.postCommentId = postCommentId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}
}