<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AllPosts</title>
</head>
<body>
	<h1>All Posts</h1>
	<table border="1">
		<tr>
			<th>PostId</th>
			<th>Created</th>
			<th>Review</th>
			<th>Rating</th>
			<th>NumInteractions</th>
			<th>Active</th>
			<th>UpVotes</th>
			<th>DownVotes</th>
			<th>Shares</th>
			<th>UserName</th>
			<th>ProductId</th>
			<th>Comments</th>
			<th>Delete BlogPost</th>
		</tr>
		<c:forEach items="${posts}" var="posts">
			<tr>
				<td><c:out value="${posts.getPostId()}" /></td>
				<td><c:out value="${posts.getCreated()}" /></td>
				<td><c:out value="${posts.getReview()}" /></td>
				<td><c:out value="${posts.getRating()}" /></td>
				<td><c:out value="${posts.getNumInteractions()}" /></td>
				<td><c:out value="${posts.isActive()}" /></td>
				<td><c:out value="${posts.getUpVotes()}" /></td>
				<td><c:out value="${posts.getDownVotes()}" /></td>
				<td><c:out value="${posts.getShares()}" /></td>
				<td><c:out value="${posts.getUserName()}" /></td>
				<td><c:out value="${posts.getProductId()}" /></td>
				<td><a
					href="singlepost?postid=<c:out value="${posts.getPostId()}"/>">See Post</a></td>
				<td><a
					href="comments?postid=<c:out value="${posts.getPostId()}"/>">Comments</a></td>
				<td><a
					href="deletepost?postid=<c:out value="${posts.getPostId()}"/>">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
