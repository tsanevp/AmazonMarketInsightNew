<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<title>Home</title>

</head>
<body>
	<jsp:include page="Header.jsp" />

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

	<!-- Error message modal -->
	<div class="modal fade" id="errorModal" tabindex="-1" role="dialog"
		aria-labelledby="errorModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" onclick="closeErrorModal()">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<!-- Display the error message here -->
					<p id="errorMessage">There was an error.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						onclick="closeErrorModal()">OK</button>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery and Bootstrap JavaScript -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

	<script type="text/javascript">
		// Function to show the error modal with a specific message
		function showErrorModal(message) {
			// Update the error message in the modal
			document.getElementById('errorMessage').textContent = message;
			// Show the modal using jQuery
			$('#errorModal').modal('show');
		}

		// Function to close the error modal
		function closeErrorModal() {
			$('#errorModal').modal('hide');
			
	        history.replaceState(null, null, 'home_page');
		}

		// Function to check if there's an error message in the URL and display it
		function checkForErrorMessage() {
			var urlParams = new URLSearchParams(window.location.search);
			var errorMessage = urlParams.get('error');
			if (errorMessage) {
				showErrorModal(errorMessage);
			}
		}

		// Call the function to check for error message when the page loads
		window.onload = function() {
			checkForErrorMessage();
		};
	</script>
</body>
</html>