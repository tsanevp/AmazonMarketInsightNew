<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Comments</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
/* Add custom styles here if needed */
</style>
</head>
<body>
	<jsp:include page="Header.jsp" />

	<div class="container mt-5">
		<h1>${messages.title}</h1>
		<%-- <div class="mb-3">
            <!-- Button to view user's profile -->
            <a href="userprofile?username=${comments[0].getUserName()}" class="btn btn-primary">View User Profile</a>
        </div> --%>
		<table class="table table-bordered">
			<thead class="table-light">
				<!-- Bootstrap's table header background color -->
				<tr>
					<th>PostId</th>
					<th>UserName</th>
					<th>PostCommentId</th>
					<th>Comment</th>
					<th>Created</th>
					<th>UpVotes</th>
					<th>DownVotes</th>
					<th>Action</th>
					<!-- New column for delete comment link -->
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${comments}" var="postComment">
					<tr>
						<td><c:out value="${postComment.getPostId()}" /></td>
						<td><c:out value="${postComment.getUserName()}" /></td>
						<td><c:out value="${postComment.getPostCommentId()}" /></td>
						<td><c:out value="${postComment.getComment()}" /></td>
						<td><fmt:formatDate value="${postComment.getCreated()}"
								pattern="MM-dd-yyyy hh:mm:sa" /></td>
						<td><c:out value="${postComment.getUpVotes()}" /></td>
						<td><c:out value="${postComment.getDownVotes()}" /></td>
						<!-- Delete comment link -->
						<td><a href="#" onclick="deleteComment(${postComment.getPostCommentId()}, '${postComment.getUserName()}')" class="text-danger">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		type="text/javascript"></script>


	<script type="text/javascript">
		function deleteComment(commentId, username) {
			$.ajax({
				type : "POST",
				url : "user_comments",
				data : {
					deleteCommentId : commentId,
					userWhoPostedComment : username
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
