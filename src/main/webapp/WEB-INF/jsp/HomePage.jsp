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

.featured-products {
    padding: 50px 0;
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
</style>
</head>
<body>
	<jsp:include page="Header.jsp" />

    <!-- Hero Section -->
    <section class="hero">
        <div class="container">
            <h1 class="hero-heading">Welcome to Amazon Product Reviews</h1>
            <p class="hero-text">Get authentic reviews and ratings on a wide range of Amazon products from real users.</p>
            <a href="all_products" class="btn btn-primary">Explore Products</a>
        </div>
    </section>

    <!-- Featured Products Section -->
    <section class="featured-products">
        <div class="container">
            <h2 class="section-heading">Featured Products</h2>
            <div class="row">
                <!-- Sample product card -->
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <img src="images/product1.jpg" class="card-img-top" alt="Product Image">
                        <div class="card-body">
                            <h5 class="card-title">Product Title</h5>
                            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                            <a href="#" class="btn btn-primary">View Details</a>
                        </div>
                    </div>
                </div>
                <!-- Add more product cards here -->
            </div>
        </div>
    </section>

    <!-- About Section -->
    <section class="about">
        <div class="container">
            <h2 class="section-heading">About Us</h2>
            <p class="about-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed blandit turpis nec sem fermentum, a volutpat justo feugiat. Duis fermentum turpis id nibh suscipit, ut ultricies sem malesuada.</p>
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