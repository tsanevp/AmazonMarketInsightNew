<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/loginPage.css" rel="stylesheet">
<link href="css/userCreate.css" rel="stylesheet">

<title>Create an account</title>

</head>
<body>
	<main class="form-signin">
		<form action="UserCreate" method="post">

			<h1 class="h3 mb-3 fw-normal">Create an account</h1>
			<div class="form-floating">
				<input type="text" class="form-control" id="username"
					name="username" placeholder="Username" required> <label
					for="username">Username</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingPassword"
					name="password" placeholder="Password" required> <label
					for="floatingPassword">Password</label>
			</div>
			<div class="form-floating">
				<input type="text" class="form-control" id="firstname"
					name="firstname" placeholder="First Name" required> <label
					for="firstname">First Name</label>
			</div>
			<div class="form-floating">
				<input type="text" class="form-control" id="lastname"
					name="lastname" placeholder="Last Name" required> <label
					for="lastname">Last Name</label>
			</div>
			<div class="form-floating">
				<input type="email" class="form-control" id="email" name="email"
					placeholder="Email" required> <label for="Email">Email</label>
			</div>
			<div class="form-floating">
				<input type="tel" class="form-control" id="phonenumber"
					pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" name="phonenumber"
					placeholder="Phone Number" required> <label
					for="phonenumber">Phone Number</label>
			</div>
			<div class="form-floating">
				<input type="date" class="form-control" id="dob" name="dob"
					placeholder="DoB" required> <label for="dob">Date
					of Birth</label>
			</div>
			<p>
				<span id="successMessage"><b>${messages.success}</b></span>
			</p>
			<p>
				<span id="successMessage"><b>${messages.error}</b></span>
			</p>
			<button class="btn btn-primary w-100 py-2" type="submit">Create
				account</button>
		</form>
		
		<a id="returnLogin" class="btn btn-primary w-100 py-2" href="UserLogin">Return to login</a>
	</main>
</body>
</html>