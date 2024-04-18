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
@WebServlet("/all_posts")
public class AllPosts extends HttpServlet {

	protected PostsDao postsDao;

	@Override
	public void init() throws ServletException {
		postsDao = PostsDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = SessionUtil.getUsername(req, resp);

		if (username == null) {
			return;
		}
		
		// Map for storing messages
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		List<Posts> posts = new ArrayList<Posts>();
		String productId = req.getParameter("productId");
		
		try {
			if (ValidationUtil.isValidString(productId)) {
				posts = postsDao.getPostsFromProductId(productId);
				req.setAttribute("title", "Posts For Product " + productId);

			} else {
				posts = postsDao.getAllPosts();
				req.setAttribute("title", "All Posts");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		messages.put("success", "Displaying results for all posts");
		// Save the previous search term, so it can be used as the default

		req.setAttribute("posts", posts);
		req.setAttribute("username", username);

		req.getRequestDispatcher("/WEB-INF/jsp/AllPosts.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = SessionUtil.getUsername(req, resp);

		if (username == null) {
			return;
		}

		System.out.println(true);

		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		
		String postIdStr = req.getParameter("postId");
		if (postIdStr == null || postIdStr.trim().isEmpty()) {
			messages.put("error", "No postId was given.");
			resp.sendRedirect(req.getContextPath() + "/all_posts");
			return;
		}
		
		int postId = -1;
		
		try {
		    postId = Integer.parseInt(postIdStr);
		} catch (NumberFormatException e) {
			messages.put("error", "You provided an invalid post id");
			resp.sendRedirect(req.getContextPath() + "/all_posts");
			return;
		}
		
		try {
			postsDao.delete(postId);
		} catch (SQLException e) {
			e.printStackTrace();
			messages.put("error", "Error deleting the post");
			resp.sendRedirect(req.getContextPath() + "/all_posts");
			return;
		}
		
		System.out.println(false);
		messages.put("success", "Deleted postId: " + postId);

		resp.sendRedirect(req.getContextPath() + "/all_posts");
		}
}
