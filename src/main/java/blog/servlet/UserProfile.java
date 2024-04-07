package blog.servlet;

import blog.dal.*;
import blog.model.*;
import blog.util.SessionUtil;

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
@WebServlet("/my_profile")
public class UserProfile extends HttpServlet {
	
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = SessionUtil.getUsername(req, resp);
		
		if (username == null) {
			return;
		}
		
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		Users user = null;
		
		try {
			user = usersDao.getUserFromUserName(username);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
		
		req.setAttribute("user", user);
		
		// Just render the JSP.
		req.getRequestDispatcher("/WEB-INF/jsp/UserProfile.jsp").forward(req, resp);
	}
}
