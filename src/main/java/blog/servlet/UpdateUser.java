package blog.servlet;

import blog.dal.*;
import blog.model.*;
import blog.util.SessionUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 */
@SuppressWarnings("serial")
@WebServlet("/update_user")
public class UpdateUser extends HttpServlet {

	protected UsersDao usersDao;
	protected PersonsDao personsDao;

	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
		personsDao = PersonsDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = SessionUtil.getUsername(req, resp);

		if (username == null) {
			return;
		}

		/*
		 * // Map for storing messages. Map<String, String> messages = new HashMap<>();
		 * req.setAttribute("messages", messages);
		 * 
		 * // Retrieve user and validate. String userName =
		 * req.getParameter("username"); if (userName == null ||
		 * userName.trim().isEmpty()) { messages.put("success",
		 * "Please enter a valid UserName."); } else { try { Users blogUser =
		 * usersDao.getUserFromUserName(userName); if (blogUser == null) {
		 * messages.put("success", "UserName does not exist."); }
		 * req.setAttribute("blogUser", blogUser); } catch (SQLException e) {
		 * e.printStackTrace(); throw new IOException(e); } }
		 * 
		 * req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
		 */
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = SessionUtil.getUsername(req, resp);

		if (username == null) {
			return;
		}

		// Map for storing messages.
		Map<String, String> messages = new HashMap<>();
		req.setAttribute("messages", messages);

		Users user = null;

		try {
			user = usersDao.getUserFromUserName(username);
		} catch (SQLException e) {
			e.printStackTrace();
			messages.put("error", "UserName does not exist. No update to perform.");
			req.setAttribute("user", user);
			req.getRequestDispatcher("/WEB-INF/jsp/UserProfile.jsp").forward(req, resp);
			return;
		}

		// Retrieve and set updated values.
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String phoneNumber = req.getParameter("phoneNumber");

		// Parse date of birth.
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String stringDob = req.getParameter("dob");
		Date dob = new Date();

		if (stringDob == null || stringDob.isEmpty()) {
			dob = user.getDob();
		} else {
			try {
				dob = dateFormat.parse(stringDob);
			} catch (ParseException e) {
				e.printStackTrace();
				messages.put("error", "Invalid DoB provided.");
				req.setAttribute("user", user);
				req.getRequestDispatcher("/WEB-INF/jsp/UserProfile.jsp").forward(req, resp);
				return;
			}
		}

		// Verify the user actually updated a value
		if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)
				&& user.getEmail().equals(email) && user.getPhoneNumber().equals(phoneNumber)
				&& user.getDob().equals(dob)) {
			messages.put("error", "No user information was updated. Update not processed.");
			req.setAttribute("user", user);
			req.getRequestDispatcher("/WEB-INF/jsp/UserProfile.jsp").forward(req, resp);
			return;
		}

		// Retrieve and set updated values.
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setDob(dob);

		// Verify updates completed successfully
		if (!daoUpdateUser(user, messages, false, req, resp))
			return;
		if (!daoUpdateUser(user, messages, true, req, resp))
			return;

		messages.put("success", "Successfully updated " + username);
		req.setAttribute("user", user);
		req.getRequestDispatcher("/WEB-INF/jsp/UserProfile.jsp").forward(req, resp);
	}

	/**
	 * Extracted error handling logic which verifies the user was successfully
	 * updated in DB.
	 *
	 * @param user           - The user to update.
	 * @param messages       - The map for storing messages that are sent back to
	 *                       the jsp pages.
	 * @param isPersonUpdate - Calls personDao if true, else calls userDao update
	 *                       method.
	 * @param req            - The servlet request.
	 * @param resp           - The response to send back.
	 * @return Whether the user successfully updated, as a boolean.
	 * @throws SQLException
	 */
	private boolean daoUpdateUser(Users user, Map<String, String> messages, boolean isPersonUpdate,
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			if (isPersonUpdate) {
				user = personsDao.updatePerson(user);
			} else {
				user = usersDao.updateUser(user);
			}

			if (user == null) {
				messages.put("error", "An error occurred while updating your info. Update not processed.");
				req.setAttribute("user", user);
				req.getRequestDispatcher("/WEB-INF/jsp/UserProfile.jsp").forward(req, resp);
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			messages.put("error", "An error occurred while updating your info. Update not processed.");
			req.setAttribute("user", user);
			req.getRequestDispatcher("/WEB-INF/jsp/UserProfile.jsp").forward(req, resp);
			return false;
		}

		return true;
	}
}
