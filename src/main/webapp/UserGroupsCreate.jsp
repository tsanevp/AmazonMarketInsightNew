<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a User Group Community!</title>
</head>
<body>
	<h1>Create User</h1>
	<form action="usergroupcreate" method="post">
		<p>
			<label for="groupid">GroupId</label> <input id="groupid"
				name="groupid" value="">
		</p>
		<p>
			<label for="groupname">GroupName</label> <input id="groupname"
				name="groupname" value="">
		</p>
		<p>
			<label for="created">Created (yyyy-mm-dd)</label> <input id="created"
				name="created" value="">
		</p>
		<p>
			<label for="categoryid">CategoryId (Category Associated With)</label>
			<input id="categoryid" name="categoryid" value="">
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