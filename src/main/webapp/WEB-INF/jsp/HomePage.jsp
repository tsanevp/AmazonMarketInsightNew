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

<style>
/* Custom CSS styling */
body {
	font-family: Arial, sans-serif;
}

.hero {
	background-image: url('images/hero-bg.jpg');
	background-size: cover;
	padding: 100px 0;
	text-align: center;
}

.hero-heading {
	font-size: 3rem;
	margin-bottom: 20px;
}

.hero-text {
	font-size: 1.2rem;
	margin-bottom: 30px;
}

.card-body {
	height: 175px;
	display: inline-flex;
	flex-direction: column;
	justify-content: space-between;
	align-items: center;
}

.btn-primary {
	height: 40px;
	width: 150px;
}

.section-heading {
	font-size: 2.5rem;
	margin-bottom: 40px;
}

.about {
	padding: 50px 0;
	text-align: center;
}

.about-text {
	font-size: 1.2rem;
	margin-bottom: 20px;
}

.powerbi-insights {
	padding: 50px 0;
	text-align: center;
}

/* Custom carousel styling */
.similar-products-container {
	margin-bottom: 50px;
}

/* Set fixed height for carousel controls */
.carousel-control-prev, .carousel-control-next {
	width: 10%;
}

/* Center carousel controls vertically */
.carousel-control-prev {
	top: 50%;
	transform: translateX(-75%);
}

/* Center carousel controls vertically */
.carousel-control-next {
	top: 50%;
	transform: translateX(75%);
}

/* Set fixed height for cards */
.card {
	height: 100%;
}

/* Set fixed height for card body */
.card-body {
	height: 100%;
}

.carousel-indicators {
	transform: translateY(250%);
}

.navi {
	margin-bottom: 25px;
}

.carousel-indicators [data-bs-target] {
	background-color: #4834d4 !important;
	list-style-type: none;
}
</style>
</head>
<body>
	<!-- Include header -->
	<jsp:include page="Header.jsp" />

	<!-- Hero Section -->
	<section class="hero">
		<div class="container">
			<h1 class="hero-heading">Welcome to Amazon Review Hub!</h1>
			<p class="hero-text">Get authentic reviews and ratings on a wide
				range of Amazon products from real users.</p>
			<a href="all_products" class="btn btn-primary">Explore Products</a>
		</div>
	</section>

	<!-- Main Sections -->
	<div class="container">
		<section class="row mb-5">
			<!-- Your existing card sections -->
		</section>
	</div>

	<!-- Featured Products Section -->
	<section class="featured-products">
		<div class="container navi">
			<div class="row align-items-center">
				<div class="col">
					<h2 class="section-heading" id="featured-name">Most Posted Products</h2>
				</div>
				<div class="col-auto">
					<!-- Dropdown menu -->
					<div class="dropdown">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							id="dropdownMenuButton" data-bs-toggle="dropdown"
							aria-expanded="false">Select Option</button>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<li><a class="dropdown-item" href="#"
								onclick="handleSelection('most_posted')">Most Posted
									Products</a></li>
							<li><a class="dropdown-item" href="#"
								onclick="handleSelection('top_users')">Top Users</a></li>
							<li><a class="dropdown-item" href="#"
								onclick="handleSelection('best_products')">Best Products</a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- Carousel markup -->
			<div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
				<div class="carousel-inner">
					<c:forEach var="product" items="${mostPostedProducts}"
						varStatus="status">
						<c:if test="${status.index % 3 == 0}">
							<div class="carousel-item${status.index == 0 ? ' active' : ''}">
								<div class="row">
						</c:if>
						<div class="col">
							<div class="card h-100">
								<div class="card-body">
									<h5 class="card-title">${product.key}</h5>
									<p class="card-text">Posts: ${product.value}</p>
									<a href="all_posts?productId=${product.key}" class="btn btn-primary">View Details</a>
								</div>
							</div>
						</div>
						<c:if test="${status.index % 3 == 2 or status.last}">
				</div>
			</div>
			</c:if>
			</c:forEach>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#myCarousel" data-bs-slide="prev">
			<span class="carousel-control-prev-icon bg-dark" aria-hidden="true"></span>
			<span class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#myCarousel" data-bs-slide="next">
			<span class="carousel-control-next-icon bg-dark" aria-hidden="true"></span>
			<span class="visually-hidden">Next</span>
		</button>
		<div class="carousel-nav-bottom-bar">
			<ol class="carousel-indicators">
				<c:set var="numPages" value="${fn:length(mostPostedProducts) / 3}" />
				<c:forEach var="page" begin="0" end="${numPages}">
					<li data-bs-target="#myCarousel" data-bs-slide-to="${page}"
						class="<c:if test='${page == 0}'>active</c:if>"></li>
				</c:forEach>
			</ol>
		</div>
		</div>
		</div>
	</section>

	<section class="about">
    <div class="container">
        <h2 class="section-heading">About Us</h2>
        <p class="about-text">Welcome to Amazon Review Hub, your trusted destination for genuine product insights. Our community-driven platform is dedicated to providing authentic reviews and empowering users to make informed choices. From the latest gadgets to household essentials, discover a diverse range of products curated from the vast Amazon marketplace. Join us in celebrating transparency, trust, and the power of community as we redefine the online shopping experience together.</p>
    </div>
</section>

	<!-- Power BI Insights Section -->
	<section class="powerbi-insights">
		<div class="container">
			<h2 class="section-heading">Product & Category Insights</h2>
			<iframe title="PM5" width="100%" height="500"
				src="https://app.powerbi.com/view?r=eyJrIjoiYTlkYzliM2QtMTQ1Ny00Njg4LWFhNDQtMjZiNTA0MTQ2OTRkIiwidCI6ImE4ZWVjMjgxLWFhYTMtNGRhZS1hYzliLTlhMzk4YjkyMTVlNyIsImMiOjN9"
				frameborder="0" allowFullScreen="true"></iframe>
		</div>
	</section>

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

	<!-- JavaScript for Error Modal -->
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

		function handleSelection(option) {
			$
					.ajax({
						type : 'POST',
						url : 'home_page',
						data : {
							option : option
						},
						success : function(response) {
							// Replace the carousel inner HTML with the updated content
    						var carouselHTML = response.carouselHTML;
    					    var carouselIndicatorsHTML = response.carouselIndicatorsHTML;
    					    var newSectionTitle = response.newSectionTitle;

							$('#myCarousel .carousel-inner').html(carouselHTML);
						    $('.carousel-indicators').html(carouselIndicatorsHTML);
							$('#featured-name').html(newSectionTitle);
				            
							$('#myCarousel').carousel();
						},
						error : function(xhr, status, error) {
							console.error('Error:', error);
							var errorMessage = "There was an error getting this information";
							window.location.href = "home_page?error="
									+ errorMessage;

						}
					});
		}
	</script>
</body>
</html>
