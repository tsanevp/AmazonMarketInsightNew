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

@SuppressWarnings("serial")
@WebServlet("/filter_products")
public class AllProductsFiltered extends HttpServlet {

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

		    String category = req.getParameter("category");
		    String minPrice = req.getParameter("minPrice");
		    String maxPrice = req.getParameter("maxPrice");
		    String rating = req.getParameter("rating");
		    String minReviews = req.getParameter("minReviews");
		    String maxReviews = req.getParameter("maxReviews");
		    String isBestSeller = req.getParameter("bestSeller");
		    String orderBy = req.getParameter("orderBy");
		    String productId = req.getParameter("productId");
		    
		    if (!ValidationUtil.isValidString(category) && !ValidationUtil.isValidString(minPrice) &&
		            !ValidationUtil.isValidString(maxPrice) && !ValidationUtil.isValidString(rating) &&
		            !ValidationUtil.isValidString(minReviews) && !ValidationUtil.isValidString(maxReviews) &&
		            !ValidationUtil.isValidString(isBestSeller) && !ValidationUtil.isValidString(productId) &&
		            (!ValidationUtil.isValidString(orderBy) || orderBy.equals("none"))) {
		        resp.sendRedirect(req.getContextPath() + "/all_products");
		        return;
		    }

		    // Fetch filtered products from the database
		    List<Products> filteredProducts = new ArrayList<>();
			try {
				filteredProducts = productsDao.getFilteredAndOrderedProducts(category, minPrice, maxPrice, rating, minReviews, maxReviews, isBestSeller, productId, orderBy);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    // Pagination logic
		    int page = 1;
		    int pageSize = 20; // Number of products per page

		    if (req.getParameter("page") != null) {
		        page = Integer.parseInt(req.getParameter("page"));
		    }

		    int totalFilteredProducts = filteredProducts.size();
		    int totalPages = (int) Math.ceil((double) totalFilteredProducts / pageSize);


		    // Calculate offset for pagination
		    int offset = (page - 1) * pageSize;
		    int endIndex = Math.min(offset + pageSize, totalFilteredProducts);

		    // Get sublist of filtered products for current page
		    List<Products> products = filteredProducts.subList(offset, endIndex);

		    // Retrieve categories
		    Map<Integer, String> categories = new HashMap<>();
		    try {
		        categories = categoriesDao.getAllCategoriesMap();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        req.setAttribute("error", "There was an error retrieving the categories. Please try again.");
		        resp.sendRedirect(req.getContextPath() + "/home_page");
		        return;
		    }

		    // Set attributes and forward to JSP
		    req.setAttribute("products", products);
		    req.setAttribute("categories", categories);
		    req.setAttribute("page", page);
		    req.setAttribute("totalPages", totalPages);
		    
		    req.setAttribute("category", category);
		    req.setAttribute("minPrice", minPrice);
		    req.setAttribute("maxPrice", maxPrice);
		    req.setAttribute("rating", rating);
		    req.setAttribute("minReviews", minReviews);
		    req.setAttribute("maxReviews", maxReviews);
		  
		    req.setAttribute("orderBy", orderBy);

		    messages.put("success", "Displaying results for filtered products");

		    req.getRequestDispatcher("/WEB-INF/jsp/FilteredProducts.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String username = SessionUtil.getUsername(req, resp);

	    if (username == null) {
	        return;
	    }

	    // Map for storing messages
	    Map<String, String> messages = new HashMap<>();
	    req.setAttribute("messages", messages);

	    String category = req.getParameter("category");
	    String minPrice = req.getParameter("minPrice");
	    String maxPrice = req.getParameter("maxPrice");
	    String rating = req.getParameter("rating");
	    String minReviews = req.getParameter("minReviews");
	    String maxReviews = req.getParameter("maxReviews");
	    String isBestSeller = req.getParameter("bestSeller");
	    String productId = req.getParameter("productId");
	    String orderBy = req.getParameter("orderBy");

	    // Fetch filtered products from the database
	    List<Products> filteredProducts = new ArrayList<>();
		try {
			filteredProducts = productsDao.getFilteredAndOrderedProducts(category, minPrice, maxPrice, rating, minReviews, maxReviews, isBestSeller, productId, orderBy);
		} catch (SQLException e) {
			e.printStackTrace();
	        req.setAttribute("error", "There was an error retrieving the categories. Please try again.");
	        resp.sendRedirect(req.getContextPath() + "/home_page");
	        return;			
		}

	    // Pagination logic
	    int page = 1;
	    int pageSize = 20; // Number of products per page

	    if (req.getParameter("page") != null) {
	        page = Integer.parseInt(req.getParameter("page"));
	    }

	    int totalFilteredProducts = filteredProducts.size();
	    int totalPages = (int) Math.ceil((double) totalFilteredProducts / pageSize);

	    // Calculate offset for pagination
	    int offset = (page - 1) * pageSize;
	    int endIndex = Math.min(offset + pageSize, totalFilteredProducts);

	    // Get sublist of filtered products for current page
	    List<Products> products = filteredProducts.subList(offset, endIndex);

	    // Retrieve categories
	    Map<Integer, String> categories = new HashMap<>();
	    try {
	        categories = categoriesDao.getAllCategoriesMap();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        req.setAttribute("error", "There was an error retrieving the categories. Please try again.");
	        resp.sendRedirect(req.getContextPath() + "/home_page");
	        return;
	    }

	    // Set attributes and forward to JSP
	    req.setAttribute("products", products);
	    req.setAttribute("categories", categories);
	    req.setAttribute("page", page);
	    req.setAttribute("totalPages", totalPages);
	    req.setAttribute("category", category);
	    req.setAttribute("minPrice", minPrice);
	    req.setAttribute("maxPrice", maxPrice);
	    req.setAttribute("rating", rating);
	    req.setAttribute("minReviews", minReviews);
	    req.setAttribute("maxReviews", maxReviews);
	    req.setAttribute("orderBy", orderBy);

	    messages.put("success", "Displaying results for filtered products");

	    req.getRequestDispatcher("/WEB-INF/jsp/FilteredProducts.jsp").forward(req, resp);
	}
}
