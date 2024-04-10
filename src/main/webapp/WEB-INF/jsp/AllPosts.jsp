<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>All Posts</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
/* Custom CSS styling */
body {
	font-family: Arial, sans-serif;
}

.container {
	padding: 20px;
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
	border: 1px solid #ddd;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

a {
	text-decoration: none;
	color: #007bff;
}

a:hover {
	text-decoration: underline;
}

.del-button {
	border: none;
	background-color: transparent;
	text-decoration: underline;
	padding-left: 0px;
}
</style>
</head>
<body>
	<jsp:include page="Header.jsp" />

	<div class="container">
		<h1>All Posts</h1>
		<table class="table">
			<thead class="table-light">
				<tr>
					<th>Post ID</th>
					<th>Created</th>
					<th>Review</th>
					<th>Rating</th>
					<th>Num Interactions</th>
					<th>Active</th>
					<th>Up Votes</th>
					<th>Down Votes</th>
					<th>Shares</th>
					<th>User Name</th>
					<th>Product ID</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${posts}" var="post">
					<tr>
						<td>${post.getPostId()}</td>
						<td>${post.getCreated()}</td>
						<td>${post.getReview()}</td>
						<td>${post.getRating()}</td>
						<td>${post.getNumInteractions()}</td>
						<td>${post.isActive()}</td>
						<td>${post.getUpVotes()}</td>
						<td>${post.getDownVotes()}</td>
						<td>${post.getShares()}</td>
						<td>${post.getUserName()}</td>
						<td>${post.getProductId()}</td>
						<td>
							<div class="d-inline">
								<a href="view_post?postId=${post.getPostId()}">See Post</a> | <a
									href="comments?postid=${post.getPostId()}">Comments</a>
							</div> <c:if test="${post.getUserName() == username}">
                | 
                				<form action="all_posts" method="post"
									style="display: inline;">
									<input type="hidden" name="postId" value="${post.getPostId()}">
									<button type="submit" class="text-danger del-button">Delete</button>
								</form>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

	<script type="text/javascript">
		
	</script>
</body>
</html>
