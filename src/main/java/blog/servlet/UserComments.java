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
@WebServlet("/user_comments")
public class UserComments extends HttpServlet {

	protected UsersDao usersDao;
	protected PostCommentsDao commentsDao;

	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
		commentsDao = PostCommentsDao.getInstance();
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
		List<PostComments> comments = new ArrayList<>();

		try {
			user = usersDao.getUserFromUserName(userToView);
			comments = commentsDao.getCommentsFromUserName(userToView);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}

		req.setAttribute("user", user);
		req.setAttribute("comments", comments);

		// Just render the JSP.
		req.getRequestDispatcher("/WEB-INF/jsp/UserComments.jsp").forward(req, resp);
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

		String commentIdStr = req.getParameter("deleteCommentId");
		String userWhoPostedComment = req.getParameter("userWhoPostedComment");

		if (!ValidationUtil.isValidString(commentIdStr) || !ValidationUtil.isValidString(userWhoPostedComment)) {
			messages.put("error", "No postId was given.");
			resp.sendRedirect(req.getContextPath() + "/all_users");
			return;
		}

		int commentId = -1;

		try {
			commentId = Integer.parseInt(commentIdStr);
		} catch (NumberFormatException e) {
			messages.put("error", "You provided an invalid comment id");
			resp.sendRedirect(req.getContextPath() + "/user_comments?username=" + userWhoPostedComment);
			return;
		}

		
		try {
			commentsDao.delete(commentId);
			resp.setContentType("text/plain");
			resp.getWriter().write("Successfully deleted user's comment.");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			messages.put("error", "Error deleting the comment");
			resp.sendRedirect(req.getContextPath() + "/user_comments?username=" + userWhoPostedComment);
			return;
		}
	}
}
