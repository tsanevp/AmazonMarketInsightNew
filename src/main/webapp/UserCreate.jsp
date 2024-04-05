<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create an accountr</title>
</head>
<body>
	<h1>Create User</h1>
	<form action="UserCreate" method="post">
		<p>
			<label for="username">UserName</label> <input id="username"
				name="username" value="">
		</p>
		<p>
			<label for="password">Password</label> <input id="password"
				name="password" value="">
		</p>
		<p>
			<label for="firstname">FirstName</label> <input id="firstname"
				name="firstname" value="">
		</p>
		<p>
			<label for="lastname">LastName</label> <input id="lastname"
				name="lastname" value="">
		</p>
		<p>
			<label for="email">Email</label> <input id="email" name="email"
				value="">
		</p>
		<p>
			<label for="phonenumber">PhoneNumber</label> <input id="phonenumber"
				name="phonenumber" value="">
		</p>
		<p>
			<label for="dob">DoB (yyyy-mm-dd)</label> <input id="dob" name="dob"
				value="">
		</p>
		<p>Is Subscribed?</p>
		<input type="radio" id="subscribed_yes" name="subscribed" value="true">
		<label for="subscribed_yes">Yes</label> <input type="radio"
			id="subscribed_no" name="subscribed" value="false"> <label
			for="subscribed_no">No</label>
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