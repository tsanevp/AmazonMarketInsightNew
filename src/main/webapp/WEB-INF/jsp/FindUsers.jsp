<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a User</title>
</head>
<body>
	<form action="findusers" method="post">
		<h1>Search for a User by FirstName</h1>
		<p>
			<label for="firstname">FirstName</label> <input id="firstname"
				name="firstname" value="${fn:escapeXml(param.firstname)}">
		</p>
		<p>
			<input type="submit"> <br />
			<br />
			<br /> <span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br />
	<div id="userCreate">
		<a href="usercreate">Create User</a>
	</div>
	<br />
	<h1>Matching Users</h1>
	<table border="1">
		<tr>
			<th>UserName</th>
			<th>FirstName</th>
			<th>LastName</th>
			<th>Email</th>
			<th>PhoneNumber</th>
			<th>Subscribed</th>
			<th>DoB</th>
			<th>Posts</th>
			<th>Comments</th>
			<th>Delete BlogUser</th>
			<th>Update BlogUser</th>
		</tr>
		<c:forEach items="${users}" var="users">
			<tr>
				<td><c:out value="${users.getUserName()}" /></td>
				<td><c:out value="${users.getFirstName()}" /></td>
				<td><c:out value="${users.getLastName()}" /></td>
				<td><c:out value="${users.getEmail()}" /></td>
				<td><c:out value="${users.getPhoneNumber()}" /></td>
				<td><c:out value="${users.isSubscribed()}" /></td>
				<td><fmt:formatDate value="${users.getDob()}"
						pattern="yyyy-MM-dd" /></td>
				<td><a
					href="userposts?username=<c:out value="${users.getUserName()}"/>">Posts</a></td>
				<td><a
					href="comments?username=<c:out value="${users.getUserName()}"/>">Comments</a></td>
				<td><a
					href="userdelete?username=<c:out value="${users.getUserName()}"/>">Delete</a></td>
				<td><a
					href="userupdate?username=<c:out value="${users.getUserName()}"/>">Update</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
