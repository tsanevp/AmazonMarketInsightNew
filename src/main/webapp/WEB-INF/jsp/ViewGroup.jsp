<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>View Group</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style type="text/css">
</style>
</head>
<body>
	<jsp:include page="Header.jsp" />

	<div class="container mt-4">
		<h1 class="mb-4">Group Information</h1>
		<div class="card">
			<div class="card-body">
				<h5 class="card-title">${group.getGroupName()}</h5>
				<p class="card-text">Group ID: ${group.getGroupId()}</p>
				<p class="card-text">Category ID: ${group.getCategoryId()}</p>
				<p class="card-text">Date Created: ${group.getCreated()}</p>
			</div>
		</div>

		<h2 class="mt-4">Members</h2>
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">Username</th>
						<th scope="col">Role</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${members}" var="member">
						<tr>
							<td><span
								class="
								<c:choose>
                            <c:when test="${member.getRole() == 'OWNER'}">font-weight-bold text-primary</c:when>
                            <c:when test="${member.getRole() == 'ADMIN'}">font-weight-bold text-danger</c:when>
                            <c:otherwise></c:otherwise>
                        </c:choose>">
									${member.getUserName()} </span></td>
							<td><span
								class="<c:choose>
                            <c:when test="${member.getRole() == 'OWNER'}">font-weight-bold text-primary</c:when>
                            <c:when test="${member.getRole() == 'ADMIN'}">font-weight-bold text-danger</c:when>
                            <c:otherwise></c:otherwise>
                        </c:choose>">
									<c:out value="${member.getRole().toString().toLowerCase()}" />
							</span></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<!-- Check if the current user is a member of the group -->
		<c:if test="${requestScope['isMember']}">
			<!-- If the user is in the group, show the leave button -->
			<button class="btn btn-danger btn-sm leave-btn"
				onclick="leaveGroup(${group.groupId})">Leave Group</button>
		</c:if>
		<!-- If the user is not in the group, show the join button -->
		<c:if test="${not requestScope['isMember']}">
			<button class="btn btn-primary btn-sm join-btn"
				onclick="joinGroup(${group.groupId})">Join Group</button>
		</c:if>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		type="text/javascript"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		type="text/javascript"></script>

	<script type="text/javascript">
		// JavaScript function to handle joining a group
		function joinGroup(groupId) {
			$.ajax({
				type : "POST",
				url : "view_group",
				data : {
					groupId : groupId,
					groupStatus : "joining"
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

		// JavaScript function to handle leaving a group
		function leaveGroup(groupId) {
			$.ajax({
				type : "POST",
				url : "view_group",
				data : {
					groupId : groupId,
					groupStatus : "leaving"
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
