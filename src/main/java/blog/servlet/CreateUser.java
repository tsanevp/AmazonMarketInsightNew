package blog.servlet;

import blog.dal.*;
import blog.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/create_user")
public class CreateUser extends HttpServlet {

	protected UsersDao usersDao;

	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<>();
		req.setAttribute("messages", messages);
		// Just render the JSP.
		req.getRequestDispatcher("/WEB-INF/jsp/CreateUser.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<>();
		req.setAttribute("messages", messages);

		// Retrieve and validate user data.
		String userName = req.getParameter("username");
		if (userName == null || userName.trim().isEmpty()) {
			messages.put("success", "Invalid UserName");
		} else {
			String password = req.getParameter("password");
			String firstName = req.getParameter("firstname");
			String lastName = req.getParameter("lastname");
			String email = req.getParameter("email");
			String phoneNumber = req.getParameter("phonenumber");

			// Parse date of birth.
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String stringDob = req.getParameter("dob");
			Date dob = new Date();
			try {
				dob = dateFormat.parse(stringDob);
			} catch (ParseException e) {
				e.printStackTrace();
				throw new IOException(e);
			}

			try {
				// Create user object and save to database.
				Users blogUser = new Users(userName, password, firstName, lastName, email, phoneNumber, dob);
				blogUser = usersDao.create(blogUser);
				messages.put("success", "Successfully created " + userName);
			} catch (SQLException e) {
				e.printStackTrace();
				messages.put("success", "Error creating " + userName);
				req.getRequestDispatcher("/WEB-INF/jsp/CreateUser.jsp").forward(req, resp);
				return;
			}
		}

		// Forward to JSP for rendering.
		req.getRequestDispatcher("/WEB-INF/jsp/CreateUser.jsp").forward(req, resp);
	}
}