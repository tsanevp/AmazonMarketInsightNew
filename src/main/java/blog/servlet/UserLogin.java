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

@SuppressWarnings("serial")
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {

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
		req.getRequestDispatcher("/SignOnPage.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve and validate name.
		String userName = req.getParameter("username");
		String password = req.getParameter("password");

		System.out.println(userName);
		System.out.println(password);

		if (userName == null || userName.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			messages.put("error", "Invalid UserName or Password");
			req.getRequestDispatcher("/SignOnPage.jsp").forward(req, resp);
			return;
		}

		try {
			// Attempt to fetch the user from the database
			Users user = usersDao.getUserFromUserName(userName);

			// If user doesn't exist, redirect to sign-on page
			if (user == null) {
				messages.put("error", "User does not exist. Please create an account");
				req.getRequestDispatcher("/SignOnPage.jsp").forward(req, resp);
				return;
			}

			// Check if password matches
			if (user.getPassword().equals(password)) {
				// Redirect to landing page after successful login
				resp.sendRedirect(req.getContextPath() + "/LandingPage.jsp");
				return;
			} else {
				messages.put("error", "Incorrect password");
				req.getRequestDispatcher("/SignOnPage.jsp").forward(req, resp);
				return;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			messages.put("error", "An error occurred. Please try again later");
			req.getRequestDispatcher("/SignOnPage.jsp").forward(req, resp);
			return;
		}
	}
}
