<%-- 
    Document   : addPetProfile
    Created on : Mar 12, 2024, 12:43:56 AM
    Author     : Sheerwin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Pet Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Add New Pet Profile</h2>
    <form action="AddPetProfile" method="post">
        <div class="mb-3">
            <label for="name" class="form-label">Pet Name:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="mb-3">
            <label for="species" class="form-label">Species:</label>
            <input type="text" class="form-control" id="species" name="species" required>
        </div>
        <div class="mb-3">
            <label for="customerId" class="form-label">Customer:</label>
            <select id="customerId" name="customerId" class="form-select">
                <!-- Dynamically populate options -->
                <c:forEach items="${customers}" var="customer">
                    <option value="${customer.customerId}">${customer.name}</option>
                </c:forEach>
            </select>
        </div>
        <!-- Button Container with Equal Spacing -->
        <div class="btn-group" role="group" aria-label="Pet Profile Actions">
            <button type="submit" class="btn btn-primary">Add Pet Profile</button>
            <a href="ViewPetProfiles" class="btn btn-info">View Pet Profiles</a>
        </div>

        <div class="mt-3">
            <a href="receiptionistDashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
        </div>
    </form>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
