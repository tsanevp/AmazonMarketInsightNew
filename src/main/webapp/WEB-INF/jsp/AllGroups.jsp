<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>All Groups</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style type="text/css">
/* Add custom styles here if needed */
</style>
</head>
<body>
	<jsp:include page="Header.jsp" />

	<div class="container mt-4">
		<h1 class="mb-4">All Groups</h1>
		<div class="row row-cols-1 row-cols-md-3 g-4">
			<!-- Iterate over the userGroups list and display each group -->
			<c:forEach items="${userGroups}" var="group">
				<div class="col">
					<div class="card h-100">
						<div class="card-body">
							<h5 class="card-title">${group.groupName}</h5>
							<p class="card-text">Group ID: ${group.groupId}</p>
							<p class="card-text">Category ID: ${group.categoryId}</p>
							<p class="card-text">Member Count:
								${fn:length(usersInGroups[group.groupId])}</p>
							<!-- Show members button -->
							<a href="/view_group?groupId=${group.groupId}"
								class="btn btn-info btn-sm">Show Members</a>
							<!-- Check if the user is already in the group -->
							<c:if test="${fn:length(usersInGroups[group.groupId]) > 0}">
								<!-- If the user is in the group, show the leave button -->
								<button class="btn btn-danger btn-sm leave-btn"
									onclick="leaveGroup(${group.groupId})">Leave</button>
							</c:if>
							<!-- If the user is not in the group, show the join button -->
							<c:if test="${fn:length(usersInGroups[group.groupId]) == 0}">
								<button class="btn btn-primary btn-sm join-btn"
									onclick="joinGroup(${group.groupId})">Join</button>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<script type="text/javascript">
    // JavaScript function to handle joining a group
    function joinGroup(groupId) {
        // Add logic to join the group
        // You can use AJAX to send a request to the server to join the group
        
        // For demonstration purposes, let's just reload the page after joining
        location.reload();
    }

    // JavaScript function to handle leaving a group
    function leaveGroup(groupId) {
        // Add logic to leave the group
        // You can use AJAX to send a request to the server to leave the group
        
        // For demonstration purposes, let's just reload the page after leaving
        location.reload();
    }
</script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		type="text/javascript"></script>
</body>
</html>
