<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>All Products</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style type="text/css">
/* Custom CSS styling */
body {
	font-family: Arial, sans-serif;
}

.box {
	padding: 20px;
}

h1 {
	margin-bottom: 30px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 30px;
}

th, td {
	padding: 10px;
	border: 1px solid #ddd;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

a {
	text-decoration: none;
	color: #007bff;
}

a:hover {
	text-decoration: underline;
}

.del-button {
	border: none;
	background-color: transparent;
	text-decoration: underline;
	padding-left: 0px;
}

.box {
	/* Ensure the box does not overflow */
	overflow-x: hidden;
}

.pagination {
	/* Fixed width for the pagination box */
	width: 100%;
	/* Enable horizontal scrolling */
	overflow-x: auto;
}

.pagination-numbers {
	/* Prevent pagination numbers from wrapping */
	white-space: nowrap;
	margin-bottom: 10px;
}

.pagination-list {
	/* Use flexbox to align pagination numbers */
	display: flex;
	/* Remove default padding and margin */
	padding: 0;
	margin: 0;
	/* Ensure list items are displayed in a row */
	flex-direction: row;
}

.pagination-list li {
	/* Add some spacing between pagination numbers */
	margin-right: 5px;
}

.pagination-list li:last-child {
	/* Remove margin for the last pagination number */
	margin-right: 0;
}
</style>
</head>
<body>
	<jsp:include page="Header.jsp" />

	<div class="box">
		<h1>All Products</h1>
		<table class="table">
			<thead class="table-light">
				<tr>
					<th>Title</th>
					<th>Category Name</th>
					<th>Image</th>
					<th>See on Amazon</th>			
					<th>Stars</th>
					<th>Reviews</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${products}" var="product">
					<tr>
						<td>${product.getTitle()}</td>
						<td>${categories[product.categoryId]}</td>
						<td><img src="${product.getImageUrl()}" alt="Product Image"
							style="max-width: 100px;"></td>
						<td><a href="${product.getProductUrl()}" target="_blank">View
								Product</a></td>
						<td>${product.getStars()}</td>
						<td>${product.getReviews()}</td>
						<!-- Add more columns as needed -->
						<td>
							<div class="d-inline">
								<!-- Add actions here -->
							</div> <c:if test="${post.getUserName() == username}">
                            | 
                            <form action="all_products" method="post"
									style="display: inline;">
									<input type="hidden" name="productId"
										value="${product.getProductId()}">
									<button type="submit" class="text-danger del-button">Delete</button>
								</form>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="box">
		<!-- Existing content here -->

		<!-- Pagination -->
		<c:if test="${totalPages > 1}">
			<nav aria-label="Page navigation">
				<ul class="pagination">
					<!-- Pagination numbers wrapped in a div for horizontal scrollbar -->
					<li class="page-item">
						<div class="pagination-box">
							<div class="pagination-numbers">
								<ul class="pagination-list">
									<c:forEach begin="1" end="${totalPages}" var="pageNum">
										<li class="page-item ${pageNum == page ? 'active' : ''}">
											<a class="page-link" href="?page=${pageNum}">${pageNum}</a>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</li>
				</ul>
			</nav>
		</c:if>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		type="text/javascript"></script>
</body>
</html>
