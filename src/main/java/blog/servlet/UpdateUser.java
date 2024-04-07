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
@WebServlet("/update_user")
public class UpdateUser extends HttpServlet {
	
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

        // Retrieve user and validate.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
        	try {
        		Users blogUser = usersDao.getUserFromUserName(userName);
        		if(blogUser == null) {
        			messages.put("success", "UserName does not exist.");
        		}
        		req.setAttribute("blogUser", blogUser);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
        	try {
        		Users user = usersDao.getUserFromUserName(userName);
        		if(user == null) {
        			messages.put("success", "UserName does not exist. No update to perform.");
        		} else {
                	String subscribedValue = req.getParameter("subscribed");
                	boolean isSubscribed = Boolean.parseBoolean(subscribedValue);
                	user = usersDao.updateSubsciption(user, isSubscribed);
    	        	messages.put("success", "Successfully updated " + userName);
    	        	messages.put("home", "LandingPage.jsp");

        		}
        		req.setAttribute("user", user);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
    }
}
