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


@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println(userName);
        System.out.println(password);

        if (userName == null || userName.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	// Get the User.

	        try {
	        	Users user = usersDao.getUserFromUserName(userName);
	        	
	        	if (user.getPassword().equals(password)) {
	                req.getRequestDispatcher("/LandingPage.jsp").forward(req, resp);
	        	}
	        	messages.put("success", "Successfully created ");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
    	req.getRequestDispatcher("/SignOnPage.jsp").forward(req, resp);
    }
}
