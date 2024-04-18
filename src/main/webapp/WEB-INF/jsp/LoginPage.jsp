<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Amazon Review Hub</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body, html {
            height: 100%;
        }

        body {
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 0;
            margin: 0;
            background-color: #f8f9fa; /* Amazon Review Hub background color */
        }

        .form-signin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: auto;
            background-color: #fff; /* Amazon Review Hub form background color */
            border-radius: 10px; /* Amazon Review Hub form border radius */
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Amazon Review Hub form box shadow */
            text-align: center; /* Center align form content */
        }

        .logo-container {
            margin-bottom: 20px; /* Spacing between logo and form elements */
        }

        .logo-container img {
            width: auto;
            height: 50px;
        }

        .line-container {
            position: relative;
            width: 100%;
            text-align: center;
            margin-top: 20px; /* Adjust the margin top as needed */
        }

        .line {
            border-top: 1px solid #000; /* Change color and thickness as needed */
            width: 100%;
        }

        /* Style for the text */
        .line-text {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #fff; /* Match background color of your page */
            padding: 0 10px; /* Adjust padding as needed */
        }

        .register {
            margin-top: 20px;
        }

        #returnLogin {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <main class="form-signin">
        <div class="logo-container">
            <img src="https://i.imgur.com/L0HDuXV.png" alt="Amazon Review Hub Logo">
        </div>
        <form action="login" method="post">
            <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
            <div class="form-floating">
                <input type="text" class="form-control" id="username" name="username" placeholder="username" required>
                <label for="username">Username</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="Password" required>
                <label for="floatingPassword">Password</label>
            </div>
            <p>
                <span id="successMessage"><b>${messages.error}</b></span>
            </p>
            <button class="btn btn-primary w-100 py-2" type="submit">Sign in</button>
        </form>
        <div class="line-container">
            <div class="line"></div>
            <div class="line-text">New User?</div>
        </div>
        <button id="registerButton" class="btn btn-primary w-100 py-2 register" type="submit">Create an account</button>
    </main>

    <script type="text/javascript">
        document.getElementById("registerButton").addEventListener("click", function() {
            window.location.href = "create_user";
        });
    </script>
</body>
</html>
