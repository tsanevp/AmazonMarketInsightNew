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
@WebServlet("/all_products")
public class AllProducts extends HttpServlet {

	protected ProductsDao productsDao;
	protected CategoriesDao categoriesDao;

	@Override
	public void init() throws ServletException {
		productsDao = ProductsDao.getInstance();
		categoriesDao = CategoriesDao.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = SessionUtil.getUsername(req, resp);

		if (username == null) {
			return;
		}

		// Map for storing messages
		Map<String, String> messages = new HashMap<>();
		req.setAttribute("messages", messages);

		int page = 1;
		int pageSize = 20; // Number of products per page

		if (req.getParameter("page") != null) {
			page = Integer.parseInt(req.getParameter("page"));
		}

		int offset = (page - 1) * pageSize;

		List<Products> products = new ArrayList<>();
		Map<Integer, String> categories = new HashMap<>();

		try {
			products = productsDao.getAllProductsByPage(offset, pageSize);
			categories = categoriesDao.getAllCategoriesMap();
			
			int totalProducts = productsDao.getTotalProductsCount();
			int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
			
			req.setAttribute("products", products);
			req.setAttribute("categories", categories);
			req.setAttribute("page", page);
			req.setAttribute("totalPages", totalPages);
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("error", "There was an error retrieving the products or categories. Please try again.");
			resp.sendRedirect(req.getContextPath() + "/home_page");
			return;
		}

		messages.put("success", "Displaying results for all products");

		req.getRequestDispatcher("/WEB-INF/jsp/AllProducts.jsp").forward(req, resp);
	}

//	@Override
//	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String username = SessionUtil.getUsername(req, resp);
//
//		if (username == null) {
//			return;
//		}
//
//		System.out.println(true);
//
//		// Map for storing messages.
//		Map<String, String> messages = new HashMap<String, String>();
//		req.setAttribute("messages", messages);
//
//		
//		String postIdStr = req.getParameter("postId");
//		if (postIdStr == null || postIdStr.trim().isEmpty()) {
//			messages.put("error", "No postId was given.");
//			resp.sendRedirect(req.getContextPath() + "/all_posts");
//			return;
//		}
//		
//		int postId = -1;
//		
//		try {
//		    postId = Integer.parseInt(postIdStr);
//		} catch (NumberFormatException e) {
//			messages.put("error", "You provided an invalid post id");
//			resp.sendRedirect(req.getContextPath() + "/all_posts");
//			return;
//		}
//		
//		try {
//			postsDao.delete(postId);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			messages.put("error", "Error deleting the post");
//			resp.sendRedirect(req.getContextPath() + "/all_posts");
//			return;
//		}
//		
//		System.out.println(false);
//		messages.put("success", "Deleted postId: " + postId);
//
//		resp.sendRedirect(req.getContextPath() + "/all_posts");
//		}
}
