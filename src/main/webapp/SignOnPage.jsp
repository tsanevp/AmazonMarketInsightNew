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

<title>Welcome Page</title>
</head>
<body>
	<main class="form-signin">
		<form action="UserLogin" method="post">

			<h1 class="h3 mb-3 fw-normal">Please sign in</h1>
			<div class="form-floating">
				<input type="text" class="form-control" id="username"
					name="username" placeholder="username"> <label
					for="username">Username</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingPassword"
					name="password" placeholder="Password"> <label
					for="floatingPassword">Password</label>
			</div>
			<p>
				<span id="successMessage"><b>${messages.success}</b></span>
			</p>
			<div class="form-check text-start my-3">
				<input class="form-check-input" type="checkbox" value="remember-me"
					id="flexCheckDefault"> <label class="form-check-label"
					for="flexCheckDefault"> Remember me </label>
			</div>
			<button class="btn btn-primary w-100 py-2" type="submit">Sign
				in</button>
		</form>
		<div class="line-container">
			<div class="line"></div>
			<div class="line-text">New User?</div>
		</div>

		<button id="registerButton"
			class="btn btn-primary w-100 py-2 register" type="submit">Create
			an account</button>
	</main>

	<script>
		document.getElementById("registerButton").addEventListener("click",
				function() {
					window.location.href = "UserCreate"
				});
	</script>
</body>
</html>