<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<title>Welcome Page</title>
</head>
   <style>
        body, html {
            height: 100%;
        }
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 0;
            margin: 0;
        }
        .form-signin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }
    </style>
<body>
    <main class="form-signin">
        <form>
            <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
            <div class="form-floating">
                <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                <label for="floatingInput">Email address</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
                <label for="floatingPassword">Password</label>
            </div>
            <div class="form-check text-start my-3">
                <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault">
                <label class="form-check-label" for="flexCheckDefault">
                    Remember me
                </label>
            </div>
            <button class="btn btn-primary w-100 py-2" type="submit">Sign in</button>
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
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>	
</body>
</html>