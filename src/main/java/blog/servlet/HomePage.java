package blog.servlet;

import blog.dal.*;
import blog.model.Products;
import blog.util.SessionUtil;
import blog.util.ValidationUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet that displays the home page if a user is logged in.
 */
@SuppressWarnings("serial")
@WebServlet("/home_page")
public class HomePage extends HttpServlet {

	protected UsersDao usersDao;
	protected PostsDao postsDao;
	protected ProductsDao productsDao;

	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
		postsDao = PostsDao.getInstance();
		productsDao = ProductsDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = SessionUtil.getUsername(req, resp);

		if (username == null) {
			return;
		}

		// Map for storing messages.
		Map<String, Integer> messages = new HashMap<>();
		req.setAttribute("messages", messages);

		Map<String, Integer> mostPostedProducts = new HashMap<>();
//		List<Products> prodcuts = new ArrayList<>();

		try {
			mostPostedProducts = postsDao.getMostPostedProducts();
			req.setAttribute("mostPostedProducts", mostPostedProducts);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Just render the JSP.
		req.getRequestDispatcher("/WEB-INF/jsp/HomePage.jsp").forward(req, resp);
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

		String option = req.getParameter("option");

		if (!ValidationUtil.isValidString(option)) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.getWriter().write("Invalid option parameter");
			return;
		}

		Map<String, Integer> optionSelected = new HashMap<>();
		Map<String, Object> responseData = new HashMap<>();
		String htmlResponse;
		String indicatorsHTML;


		try {
			if (option.equals("top_users")) {
				optionSelected = postsDao.getMostActiveUser();
				htmlResponse = generateCarouselHTML(optionSelected, "user_posts?username=");
		        indicatorsHTML = generateCarouselIndicators(optionSelected.size());
		        responseData.put("newSectionTitle", "Top Users");
			} else if (option.equals("best_products")) {
				List<Products> bestProducts = productsDao.getBestProducts();
				htmlResponse = generateCarouselHTML(bestProducts);
		        indicatorsHTML = generateCarouselIndicators(bestProducts.size());
		        responseData.put("newSectionTitle", "Best Products");
			} else {
				optionSelected = postsDao.getMostPostedProducts();
				htmlResponse = generateCarouselHTML(optionSelected, "all_posts?productId=");
		        indicatorsHTML = generateCarouselIndicators(optionSelected.size());
		        responseData.put("newSectionTitle", "Most Posted Products");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.getWriter().write("Invalid option parameter");
			return;
		}
		

		responseData.put("carouselHTML", htmlResponse);
        responseData.put("carouselIndicatorsHTML", indicatorsHTML);

    	resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("application/json");
		resp.getWriter().write(new Gson().toJson(responseData));
		return;
	}

	private String generateCarouselHTML(Map<String, Integer> products, String option) {
		StringBuilder htmlBuilder = new StringBuilder();

		// Start building the carousel inner HTML
		htmlBuilder.append("<div class=\"carousel-inner\">");

		// Variable to keep track of the index
		int index = 0;

		// Iterate through the products map
		for (Map.Entry<String, Integer> entry : products.entrySet()) {
			String productName = entry.getKey();
			int numberOfPosts = entry.getValue();

			// Check if the index is divisible by 3 or it's the last item
			if (index % 3 == 0 || index == 0) {
				// If it's the first item or divisible by 3, add carousel item div
				htmlBuilder.append("<div class=\"carousel-item");
				// Add active class if it's the first item
				if (index == 0) {
					htmlBuilder.append(" active");
				}
				htmlBuilder.append("\">");
				htmlBuilder.append("<div class=\"row\">");
			}

			// Add column and card for each product
			htmlBuilder.append("<div class=\"col\">");
			htmlBuilder.append("<div class=\"card h-100\">");
			htmlBuilder.append("<div class=\"card-body\">");
			htmlBuilder.append("<h5 class=\"card-title\">").append(productName).append("</h5>");
			htmlBuilder.append("<p class=\"card-text\">Posts: ").append(numberOfPosts).append("</p>");
			htmlBuilder.append("<a href=\"" + option + productName + "\" class=\"btn btn-primary\">View Details</a>");
			htmlBuilder.append("</div></div></div>");

			// Check if it's the last item in the row
			if ((index + 1) % 3 == 0 || index == products.size() - 1) {
				htmlBuilder.append("</div>"); // Close row div
				htmlBuilder.append("</div>"); // Close carousel item div
			}

			// Increment index
			index++;
		}

		// End building the carousel inner HTML
		htmlBuilder.append("</div>");

		return htmlBuilder.toString();
	}
	
	private String generateCarouselHTML(List<Products> products) {
	    StringBuilder htmlBuilder = new StringBuilder();

	    // Start building the carousel inner HTML
	    htmlBuilder.append("<div class=\"carousel-inner\">");

	    // Variable to keep track of the index
	    int index = 0;

	    // Iterate through the products list
	    for (Products product : products) {
	        String productName = product.getProductId();
	        double stars = product.getStars();
	        double price = product.getPrice();
	        boolean bestSeller = product.isBestSeller();

	        // Check if the index is divisible by 3 or it's the last item
	        if (index % 3 == 0 || index == 0) {
	            // If it's the first item or divisible by 3, add carousel item div
	            htmlBuilder.append("<div class=\"carousel-item");
	            // Add active class if it's the first item
	            if (index == 0) {
	                htmlBuilder.append(" active");
	            }
	            htmlBuilder.append("\">");
	            htmlBuilder.append("<div class=\"row\">");
	        }

	        // Add column and card for each product
	        htmlBuilder.append("<div class=\"col\">");
	        htmlBuilder.append("<div class=\"card h-100\">");
	        htmlBuilder.append("<div class=\"card-body\">");
	        htmlBuilder.append("<h5 class=\"card-title\">").append(productName).append("</h5>");
	        htmlBuilder.append("<p class=\"card-text\">Stars: ").append(stars).append("</p>");
	        htmlBuilder.append("<p class=\"card-text\">Price: $").append(price).append("</p>");
	        htmlBuilder.append("<p class=\"card-text\">Best Seller: ").append(bestSeller).append("</p>");
	        htmlBuilder.append("<a href=\"all_posts?productId=" + productName + "\" class=\"btn btn-primary\">View Details</a>");
	        htmlBuilder.append("</div></div></div>");

	        // Check if it's the last item in the row
	        if ((index + 1) % 3 == 0 || index == products.size() - 1) {
	            htmlBuilder.append("</div>"); // Close row div
	            htmlBuilder.append("</div>"); // Close carousel item div
	        }

	        // Increment index
	        index++;
	    }

	    // End building the carousel inner HTML
	    htmlBuilder.append("</div>");

	    return htmlBuilder.toString();
	}


	private String generateCarouselIndicators(int totalProducts) {
	    StringBuilder indicatorsBuilder = new StringBuilder();

	    // Calculate the number of pages (each page displays 3 items)
	    int numPages = (int) Math.ceil((double) totalProducts / 3);

	    // Start building the carousel indicators HTML
	    indicatorsBuilder.append("<ol class=\"carousel-indicators\">");

	    // Iterate through the number of pages and generate indicators
	    for (int i = 0; i < numPages; i++) {
	        // Add the active class to the first indicator
	        String activeClass = (i == 0) ? "active" : "";

	        indicatorsBuilder.append("<li data-bs-target=\"#myCarousel\" data-bs-slide-to=\"")
	                        .append(i)
	                        .append("\" class=\"")
	                        .append(activeClass)
	                        .append("\"></li>");
	    }

	    // End building the carousel indicators HTML
	    indicatorsBuilder.append("</ol>");

	    return indicatorsBuilder.toString();
	}
}
