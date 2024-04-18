package blog.servlet;

import blog.dal.*;
import blog.model.*;
import blog.util.ValidationUtil;
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
@WebServlet("/create_post")
public class CreatePost extends HttpServlet {
	
	protected PostsDao postsDao;
	
	@Override
	public void init() throws ServletException {
		postsDao = PostsDao.getInstance();
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

		// Check if product id is passed
		String productId = req.getParameter("productId");
		
		if (ValidationUtil.isValidString(productId)) {
			req.setAttribute("productId", productId);
		}
				
		// Just render the JSP.
		req.getRequestDispatcher("/WEB-INF/jsp/CreatePost.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		String username = SessionUtil.getUsername(req, resp);

		if (username == null) {
			return;
		}

		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
        
		String review = req.getParameter("review");
	    String productId = req.getParameter("productId");
	    String ratingStr = req.getParameter("rating");
	    
	    // Perform checks
	    if (isInvalidStringParameter(review) || isInvalidStringParameter(productId) || isInvalidStringParameter(ratingStr)) {
			req.setAttribute("error", "Missing or invalid input. Check values.");
			req.getRequestDispatcher("/WEB-INF/jsp/CreatePost.jsp").forward(req, resp);
			return;
	    }
        
	    double rating = 0.00;
	    
	    try {
	    	rating = Double.parseDouble(req.getParameter("rating"));
	    } catch (NumberFormatException e) {
			e.printStackTrace();
			throw new IOException(e);
	    }
	    
	    Posts post = new Posts(review, rating, username, productId);
	    
	    try {
			post = postsDao.create(post);
			req.setAttribute("success", "Post successfully created! Return home or view post.");

		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("error", "Database error. Could not save post. Try again.");
			req.getRequestDispatcher("/WEB-INF/jsp/CreatePost.jsp").forward(req, resp);
			return;
		}

		req.getRequestDispatcher("/WEB-INF/jsp/CreatePost.jsp").forward(req, resp);
    }
	
	private boolean isInvalidStringParameter(String str) {
		return str.isEmpty() || str == "";
	}
}
