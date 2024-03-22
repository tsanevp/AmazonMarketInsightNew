<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products</title>
</head>
<body>
	<h1>Products List</h1>
	<table border="1">
		<tr>
			<th>ProductId</th>
			<th>Title</th>
			<th>ImageUrl</th>
			<th>ProductUrl</th>
			<th>Stars</th>
			<th>Reviews</th>
			<th>Price</th>
			<th>ListedPrice</th>
			<th>CategoryId</th>
			<th>BestSeller</th>
			<th>BoughtInLastMonth</th>
			<th>TimesPosted</th>
			<th>NumViews</th>
			<th>Likes</th>
			<th>Dislikes</th>
		</tr>
		<c:forEach items="${products}" var="products">
			<tr>
				<td><c:out value="${products.getProductId()}" /></td>
				<td><c:out value="${products.getTitle()}" /></td>
				<td><c:out value="${products.getImageUrl()}" /></td>
				<td><c:out value="${products.getProductUrl()}" /></td>
				<td><c:out value="${products.getStars()}" /></td>
				<td><c:out value="${products.getReviews()}" /></td>
				<td><c:out value="${products.getPrice()}" /></td>
				<td><c:out value="${products.getListedPrice()}" /></td>
				<td><c:out value="${products.getCategoryId()}" /></td>
				<td><c:out value="${products.isBestSeller()}" /></td>
				<td><c:out value="${products.getBoughtInLastMonth()}" /></td>
				<td><c:out value="${products.getTimesPosted()}" /></td>
				<td><c:out value="${products.getNumViews()}" /></td>
				<td><c:out value="${products.getLikes()}" /></td>
				<td><c:out value="${products.getDislikes()}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
