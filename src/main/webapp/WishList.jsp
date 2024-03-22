<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Wishlists</title>
</head>
<body>
	<h1>${messages.title}</h1>
	<table border="1">
		<tr>
			<th>WishListId</th>
			<th>UserName</th>
			<th>ProductId</th>
		</tr>
		<c:forEach items="${wishlists}" var="wishlists">
			<tr>
				<td><c:out value="${wishlists.getWishListId()}" /></td>
				<td><c:out value="${wishlists.getUserName()}" /></td>
				<td><c:out value="${wishlists.getProductId()}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>