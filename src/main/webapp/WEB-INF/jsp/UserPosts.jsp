<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Posts</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
  /* Custom CSS styling */
  .post-container {
    margin-top: 50px;
  }
  .post-card {
    margin-bottom: 20px;
  }
  .post-title {
    font-size: 24px;
    font-weight: bold;
  }
  .post-button {
    margin-top: 10px;
  }
</style>
</head>
<body>
<jsp:include page="Header.jsp" />

<div class="container post-container">
  <div class="row">
    <div class="col">
      <h1 class="mb-4">My Posts</h1>
    </div>
    <div class="col text-end">
      <a href="create_post.jsp" class="btn btn-primary">Create New Post</a>
    </div>
  </div>
  <h5 class="mb-4">Total Posts: ${fn:length(posts)}</h5>
  <c:forEach items="${posts}" var="post">
    <div class="card post-card">
      <div class="card-body">
        <h5 class="card-title post-title">${post.getReview()}</h5>
        <p class="card-text">Product ID: ${post.getProductId()}</p>
		<p class="card-text">User Name: ${post.getUserName()}</p>
        <p class="card-text">Posted on: <fmt:formatDate value="${post.getCreated()}" pattern="MM/dd/yyyy HH:mm a" /></p>
        <p class="card-text">Rating: ${post.getRating()}</p>
        <p class="card-text">Up Votes: ${post.getUpVotes()}</p>
        <p class="card-text">Down Votes: ${post.getDownVotes()}</p>
        <p class="card-text">Shares: ${post.getShares()}</p>
        <p class="card-text">Total Interactions: ${post.getNumInteractions()}</p>
        <p class="card-text">Active: ${post.isActive()}</p>        
        <!-- Add more attributes as needed -->
        <a href="view_post?postId=${post.getPostId()}" class="btn btn-primary post-button">View Post</a>
      </div>
    </div>
  </c:forEach>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
