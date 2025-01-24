<%-- 
    Document   : editPetProfile
    Created on : Mar 14, 2024, 2:18:06 PM
    Author     : Sheerwin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Pet Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Edit Pet Profile</h2>
    <form action="EditPetProfile" method="post">
        <input type="hidden" name="petId" value="${pet.petId}"/>
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" class="form-control" value="${pet.name}" required>
        </div>
        <div class="form-group">
            <label for="species">Species:</label>
            <input type="text" id="species" name="species" class="form-control" value="${pet.species}" required>
        </div>
        <!-- Include additional fields as necessary -->
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Update Pet Profile</button>
            <a href="ViewPetProfiles" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
