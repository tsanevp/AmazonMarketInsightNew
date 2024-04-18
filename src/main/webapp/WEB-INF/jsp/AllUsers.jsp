<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>All Users</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom CSS -->
<style>
body {
	font-family: Arial, sans-serif;
}

h1 {
	margin-bottom: 30px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 30px;
}

th, td {
	padding: 10px;
	border: 1px solid #dee2e6; /* Bootstrap's table border color */
	text-align: left;
}

th {
	background-color: #f8f9fa;
	/* Bootstrap's table header background color */
}

tr:nth-child(even) {
	background-color: #f8f9fa; /* Bootstrap's table row background color */
}

a {
	text-decoration: none;
	color: #007bff; /* Bootstrap's primary color */
}

a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<jsp:include page="Header.jsp" />

	<div class="container">
		<h1 class="mt-5">All Users</h1>
		<table class="table table-bordered">
			<thead class="table-light">
				<!-- Bootstrap's table header background color -->
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
					<th>Delete User</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td><c:out value="${user.getUserName()}" /></td>
						<td><c:out value="${user.getFirstName()}" /></td>
						<td><c:out value="${user.getLastName()}" /></td>
						<td><c:out value="${user.getEmail()}" /></td>
						<td><c:out value="${user.getPhoneNumber()}" /></td>
						<td><c:out value="${user.isSubscribed()}" /></td>
						<td><fmt:formatDate value="${user.getDob()}"
								pattern="yyyy-MM-dd" /></td>
						<td><a
							href="user_posts?username=<c:out value="${user.getUserName()}"/>">Posts</a></td>
						<td><a
							href="user_comments?username=<c:out value="${user.getUserName()}"/>">Comments</a></td>
						<td>
							<a href="#" onclick="deleteUser('${user.getUserName()}')">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		type="text/javascript"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		type="text/javascript"></script>


	<script type="text/javascript">
		function deleteUser(username) {
			$.ajax({
				type : "POST",
				url : "all_users",
				data : {
					action: "delete",
					username : username
				},
				success : function(response) {
					// Reload the page after successfully joining the group
					location.reload();
				},
				error : function(xhr, status, error) {
					// Handle error if joining the group fails
					console.error(error);
					// Display an error message or perform any other action as needed
				}
			});
		}
	</script>
</body>
</html>
