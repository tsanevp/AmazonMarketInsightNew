package blog.servlet;

import blog.dal.*;
import blog.model.*;
import blog.util.SessionUtil;

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

/**
 * FindUsers is the primary entry point into the application.
 * 
 * Note the logic for doGet() and doPost() are almost identical. However, there
 * is a difference: doGet() handles the http GET request. This method is called
 * when you put in the /findusers URL in the browser. doPost() handles the http
 * POST request. This method is called after you click the submit button.
 * 
 * To run: 1. Run the SQL script to recreate your database schema:
 * http://goo.gl/86a11H. 2. Insert test data. You can do this by running
 * blog.tools.Inserter (right click, Run As > JavaApplication. Notice that this
 * is similar to Runner.java in our JDBC example. 3. Run the Tomcat server at
 * localhost. 4. Point your browser to
 * http://localhost:8080/BlogApplication/findusers.
 */
@SuppressWarnings("serial")
@WebServlet("/manage_subscription")
public class UserSubscription extends HttpServlet {

	protected UsersDao usersDao;
	protected CreditCardsDao creditCardsDao;

	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
		creditCardsDao = CreditCardsDao.getInstance();
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

		Users user = null;

		try {
			user = usersDao.getUserFromUserName(username);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		
		List<CreditCards> creditCards = new ArrayList<>();
		
		try {
			creditCards = creditCardsDao.getCreditCardsByUserName(username);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}

		req.setAttribute("creditCards", creditCards);
		
		String subscriptionStatus = req.getParameter("subscriptionStatus");
		if (subscriptionStatus != null && subscriptionStatus.equals("false")) {
			System.out.println("DFSKLF");
			try {
				user = usersDao.updateSubsciption(user, false);
			} catch (SQLException e) {
				e.printStackTrace();
				messages.put("error", "There was an issue updating your subscription");
				req.setAttribute("user", user);
				req.getRequestDispatcher("/WEB-INF/jsp/ManageSubscription.jsp").forward(req, resp);
				return;
			}
		}
		
		req.setAttribute("user", user);
		req.getRequestDispatcher("/WEB-INF/jsp/ManageSubscription.jsp").forward(req, resp);
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

		Users user = null;

		try {
			user = usersDao.getUserFromUserName(username);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		
		req.setAttribute("user", user);
		
		String cardNumber = req.getParameter("cardNumber");
		String expiration = req.getParameter("expiration");
		String existingCard = req.getParameter("existingCards");
		
		if (existingCard != null) {
			try {
				user = usersDao.updateSubsciption(user, true);
				messages.put("success", "You have been subscribed");
				req.getRequestDispatcher("/WEB-INF/jsp/ManageSubscription.jsp").forward(req, resp);
				return;
			} catch (SQLException e) {
				e.printStackTrace();
				messages.put("error", "There was an issue updating your subscription");
				req.getRequestDispatcher("/WEB-INF/jsp/ManageSubscription.jsp").forward(req, resp);
				return;
			}
		}
		
		if (cardNumber == null || expiration == null) {
			messages.put("error", "You must provide all new card details for your payment");
			req.getRequestDispatcher("/WEB-INF/jsp/ManageSubscription.jsp").forward(req, resp);
			return;
		} 
		
		try {
			user = usersDao.updateSubsciption(user, true);
			messages.put("success", "You have been subscribed");
			req.getRequestDispatcher("/WEB-INF/jsp/ManageSubscription.jsp").forward(req, resp);
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			messages.put("error", "There was an issue updating your subscription");
			req.getRequestDispatcher("/WEB-INF/jsp/ManageSubscription.jsp").forward(req, resp);
			return;
		}
	}
	
}
