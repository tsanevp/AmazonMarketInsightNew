package blog.servlet;

import blog.dal.*;
import blog.model.*;
import blog.util.SessionUtil;
import blog.util.ValidationUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/user_posts")
public class UserPosts extends HttpServlet {

	protected UsersDao usersDao;
	protected PostsDao postsDao;

	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
		postsDao = PostsDao.getInstance();
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

		String userToView = req.getParameter("username");

		if (!ValidationUtil.isValidString(userToView)) {
			messages.put("error", "No username to view was given.");
			resp.sendRedirect(req.getContextPath() + "/all_users");
			return;
		}

		Users user = null;
		List<Posts> posts = new ArrayList<>();

		try {
			user = usersDao.getUserFromUserName(userToView);
			posts = postsDao.getPostsFromUserName(userToView);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}

		req.setAttribute("user", user);
		req.setAttribute("posts", posts);

		// Just render the JSP.
		req.getRequestDispatcher("/WEB-INF/jsp/UserPosts.jsp").forward(req, resp);
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
			messages.put("error", "No postId was given.");
			resp.sendRedirect(req.getContextPath() + "/all_users");
			return;
		}

		int postId = -1;

		try {
			postId = Integer.parseInt(postIdStr);
		} catch (NumberFormatException e) {
			messages.put("error", "You provided an invalid post id");
			resp.sendRedirect(req.getContextPath() + "/all_users");
			return;
		}

		try {
			postsDao.delete(postId);
		} catch (SQLException e) {
			e.printStackTrace();
			messages.put("error", "Error deleting the post");
			resp.sendRedirect(req.getContextPath() + "/all_users");
			return;
		}

		messages.put("success", "Deleted postId: " + postId);

		resp.sendRedirect(req.getContextPath() + "/user_posts");
	}
}
