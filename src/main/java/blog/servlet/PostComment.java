package blog.servlet;

import blog.dal.*;
import blog.model.*;
import blog.util.SessionUtil;
import blog.util.ValidationUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/post_comment")
public class PostComment extends HttpServlet {

	protected PostsDao postsDao;
	protected PostCommentsDao postCommentsDao;

	@Override
	public void init() throws ServletException {
		postsDao = PostsDao.getInstance();
		postCommentsDao = PostCommentsDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = SessionUtil.getUsername(req, resp);

		if (username == null) {
			return;
		}
		
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		
		String postIdStr = req.getParameter("postId");
		if (!ValidationUtil.isValidString(postIdStr)) {
			messages.put("error", "No postId was given.");
			req.getRequestDispatcher("/WEB-INF/jsp/UserPosts.jsp").forward(req, resp);
			return;
		}
		
		req.getRequestDispatcher("/WEB-INF/jsp/SinglePost.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = SessionUtil.getUsername(req, resp);

		if (username == null) {
			return;
		}
		
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		
		String postIdStr = req.getParameter("postId");
		if (postIdStr == null || postIdStr.trim().isEmpty()) {
			messages.put("error", "There was an error retrieving the post.");
			resp.sendRedirect(req.getContextPath() + "/all_posts");
			return;
		}
		
		int postId = -1;
		
		try {
		    postId = Integer.parseInt(postIdStr);
		} catch (NumberFormatException e) {
			messages.put("error", "You provided an invalid post id");
			resp.sendRedirect(req.getContextPath() + "/all_posts");
			return;
		}

		String comment = req.getParameter("commentContent");
		if (comment == null || comment.trim().isEmpty()) {
			messages.put("error", "The comment had no content was given.");
			resp.sendRedirect(req.getContextPath() + "/view_post?postId=" + postIdStr);
			return;
		}
				
		try {
			postCommentsDao.create(new PostComments(comment, username, postId));
		} catch (SQLException e) {
			e.printStackTrace();
			messages.put("error", "Comment could not be added to the post. Try again.");
		}
		
		resp.sendRedirect(req.getContextPath() + "/view_post?postId=" + postIdStr);
	}
}
