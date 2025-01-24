<%-- 
    Document   : receiptionistDashboard
    Created on : Mar 10, 2024, 6:30:04 PM
    Author     : Sheerwin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Receptionist Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/receptionist-dashboard.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Receptionist Dashboard</h1>
        <h2>Welcome, ${user.name}!</h2>
    </div>
    
    <div class="section">
        <h2>Profile Management</h2>
        <a href="editProfile.jsp" class="btn btn-info btn-custom">Edit Your Profile</a>
    </div>
    
    <div class="section">
        <h2>Appointments</h2>
        <a href="ViewAppointments" class="btn btn-primary btn-custom">Manage Appointments</a>
    </div>
    
    <div class="section">
        <h2>Customer and Pet Profiles</h2>
        <a href="AddCustomerProfile" class="btn btn-info btn-custom">Add New Customer Profile</a>
        <a href="AddPetProfile" class="btn btn-info btn-custom">Add New Pet Profile</a>
    </div>
    

    <div class="section">
        <a href="LogOut" class="btn btn-warning logout-btn">Log Out</a>
    </div>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
