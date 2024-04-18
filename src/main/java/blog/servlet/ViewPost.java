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

@SuppressWarnings("serial")
@WebServlet("/view_post")
public class ViewPost extends HttpServlet {

	protected PostsDao postsDao;
	protected PostCommentsDao postCommentsDao;
	protected ProductsDao productsDao;

	@Override
	public void init() throws ServletException {
		postsDao = PostsDao.getInstance();
		postCommentsDao = PostCommentsDao.getInstance();
		productsDao = ProductsDao.getInstance();
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

//		String jspPage = req.getParameter("jspPage");
		
		String postIdStr = req.getParameter("postId");
		if (postIdStr == null || postIdStr.trim().isEmpty()) {
			messages.put("error", "No postId was given.");
			req.getRequestDispatcher("/WEB-INF/jsp/UserPosts.jsp").forward(req, resp);

//			req.getRequestDispatcher("/WEB-INF/jsp/" + jspPage).forward(req, resp);
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
		
		Posts post = null;
		List<PostComments> comments = new ArrayList<>();
		List<Products> proudcts = new ArrayList<Products>();

		try {
			post = postsDao.getPostFromPostId(postId);
			comments = postCommentsDao.getCommentsFromPostId(postId);
			proudcts = productsDao.getSimilarProductsToPost(postId);
		} catch (SQLException e) {
			e.printStackTrace();
			messages.put("error", "Error retrieving the post or similar products");
			req.getRequestDispatcher("/WEB-INF/jsp/UserPosts.jsp").forward(req, resp);
//			req.getRequestDispatcher("/WEB-INF/jsp/" + jspPage).forward(req, resp);

			return;
		}
		
		messages.put("success", "Displaying results for " + postId);
		messages.put("postId", postIdStr);

		req.setAttribute("post", post);
		req.setAttribute("comments", comments);
		req.setAttribute("similarProducts", proudcts);

		req.getRequestDispatcher("/WEB-INF/jsp/SinglePost.jsp").forward(req, resp);
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

		String postIdStr = req.getParameter("postId");
		String likeOrDislike = req.getParameter("likeOrDislike");
		
		boolean invalidLikeOrDislike = (likeOrDislike == null || likeOrDislike.trim().isEmpty());
		
		if ((postIdStr == null || postIdStr.trim().isEmpty()) && invalidLikeOrDislike) {
			// error req
			return;
		}
		
		
		int postId = -1;
        
		try {
			postId = Integer.parseInt(postIdStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			// Add error since there was an issue retrieving group id
			return;
		}

		if (postId != -1 && invalidLikeOrDislike) {
			// return to this
			return;
		}
		
		try {
			// After successfully adding or deleting the user, return a response
			postsDao.updateLikeOrDislike(postId, likeOrDislike);
	        resp.setContentType("text/plain");
	        resp.getWriter().write("User successfully joined the group.");
	        return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/jsp/SinglePost.jsp").forward(req, resp);
		}
	}
}
