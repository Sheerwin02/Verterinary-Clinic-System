<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>APU Veterinary Clinic System (AVCS) - Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f5f5f5;
        }
        .container {
            max-width: 400px;
            margin-top: 10%;
            background-color: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,.1);
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-control {
            border-radius: 20px;
        }
        .btn-primary {
            border-radius: 20px;
            padding: 10px 30px;
        }
        .btn-success {
            border-radius: 20px;
            padding: 10px 30px;
        }
        h2 {
            text-align: center;
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>APU Veterinary Clinic System (AVCS)</h2>
    <h3>Login</h3>
    <form action="Login" method="POST">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" class="form-control" required autofocus>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" class="form-control" required>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Login</button>
        </div>
        <div class="text-center mt-4">
            <a href="register.jsp" class="btn btn-success">Register</a>
        </div>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="alert alert-danger mt-4" role="alert">
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>
    </form>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
