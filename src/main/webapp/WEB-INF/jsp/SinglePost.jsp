<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Post</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style type="text/css">
/* Custom CSS styling */
.post-container {
	margin-top: 50px;
}

.post-card {
	padding: 20px;
	margin-bottom: 20px;
}

.post-info, .comment-info {
	font-size: 12px;
	color: #6c757d;
}

.interaction-buttons {
	margin-top: 10px;
}

.comment-container {
	margin-top: 20px;
}

.add-comment-button, .post_comment {
	margin-top: 20px;
	margin-bottom: 30px;
}

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
</style>
</head>
<body>
	<jsp:include page="Header.jsp" />

	<div class="container post-container">
		<div class="card post-card">
			<div class="card-body">
				<h1>${post.getReview()}</h1>
				<p>User: ${post.getUserName()}</p>
				<p>
					Posted on:
					<fmt:formatDate value="${post.getCreated()}"
						pattern="MM/dd/yyyy HH:mm a" />
				</p>
				<p>Rating: ${post.getRating()}</p>
				<p>Total Interactions: ${post.getNumInteractions()}</p>
				<p>Active: ${post.isActive()}</p>
				<div class="interaction-info">
					<span class="post-info">Up Votes: ${post.getUpVotes()}</span> <span
						class="post-info">Down Votes: ${post.getDownVotes()}</span> <span
						class="post-info">Shares: ${post.getShares()}</span> <span
						class="post-info">Number Comments: ${fn:length(comments)}</span> <span
						class="post-info">PostId: ${post.getPostId()}</span>

				</div>
				<div class="interaction-buttons">
					<!-- Like Button -->
					<button class="btn btn-primary" onclick="likePost()">Like</button>
					<!-- Dislike Button -->
					<button class="btn btn-danger" onclick="dislikePost()">Dislike</button>
					<!-- Share Button -->
					<button class="btn btn-success" onclick="sharePost()">Share</button>
				</div>
			</div>
		</div>

		<!-- Comments Section -->
		<div class="row comment-container">
			<div class="col">
				<h2>Comments</h2>
				<c:choose>
					<c:when test="${empty comments}">
						<p>No comments yet.</p>
					</c:when>
					<c:otherwise>
						<c:forEach items="${comments}" var="comment">
							<div class="card">
								<div class="card-body">
									<p>${comment.getComment()}</p>
									<p>User: ${comment.getUserName()}</p>
									<p>
										Posted on:
										<fmt:formatDate value="${comment.getCreated()}"
											pattern="MM/dd/yyyy HH:mm a" />
									</p>
									<!-- Display upVotes and downVotes -->	
									<div class="interaction-info">
										<span class="comment-info">Up Votes: ${comment.getUpVotes()}</span>
										<span class="comment-info">Down Votes:
											${comment.getDownVotes()}</span> 
											<span class="comment-info">CommentId:
											${comment.getPostCommentId()}</span>

									</div>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>

				<!-- Add New Comment form -->
				<div class="post_comment" style="display: none;">
					<h3>Add a Comment</h3>
					<form action="post_comment" method="post">
						<input type="hidden" name="postId" value="${post.getPostId()}">
						<div class="mb-3">
							<label for="commentContent" class="form-label">Comment:</label>
							<textarea class="form-control" id="commentContent"
								name="commentContent" rows="3" cols=""></textarea>
						</div>
						<button type="submit" class="btn btn-primary">Submit</button>
						<button type="button" class="btn btn-secondary"
							onclick="cancelComment()">Cancel</button>
					</form>
				</div>

				<!-- Button to toggle Add New Comment form -->
				<button class="btn btn-primary add-comment-button"
					onclick="toggleAddCommentForm()">Add Comment</button>
			</div>
		</div>

		<!-- Similar Products Section -->
		<div class="row similar-products-container">
			<div class="col similar-col">
				<h2>Similar Products</h2>
				<c:choose>
					<c:when test="${empty similarProducts}">
					No similar products.
					</c:when>
					<c:otherwise>
						<!-- Carousel markup -->
						<div id="similarProductsCarousel" class="carousel slide"
							data-bs-ride="carousel">
							<div class="carousel-inner">
								<!-- Iterate through similar products -->
								<c:forEach items="${similarProducts}" var="product"
									varStatus="loop">
									<!-- Check if it's the first item to mark it as active -->
									<c:set var="active" value="${loop.first ? 'active' : ''}" />
									<div class="carousel-item ${active}">
										<div class="row">
											<!-- Display three cards per slide -->
											<c:forEach begin="${loop.index}" end="${loop.index + 2}"
												items="${similarProducts}" var="product">
												<div class="col">
													<div class="card h-100">
														<!-- Set fixed height for the card -->
														<div class="card-body">
															<h5 class="card-title">${product.getTitle()}</h5>
															<p class="card-text">Product ID:
																${product.getProductId()}</p>
															<p class="card-text">Image URL:
																${product.getImageUrl()}</p>
															<p class="card-text">Product URL:
																${product.getProductUrl()}</p>
															<p class="card-text">Stars: ${product.getStars()}</p>
															<p class="card-text">Reviews: ${product.getReviews()}</p>
															<!-- Add more product details as needed -->
														</div>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</c:forEach>
							</div>
							<!-- Carousel controls -->

							<button class="carousel-control-prev" type="button"
								data-bs-target="#similarProductsCarousel" data-bs-slide="prev">
								<span class="carousel-control-prev-icon bg-dark"
									aria-hidden="true"></span>
								<!-- Change arrow color -->
								<span class="visually-hidden">Previous</span>
							</button>
							<button class="carousel-control-next" type="button"
								data-bs-target="#similarProductsCarousel" data-bs-slide="next">
								<span class="carousel-control-next-icon bg-dark"
									aria-hidden="true"></span>
								<!-- Change arrow color -->
								<span class="visually-hidden">Next</span>
							</button>
						</div>

					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		type="text/javascript"></script>

	<script type="text/javascript">
		function toggleAddCommentForm() {
			var addCommentForm = document.querySelector('.post_comment');
			var addCommentButton = document
					.querySelector('.add-comment-button');
			if (addCommentForm.style.display === 'none') {
				addCommentForm.style.display = 'block';
				addCommentButton.style.display = 'none'; // Hide the "Add Comment" button
			}
		}

		function cancelComment() {
			var addCommentForm = document.querySelector('.post_comment');
			var addCommentButton = document
					.querySelector('.add-comment-button');
			addCommentForm.style.display = 'none'; // Hide the form
			addCommentButton.style.display = 'block'; // Show the "Add Comment" button
		}
	</script>
</body>
</html>
