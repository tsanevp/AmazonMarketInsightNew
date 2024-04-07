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

<title>Home</title>

</head>
<body>
	<header class="p-3 mb-3 border-bottom">
		<div class="container">
			<div
				class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
				<ul
					class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
					<li><a href="#" class="nav-link px-2 link-secondary">Overview</a></li>
					<li><a href="#" class="nav-link px-2 link-body-emphasis">Inventory</a></li>
					<li><a href="#" class="nav-link px-2 link-body-emphasis">Customers</a></li>
					<li><a href="#" class="nav-link px-2 link-body-emphasis">Products</a></li>
				</ul>

				<form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search"
					action="">
					<input type="search" class="form-control" placeholder="Search..."
						aria-label="Search">
				</form>

				<div class="dropdown text-end">
					<a href="#"
						class="d-block link-body-emphasis text-decoration-none dropdown-toggle"
						data-bs-toggle="dropdown" aria-expanded="false"> <img
						src="https://github.com/mdo.png" alt="mdo" width="32" height="32"
						class="rounded-circle">
					</a>
					<ul class="dropdown-menu text-small" style="">
						<li><a class="dropdown-item" href="#">New project...</a></li>
						<li><a class="dropdown-item" href="#">Settings</a></li>
						<li><a class="dropdown-item" href="#">Profile</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item" href="logout">Sign out</a></li>
					</ul>
				</div>
			</div>
		</div>
	</header>
	<!-- 
	<main class="form-signin">
		<form action="">
			<h1 class="h3 mb-3 fw-normal">Please sign in</h1>
			<div class="form-floating">
				<input type="email" class="form-control" id="floatingInput"
					placeholder="name@example.com"> <label for="floatingInput">Email
					address</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingPassword"
					placeholder="Password"> <label for="floatingPassword">Password</label>
			</div>
			<div class="form-check text-start my-3">
				<input class="form-check-input" type="checkbox" value="remember-me"
					id="flexCheckDefault"> <label class="form-check-label"
					for="flexCheckDefault"> Remember me </label>
			</div>
			<button class="btn btn-primary w-100 py-2" type="submit">Sign
				in</button>
		</form>
	</main>

	<h1>Welcome to the Application</h1>
	<ul>
		<li><a href="UserCreate.jsp">Create a User</a></li>
		<li><a href="users">See all Users</a></li>
		<li><a href="UserUpdate.jsp">Update a User</a></li>
		<li><a href="FindUsers.jsp">Find a User</a></li>
		<li><a href="Wishlist.jsp">All Wishlists</a></li>

		<li><a href="CreditCards.jsp">Credit Cards Information</a></li>

		<li><a href="Achievements.jsp">See Achievements</a></li>
		<li><a href="AchievementsEarned.jsp">See Achievements Earned</a></li>

		<li><a href="UserGroupsCreate.jsp">Create a User Group</a></li>
		<li><a href="UserGroups.jsp">See all User Groups</a></li>
		<li><a href="AddGroupMember.jsp">Add User Group Member</a></li>
		<li><a href="GroupMembers.jsp">All Group Members</a></li>


		<li><a href="Categories.jsp">Categories Information</a></li>
		<li><a href="Products.jsp">See all Products</a></li>

		<li><a href="allposts">All Posts</a></li>
		<li><a href="PostComments.jsp">Post Comments</a></li>
	</ul>
	 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		type="text/javascript"></script>
</body>
</html>