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
/* Add your custom CSS styles here */
</style>
</head>
<body>
	<jsp:include page="Header.jsp" />

	<main class="container mt-5">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="card">
					<div class="card-header">
						<h5 class="card-title text-center">Manage Subscription</h5>
					</div>
					<div class="card-body">
						<!-- Subscription status and payment method -->
						<div class="mb-3">
							<h6>
								Subscription Status: <span
									class='text-<c:choose>
	    							<c:when test="${user.subscribed}">success</c:when>
							    	<c:otherwise>danger</c:otherwise>
								</c:choose>'>
									<c:if test="${user.subscribed}">Subscribed</c:if> <c:if
										test="${not user.subscribed}">Not Subscribed</c:if>
								</span>
							</h6>
							<c:if test="${user.subscribed}">
								<h6>
									Payment Method: <span class="text-primary">Credit Card</span>
								</h6>
							
								<div class="col-auto">
									<button id="unsubscribe" class="btn btn-primary" type="submit">Unsubscribe</button>
								</div>
							</c:if>
						</div>
						<hr>
						<!-- Form to add a new card -->
						<form action="manage_subscription" method="post">
							<div class="mb-3">
								<label for="cardNumber" class="form-label">Card Number</label> <input
									type="text" class="form-control" id="cardNumber"
									name="cardNumber">
							</div>
							<div class="row mb-3">
								<div class="col">
									<label for="expiration" class="form-label">Expiration
										Date</label> <input type="date" class="form-control" id="expiration"
										name="expiration">
								</div>
							</div>
							<button type="submit" class="btn btn-primary">Subscribe
								With New Card</button>
						</form>
						<hr>

						<!-- Form to choose from existing cards -->
						<form action="manage_subscription" method="post">
							<div class="mb-3">
								<label for="existingCards" class="form-label">Choose
									Existing Card</label> <select class="form-select" id="existingCards"
									name="existingCards">
									<option selected></option>
									<c:forEach items="${creditCards}" var="creditCard">
										<option value="${creditCard.cardNumber}">${creditCard.cardNumber}</option>
									</c:forEach>
								</select>
							</div>
							<button type="submit" class="btn btn-primary">Subscribe
								With Existing Card</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</main>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		type="text/javascript"></script>
		
	<script type="text/javascript">
	document.getElementById("unsubscribe").addEventListener("click",
			function() {
				var url = "manage_subscription";
				url += "?subscriptionStatus=" + encodeURIComponent("false");
				
				window.location.href = url;
			});
	</script>
</body>
</html>
