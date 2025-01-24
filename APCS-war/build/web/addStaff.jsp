<%-- 
    Document   : addStaff
    Created on : Mar 10, 2024, 7:04:55 PM
    Author     : Sheerwin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Staff</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        function toggleExpertiseField() {
            var role = document.getElementById('role').value;
            var expertiseSection = document.getElementById('expertiseSection');
            expertiseSection.style.display = (role === 'Vet') ? 'block' : 'none';
        }
    </script>
</head>
<body>
<div class="container mt-5">
    <h2>Add New Staff</h2>
    <form action="AddStaff" method="POST">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
        </div>
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="text" class="form-control" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="phoneNumber">Phone Number:</label>
            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" required>
        </div>
        <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" class="form-control" id="address" name="address" required>
        </div>
        <div class="form-group">
            <label for="role">Role:</label>
            <select id="role" name="role" class="form-control" onchange="toggleExpertiseField()">
                <option value="Managing Staff">Managing Staff</option>
                <option value="Vet">Vet</option>
                <option value="Receptionist">Receptionist</option>
            </select>
        </div>
        <div class="form-group" id="expertiseSection" style="display:none;">
            <label for="areaOfExpertise">Area of Expertise:</label>
            <input type="text" class="form-control" id="areaOfExpertise" name="areaOfExpertise">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        <a href="managingStaffDashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
    </form>
</div>
</body>
</html>
