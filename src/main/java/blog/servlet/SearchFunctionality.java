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
@WebServlet("/search")
public class SearchFunctionality extends HttpServlet {

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

		String query = req.getParameter("query");
		if (!ValidationUtil.isValidString(query)) {
			req.setAttribute("error", "There was an error in your search parameters. Please try again.");
			System.out.println("DFSDFS");

			resp.sendRedirect(req.getContextPath() + "/home_page");
			return;
		}

		// Perform search logic based on the query
		List<Products> searchResults = searchProducts(query);

		// Pagination logic
		int page = 1;
		int pageSize = 20; // Number of products per page

		if (req.getParameter("page") != null) {
			page = Integer.parseInt(req.getParameter("page"));
		}

		int totalSearchedProducts = searchResults.size();
		int totalPages = (int) Math.ceil((double) totalSearchedProducts / pageSize);

		// Calculate offset for pagination
		int offset = (page - 1) * pageSize;
		int endIndex = Math.min(offset + pageSize, totalSearchedProducts);

		// Get sublist of filtered products for current page
		List<Products> products = searchResults.subList(offset, endIndex);

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

		// Set the search results in the request attributes
		req.setAttribute("query", query);
		req.setAttribute("totalCount", totalSearchedProducts);
		req.setAttribute("searchResults", searchResults);
		req.setAttribute("products", products);
		req.setAttribute("categories", categories);
		req.setAttribute("page", page);
		req.setAttribute("totalPages", totalPages);

		messages.put("success", "Displaying results for searched products");

		req.getRequestDispatcher("/WEB-INF/jsp/SearchedProducts.jsp").forward(req, resp);
	}

//	// Method to perform search based on the query
//	private List<Object> performSearch(String query) {
//		List<Object> searchResults = new ArrayList<>();
//
//		// Perform product search
//		List<Products> productResults = searchProducts(query);
//		searchResults.addAll(productResults);
//
//		// Perform search for other types of content (e.g., posts, groups, etc.)
//		// Add logic here to search for other types of content
//
//		return searchResults;
//	}

	// Method to search for products
	private List<Products> searchProducts(String query) {
		List<Products> products = new ArrayList<>();

		try {
			products = productsDao.getProductsByTitle(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}
}
