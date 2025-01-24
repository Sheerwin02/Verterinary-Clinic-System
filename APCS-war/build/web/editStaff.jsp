<%-- 
    Document   : editStaff
    Created on : Mar 11, 2024, 12:48:34 AM
    Author     : Sheerwin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Users,Model.VetExpertises"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Staff</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Edit Staff Member</h2>
    <form action="EditStaff" method="POST">
        <input type="hidden" name="userId" value="${user.userId}" />
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username" value="${user.username}" required readonly> <!-- Readonly if you don't allow changing username -->
        </div>
        <div class="form-group">
            <label for="password">Password (leave blank to not change):</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name" value="${user.name}" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" value="${user.email}" required>
        </div>
        <div class="form-group">
            <label for="phoneNumber">Phone Number:</label>
            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${user.phoneNumber}" required>
        </div>
        <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" class="form-control" id="address" name="address" value="${user.address}" required>
        </div>
        <c:if test="${user.role == 'Vet'}">
            <div class="form-group">
                <label for="areaOfExpertise">Area of Expertise:</label>
                <input type="text" class="form-control" id="areaOfExpertise" name="areaOfExpertise" value="${areaOfExpertise}" required>
            </div>
        </c:if>
        <button type="submit" class="btn btn-primary">Update</button>
        <a href="ViewStaff" class="btn btn-secondary">Back to Staff List</a>
    </form>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
