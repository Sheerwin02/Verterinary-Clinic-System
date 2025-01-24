<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script>
        function toggleAreaOfExpertise() {
            var roleSelect = document.getElementById('role');
            var areaOfExpertiseField = document.getElementById('areaOfExpertiseField');
            if (roleSelect.value === 'Vet') {
                areaOfExpertiseField.style.display = '';
            } else {
                areaOfExpertiseField.style.display = 'none';
            }
        }
    </script>
</head>
<body>
<div class="container mt-4">
    <h2>Register</h2>
    <form action="Register" method="POST">
        <input type="hidden" name="formSubmitted" value="true">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required class="form-control">
            <% if (request.getAttribute("usernameError") != null && "true".equals(request.getParameter("formSubmitted"))) { %>
                <div class="alert alert-danger"><%= request.getAttribute("usernameError") %></div>
            <% } %>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required class="form-control">
            <% if (request.getAttribute("passwordError") != null && "true".equals(request.getParameter("formSubmitted"))) { %>
                <div class="alert alert-danger"><%= request.getAttribute("passwordError") %></div>
            <% } %>
        </div>
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required class="form-control">
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required class="form-control">
            <% if (request.getAttribute("emailError") != null && "true".equals(request.getParameter("formSubmitted"))) { %>
                <div class="alert alert-danger"><%= request.getAttribute("emailError") %></div>
            <% } %>
        </div>
        <div class="form-group">
            <label for="phoneNumber">Phone Number:</label>
            <input type="text" id="phoneNumber" name="phoneNumber" class="form-control">
            <% if (request.getAttribute("phoneNumberError") != null && "true".equals(request.getParameter("formSubmitted"))) { %>
                <div class="alert alert-danger"><%= request.getAttribute("phoneNumberError") %></div>
            <% } %>
        </div>
        <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" class="form-control">
        </div>
        <div class="form-group">
            <label for="role">Role:</label>
            <select id="role" name="role" required class="form-control" onchange="toggleAreaOfExpertise()">
                <option value="">Select Role</option>
                <option value="Managing Staff">Managing Staff</option>
                <option value="Vet">Vet</option>
                <option value="Receptionist">Receptionist</option>
            </select>
        </div>
        <div class="form-group" id="areaOfExpertiseField" style="display:none;">
            <label for="areaOfExpertise">Area of Expertise:</label>
            <input type="text" id="areaOfExpertise" name="areaOfExpertise" class="form-control">
        </div>
        <div class="row">
            <div class="col">
                <button type="submit" class="btn btn-primary btn-block">Register</button>
            </div>
            <div class="col">
                <a href="login.jsp" class="btn btn-secondary btn-block">Go to Login Page</a>
            </div>
        </div>
        <% if ("true".equals(request.getParameter("formSubmitted"))) { %>
            <% if (request.getAttribute("errorMessage") != null) { %>
                <div class="alert alert-danger mt-3"><%= request.getAttribute("errorMessage") %></div>
            <% } %>
        <% } %>
    </form>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
