<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Create Post</title>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet">
<style>
/* Add custom styles here if needed */
</style>
</head>
<body>

<jsp:include page="Header.jsp" />

<div class="container">
    <h1 class="mt-5">Create Post</h1>   
    <form action="create_post" method="post" class="mt-4">
        <div class="mb-3">
            <label for="productId" class="form-label">Product ID</label> 
            <input type="text" class="form-control" id="productId" name="productId" value="${not empty productId ? productId : ''}" ${not empty productId ? 'readonly' : ''} required>
        </div>
        <div class="mb-3">
            <label for="rating" class="form-label">Rating (Decimal Value 0.0 - 5.0)</label> 
            <input type="number" step="0.1" min="0" max="5" class="form-control" id="rating" name="rating" required>
        </div>
        <div class="mb-3">
            <label for="review" class="form-label">Review Content</label>
            <textarea class="form-control" id="review" name="review" rows="3" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
