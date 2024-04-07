<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add User Group Member!</title>
</head>
<body>
	<h1>Add A User</h1>
	<form action="usergroupmemberadd" method="post">
		<p>
			<label for="groupid">GroupId</label> <input id="groupid"
				name="groupid" value="">
		</p>
		<p>
			<label for="username">UserName</label> <input id="username"
				name="username" value="">
		</p>
		<p>
			<label for="role">Role (Owner, Admin, Member)</label> <input
				id="role" name="role" value="">
		</p>
		<p>
			<label for="joined">Joined On (yyyy-mm-dd)</label> <input id="joined"
				name="joined" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br />
	<br />
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>