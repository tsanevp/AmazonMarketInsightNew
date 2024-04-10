<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<title>Create Group</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
/* Add custom styles here if needed */
.form-container {
	margin-top: 100px;
}
</style>
</head>
<body>

	<jsp:include page="Header.jsp" />

	<div class="container form-container">
		<h1>Create Group</h1>

		<form action="${pageContext.request.contextPath}/create_group"
			method="post">
			<div class="mb-3">
				<label for="groupName" class="form-label">Group Name</label> <input
					type="text" class="form-control" id="groupName" name="groupName"
					required>
			</div>
			<div class="mb-3">
				<label for="categoryId" class="form-label">Category ID</label> <select
					class="form-select" id="categoryId" name="categoryId" required>
					<option value="">Select Category ID</option>
					<c:forEach items="${categories}" var="category">
						<option value="${category.getCategoryId()}">${category.getCategoryId()}
							- ${category.getName()}</option>
					</c:forEach>
				</select>
			</div>
			<button type="submit" class="btn btn-primary">Create Group</button>
		</form>

		<%-- Display error message if any --%>
		<%
		if (request.getAttribute("errorMessage") != null) {
		%>
		<div class="alert alert-danger mt-3" role="alert">
			${request.getAttribute("errorMessage")}</div>
		<%
		}
		%>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
