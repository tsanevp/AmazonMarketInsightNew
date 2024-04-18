package blog.dal;

import blog.model.*;
import blog.util.ValidationUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsDao {
	protected ConnectionManager connectionManager;

	private static ProductsDao instance = null;

	protected ProductsDao() {
		connectionManager = new ConnectionManager();
	}

	public static ProductsDao getInstance() {
		if (instance == null) {
			instance = new ProductsDao();
		}
		return instance;
	}

	public Products create(Products product) throws SQLException {
		String insertProduct = "INSERT INTO Products(ProductId, Title, ImageUrl, ProductUrl, Stars, Reviews, "
				+ "Price, ListedPrice, CategoryId, BestSeller, BoughtInLastMonth, TimesPosted, NumViews, "
				+ "Likes, Dislikes) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertProduct);
			insertStmt.setString(1, product.getProductId());
			insertStmt.setString(2, product.getTitle());
			insertStmt.setString(3, product.getImageUrl());
			insertStmt.setString(4, product.getProductUrl());
			insertStmt.setDouble(5, product.getStars());
			insertStmt.setInt(6, product.getReviews());
			insertStmt.setDouble(7, product.getPrice());
			insertStmt.setDouble(8, product.getListedPrice());
			if (product.getCategoryId() != null) {
				insertStmt.setInt(9, product.getCategoryId());
			} else {
				insertStmt.setNull(9, java.sql.Types.INTEGER);
			}
			insertStmt.setBoolean(10, product.isBestSeller());
			insertStmt.setInt(11, product.getBoughtInLastMonth());
			insertStmt.setInt(12, product.getTimesPosted());
			insertStmt.setInt(13, product.getNumViews());
			insertStmt.setInt(14, product.getLikes());
			insertStmt.setInt(15, product.getDislikes());
			insertStmt.executeUpdate();
			return product;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	public Products update(Products product) throws SQLException {
		String updateProduct = "UPDATE Products SET Title=?, ImageUrl=?, ProductUrl=?, Stars=?, Reviews=?, "
				+ "Price=?, ListedPrice=?, CategoryId=?, BestSeller=?, BoughtInLastMonth=?, TimesPosted=?, NumViews=?, "
				+ "Likes=?, Dislikes=? WHERE ProductId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateProduct);
			updateStmt.setString(1, product.getTitle());
			updateStmt.setString(2, product.getImageUrl());
			updateStmt.setString(3, product.getProductUrl());
			updateStmt.setDouble(4, product.getStars());
			updateStmt.setInt(5, product.getReviews());
			updateStmt.setDouble(6, product.getPrice());
			updateStmt.setDouble(7, product.getListedPrice());
			if (product.getCategoryId() != null) {
				updateStmt.setInt(8, product.getCategoryId());
			} else {
				updateStmt.setNull(8, java.sql.Types.INTEGER);
			}
			updateStmt.setBoolean(9, product.isBestSeller());
			updateStmt.setInt(10, product.getBoughtInLastMonth());
			updateStmt.setInt(11, product.getTimesPosted());
			updateStmt.setInt(12, product.getNumViews());
			updateStmt.setInt(13, product.getLikes());
			updateStmt.setInt(14, product.getDislikes());
			updateStmt.setString(15, product.getProductId());
			updateStmt.executeUpdate();
			return product;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	public boolean delete(String productId) throws SQLException {
		String deleteProduct = "DELETE FROM Products WHERE ProductId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteProduct);
			deleteStmt.setString(1, productId);
			int affectedRows = deleteStmt.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	public Products getProductById(String productId) throws SQLException {
		String selectProduct = "SELECT * FROM Products WHERE ProductId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet resultSet = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProduct);
			selectStmt.setString(1, productId);
			resultSet = selectStmt.executeQuery();
			if (resultSet.next()) {
				return parseProduct(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		}
		return null;
	}

	public List<Products> getAllProducts() throws SQLException {
		List<Products> productList = new ArrayList<>();
		String selectAllProducts = "SELECT * FROM Products;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet resultSet = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAllProducts);
			resultSet = selectStmt.executeQuery();
			while (resultSet.next()) {
				Products product = parseProduct(resultSet);
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		}
		return productList;
	}

	public List<Products> getAllProductsByPage(int offset, int pageSize) throws SQLException {
		List<Products> productList = new ArrayList<>();
		String selectAllProducts = "SELECT * FROM Products LIMIT ?, ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet resultSet = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAllProducts);
			selectStmt.setInt(1, offset);
			selectStmt.setInt(2, pageSize);
			resultSet = selectStmt.executeQuery();
			while (resultSet.next()) {
				Products product = parseProduct(resultSet);
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		}
		return productList;
	}

	public int getTotalProductsCount() throws SQLException {
		String countQuery = "SELECT COUNT(*) AS total FROM Products;";
		Connection connection = null;
		PreparedStatement countStmt = null;
		ResultSet resultSet = null;
		try {
			connection = connectionManager.getConnection();
			countStmt = connection.prepareStatement(countQuery);
			resultSet = countStmt.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (countStmt != null) {
				countStmt.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		}
		return 0; // return 0 if no products found or any error occurs
	}

	public List<Products> getSimilarProductsToPost(int postId) throws SQLException {
		List<Products> products = new ArrayList<Products>();

		String selectProducts = "SELECT * FROM Products INNER JOIN \r\n"
				+ "(SELECT PostId,Created,Review,Rating,NumInteractions,Active,UpVotes,DownVotes,Shares,UserName,Posts.ProductId,CategoryId FROM Posts INNER JOIN Products ON Posts.ProductId = Products.ProductId WHERE PostId = ?) AS CurrentPostInfo\r\n"
				+ "ON Products.CategoryId = CurrentPostInfo.CategoryId\r\n"
				+ "ORDER BY BoughtInLastMonth DESC, Stars DESC, Price, BestSeller DESC\r\n" + "Limit 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProducts);
			selectStmt.setInt(1, postId);

			results = selectStmt.executeQuery();
			while (results.next()) {
				products.add(parseProduct(results));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return products;
	}

	// Method to retrieve products by title containing the specified string
	public List<Products> getProductsByTitle(String searchString) throws SQLException {
		List<Products> products = new ArrayList<>();

		String selectProducts = "SELECT * FROM products WHERE title LIKE ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProducts);

			// Set the search parameter with wildcard '%' to match any substring
			selectStmt.setString(1, "%" + searchString + "%");

			results = selectStmt.executeQuery();
			while (results.next()) {
				products.add(parseProduct(results));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}

		return products;
	}

	public List<Products> getBestProducts() throws SQLException {
		List<Products> bestProducts = new ArrayList<>();

		String selectPost = "SELECT ProductId, Stars, BestSeller, Price FROM Products WHERE Stars>='4.5' AND BestSeller='1' AND Price BETWEEN '25.00' AND '100.00' ORDER BY Price ASC, Stars DESC LIMIT 9;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPost);

			results = selectStmt.executeQuery();
			while (results.next()) {
				String productId = results.getString("ProductId");
				double stars = results.getDouble("Stars");
				double price = results.getDouble("Price");
				boolean bestSeller = results.getBoolean("BestSeller");

				bestProducts.add(new Products(productId, stars, price, bestSeller));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return bestProducts;
	}

	public List<Products> getFilteredAndOrderedProducts(String category, String minPrice, String maxPrice,
			String rating, String minReviews, String maxReviews, String isBestSeller, String productId, String orderBy)
			throws SQLException {
		List<Products> productList = new ArrayList<>();
		StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Products WHERE 1=1");

		// Add filters based on the provided criteria
		if (ValidationUtil.isValidString(category)) {
			queryBuilder.append(" AND CategoryId = ?");
		}

		// Add price filter
		if (ValidationUtil.isValidString(minPrice) && ValidationUtil.isValidString(maxPrice)) {
			queryBuilder.append(" AND Price BETWEEN ? AND ?");
		} else if (ValidationUtil.isValidString(minPrice)) {
			queryBuilder.append(" AND Price >= ?");
		} else if (ValidationUtil.isValidString(maxPrice)) {
			queryBuilder.append(" AND Price <= ?");
		}

		// Add rating filter
		if (ValidationUtil.isValidString(rating)) {
			queryBuilder.append(" AND Stars >= ?");
		}

		// Add reviews filter
		if (ValidationUtil.isValidString(minReviews) && ValidationUtil.isValidString(maxReviews)) {
			queryBuilder.append(" AND Reviews BETWEEN ? AND ?");
		} else if (ValidationUtil.isValidString(minReviews)) {
			queryBuilder.append(" AND Reviews >= ?");
		} else if (ValidationUtil.isValidString(maxReviews)) {
			queryBuilder.append(" AND Reviews <= ?");
		}

		// Add reviews filter
		if (ValidationUtil.isValidString(isBestSeller)) {
			if (isBestSeller.equals("true")) {
				queryBuilder.append(" AND BestSeller = '1'");
			} else {
				queryBuilder.append(" AND BestSeller = '0'");
			}
		}

		// Add rating filter
		if (ValidationUtil.isValidString(productId)) {
			queryBuilder.append(" AND ProductId = ?");
		}

		// Append ordering condition
		if (orderBy != null && !orderBy.isEmpty()) {
			if (orderBy.equals("asc")) {
				queryBuilder.append(" ORDER BY Price ASC"); // Order by price ascending
			} else if (orderBy.equals("desc")) {
				queryBuilder.append(" ORDER BY Price DESC"); // Order by price descending
			}
		}

		String query = queryBuilder.toString();
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet resultSet = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(query);

			int parameterIndex = 1;
			if (ValidationUtil.isValidString(category)) {
				selectStmt.setString(parameterIndex++, category);
			}
			if (ValidationUtil.isValidString(minPrice)) {
				selectStmt.setDouble(parameterIndex++, Double.parseDouble(minPrice));
			}
			if (ValidationUtil.isValidString(maxPrice)) {
				selectStmt.setDouble(parameterIndex++, Double.parseDouble(maxPrice));
			}
			if (ValidationUtil.isValidString(rating)) {
				selectStmt.setInt(parameterIndex++, Integer.parseInt(rating));
			}
			if (ValidationUtil.isValidString(minReviews)) {
				selectStmt.setInt(parameterIndex++, Integer.parseInt(minReviews));
			}
			if (ValidationUtil.isValidString(maxReviews)) {
				selectStmt.setInt(parameterIndex++, Integer.parseInt(maxReviews));
			}

			if (ValidationUtil.isValidString(productId)) {
				selectStmt.setString(parameterIndex++, productId);
			}

			resultSet = selectStmt.executeQuery();
			while (resultSet.next()) {
				Products product = parseProduct(resultSet);
				productList.add(product);
			}
		} catch (SQLException | NumberFormatException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (selectStmt != null) {
					selectStmt.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return productList;
	}

	private Products parseProduct(ResultSet resultSet) throws SQLException {
		String productId = resultSet.getString("ProductId");
		String title = resultSet.getString("Title");
		String imageUrl = resultSet.getString("ImageUrl");
		String productUrl = resultSet.getString("ProductUrl");
		double stars = resultSet.getDouble("Stars");
		int reviews = resultSet.getInt("Reviews");
		double price = resultSet.getDouble("Price");
		double listedPrice = resultSet.getDouble("ListedPrice");
		Integer categoryId = resultSet.getInt("CategoryId");
		boolean bestSeller = resultSet.getBoolean("BestSeller");
		int boughtInLastMonth = resultSet.getInt("BoughtInLastMonth");
		int timesPosted = resultSet.getInt("TimesPosted");
		int numViews = resultSet.getInt("NumViews");
		int likes = resultSet.getInt("Likes");
		int dislikes = resultSet.getInt("Dislikes");
		return new Products(productId, title, imageUrl, productUrl, stars, reviews, price, listedPrice, categoryId,
				bestSeller, boughtInLastMonth, timesPosted, numViews, likes, dislikes);
	}
}
