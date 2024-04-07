package blog.servlet;

import blog.dal.*;
import blog.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet that manages the user login process. If a user successfully logs in, a session is created.
 */
@SuppressWarnings("serial")
@WebServlet("/")
public class Login extends HttpServlet {

	protected UsersDao usersDao;

	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Just render the JSP.
		req.getRequestDispatcher("/WEB-INF/jsp/LoginPage.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve and validate name.
		String username = req.getParameter("username").trim();
		String password = req.getParameter("password").trim();

		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			messages.put("error", "Invalid UserName or Password");
			req.getRequestDispatcher("/WEB-INF/jsp/LoginPage.jsp").forward(req, resp);
			return;
		}

		// Authenticate user, check credientials against database
		boolean isAuthenticated = authenticateUser(username, password, messages);
		
		if (isAuthenticated) {
			// Create a user session
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			session.setMaxInactiveInterval(30 * 60);
			
			// Redirect user to home page
			resp.sendRedirect(req.getContextPath() + "/home_page");
			return;
		}
		
		req.getRequestDispatcher("/WEB-INF/jsp/LoginPage.jsp").forward(req, resp);
	}
	
    /**
     * Authenticates the current user attempting to login.
     *
     * @param username - The user's username.
     * @param password - The user's password.
     * @param messages - A map of messages to return to the jsp page upon error.
     * @return Whether the user was authenticated as a boolean.
     */
	private boolean authenticateUser(String username, String password, Map<String, String> messages) {
		try {
			// Attempt to fetch the user from the database
			Users user = usersDao.getUserFromUserName(username);
			
			// If user doesn't exist, add message, return false
			if (user == null) {
				messages.put("error", "User does not exist. Please create an account");
				return false;
			}
			
			// If passwords do not match, add message, return false
			if (!user.getPassword().equals(password)) {
				messages.put("error", "Incorrect password");
				return false;
			}
			
			// Return true, user exists and passwords match
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
