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
import java.util.stream.Collectors;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/all_users")
public class AllUsers extends HttpServlet {

	protected UsersDao usersDao;

	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
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

		List<Users> users = new ArrayList<Users>();

		try {
			users = usersDao.getAllUsers().stream()
	        .filter(user -> !user.getUserName().toLowerCase().trim().equals(username.toLowerCase().trim()))
	        .collect(Collectors.toList());
		} catch (SQLException e) {
			e.printStackTrace();
			String errorMessage = "There was an error retrieving all users. Try again.";
			resp.sendRedirect(req.getContextPath() + "/home_page?error=" + errorMessage);
			return;
		}
		
		messages.put("success", "Displaying results for all users");

		req.setAttribute("users", users);

		req.getRequestDispatcher("/WEB-INF/jsp/AllUsers.jsp").forward(req, resp);
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

		String action = req.getParameter("action");
		
		if (ValidationUtil.isValidString(action) && action.equals("delete")) {
			String userToDelete = req.getParameter("username");
			
			if (!ValidationUtil.isValidString(userToDelete)) {
				messages.put("error", "You provided an invalid user id");
				resp.sendRedirect(req.getContextPath() + "/AllUsers.jsp");
				return;
			}
			
			try {
				usersDao.delete(userToDelete);
				resp.setContentType("text/plain");
				resp.getWriter().write("Successfully deleted user");
				return;
			} catch (SQLException e) {
				e.printStackTrace();
				messages.put("error", "Error deleting the user");
				resp.sendRedirect(req.getContextPath() + "/AllUsers.jsp");
				return;
			}
		}
		
		resp.getWriter().write("Something is wrong");
	}
}
