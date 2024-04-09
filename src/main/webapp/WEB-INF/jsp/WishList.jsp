<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Wishlist</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
/* Custom CSS styling */
.wishlist-container {
	margin-top: 50px;
}

.wishlist-title {
	font-size: 24px;
	font-weight: bold;
	margin-bottom: 20px;
}

.wishlist-table th {
	background-color: #f8f9fa;
	text-align: center;
}

.wishlist-table td {
	text-align: center;
}
</style>
</head>
<body>
	<jsp:include page="Header.jsp" />

	<div class="container wishlist-container">
		<h1 class="wishlist-title">${user.getFirstName()}'s Wishlist</h1>
		<table class="table table-bordered wishlist-table">
			<thead>
				<tr>
					<th>WishListId</th>
					<th>Product Name</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${wishlistItems}" var="wishlistItem">
					<tr>
						<td><c:out value="${wishlistItem.getWishListId()}" /></td>
						<td><c:out value="${wishlistItem.getProductId()}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
