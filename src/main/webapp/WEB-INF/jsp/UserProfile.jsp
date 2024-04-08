<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Profile</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style type="text/css">
.user-info h3, .update-info h3 {
	margin-top: 0;
}

.user-info label, .update-info label {
	font-weight: bold;
}

.update-info .form-group {
	margin-bottom: 20px;
}

.row {
	align-items: flex-start;
}

.col-md-6 {
	padding: 0 15px;
}

.form-group {
	margin-bottom: 1rem;
}

.form-control {
	display: block;
	width: 100%;
	padding: .375rem .75rem;
	font-size: 1rem;
	line-height: 1.5;
	color: #495057;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #ced4da;
	border-radius: .25rem;
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
}
</style>
</head>
<body>
	<jsp:include page="Header.jsp" />

	<div class="container mt-5">
		<h1 class="mb-4">User Profile</h1>
		<div class="row mt-4">
			<div class="col-md-6 user-info">
				<h3>User Information</h3>
				<div class="form-group">
					<label for="currentUsername">Username</label>
					<p id="currentUsername">${user.userName}</p>
				</div>
				<div class="form-group">
					<label for="currentFirstName">First Name</label>
					<p id="currentFirstName">${user.firstName}</p>
				</div>
				<div class="form-group">
					<label for="currentLastName">Last Name</label>
					<p id="currentLastName">${user.lastName}</p>
				</div>
				<div class="form-group">
					<label for="currentEmail">Email</label>
					<p id="currentEmail">${user.email}</p>
				</div>
				<div class="form-group">
					<label for="currentPhoneNumber">Phone Number</label>
					<p id="currentPhoneNumber">${user.phoneNumber}</p>
				</div>
				<div class="form-group">
					<label for="currentDob">Date of Birth</label>
					<p id="currentDob">
						<fmt:formatDate value="${user.dob}" pattern="MM/dd/yyyy" />
					</p>
				</div>
				<div class="form-group">
					<label for="currentSubscribed">Subscribed</label>
					<p id="currentSubscribed">${user.subscribed ? 'Yes' : 'No'}</p>
				</div>
			</div>
			<div class="col-md-6 update-info">
				<h3>Update Information</h3>
				<form action="update_user" method="post">
					<!-- Input fields for updated information -->
					<div class="form-group">
						<label for="firstName">First Name</label> <input type="text"
							class="form-control" id="firstName" name="firstName"
							value="${user.firstName}">
					</div>
					<div class="form-group">
						<label for="lastName">Last Name</label> <input type="text"
							class="form-control" id="lastName" name="lastName"
							value="${user.lastName}">
					</div>
					<div class="form-group">
						<label for="email">Email</label> <input type="email"
							class="form-control" id="email" name="email"
							value="${user.email}">
					</div>
					<div class="form-group">
						<label for="phoneNumber">Phone Number</label> <input type="text"
							class="form-control" id="phoneNumber" name="phoneNumber"
							value="${user.phoneNumber}">
					</div>
					<div class="form-group">
						<label for="dob">Date of Birth</label> <input type="date"
							class="form-control" id="dob" name="dob" value="${user.dob}">
					</div>
					<div class="form-group">
						<label for="subscribed">To Subscribe, click your photo top
							right after updating this information</label>
					</div>
					<button type="submit" class="btn btn-primary">Update</button>
					<p>
						<span id="successMessage"><b>${messages.success}</b></span>
					</p>
					<p>
						<span id="successMessage"><b>${messages.error}</b></span>
					</p>
				</form>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		type="text/javascript"></script>
</body>
</html>