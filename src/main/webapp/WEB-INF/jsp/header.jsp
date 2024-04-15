<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<header class="p-3 mb-3 border-bottom">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <!-- Logo -->
            <a href="home_page">
                <img src="images/logo.png" alt="Amazon Review Hub Logo" width="auto" height="40">
            </a>
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="home_page" class="nav-link px-2 link-secondary">Home</a></li>
                <li><a href="all_products" class="nav-link px-2 link-body-emphasis">Products</a></li>
                <li><a href="all_posts" class="nav-link px-2 link-body-emphasis">Posts</a></li>
                <li class="dropdown">
                    <a href="#" class="nav-link px-2 link-body-emphasis dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">Groups </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="all_groups">All Groups</a></li>
                        <li><a class="dropdown-item" href="#">Joined Groups</a></li>
                        <li><a class="dropdown-item" href="#">My Groups</a></li>
                        <li><a class="dropdown-item" href="create_group">Create Group</a></li>
                    </ul>
                </li>
            </ul>

            <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search" action="">
                <input type="search" class="form-control" placeholder="Search..." aria-label="Search">
            </form>

            <div class="dropdown text-end">
                <a href="#" class="d-block link-body-emphasis text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="https://github.com/mdo.png" alt="mdo" width="32" height="32" class="rounded-circle">
                </a>
                <ul class="dropdown-menu text-small" style="">
                    <li><a class="dropdown-item" href="#">New post...</a></li>
                    <li><a class="dropdown-item" href="my_posts">My posts</a></li>
                    <li><a class="dropdown-item" href="my_wishlist">My wishlist</a></li>
                    <li><a class="dropdown-item" href="manage_subscription">Subscribe</a></li>
                    <li><a class="dropdown-item" href="my_profile">Profile</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="logout">Sign out</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
</body>
</html>
