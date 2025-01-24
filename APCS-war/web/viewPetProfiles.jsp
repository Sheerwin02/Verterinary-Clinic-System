<%-- 
    Document   : viewPetProfiles
    Created on : Mar 12, 2024, 1:11:14 AM
    Author     : Sheerwin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Pet Profiles</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Pet Profiles</h2>
    <div class="mb-4">
        <a href="AddPetProfile" class="btn btn-primary">Add Pet Profile</a>
        <a href="receiptionistDashboard.jsp" class="btn btn-secondary ml-2">Back to Dashboard</a>
    </div>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Species</th>
                <th>Owner</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${pets}" var="pet">
                <tr>
                    <td>${pet.petId}</td>
                    <td>${pet.name}</td>
                    <td>${pet.species}</td>
                    <td>${pet.customerId.name}</td>
                    <td>
                        <a href="EditPetProfile?petId=${pet.petId}" class="btn btn-primary">Edit</a>
                        <a href="DeletePet?petId=${pet.petId}" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this pet profile?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
