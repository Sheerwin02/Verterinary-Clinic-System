<%-- 
    Document   : managingStaffDashboard
    Created on : Mar 10, 2024, 6:27:23 PM
    Author     : Sheerwin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Managing Staff Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Managing Staff Dashboard</h1>
    <h2>Welcome, ${user.name}!</h2>

    <div class="row">
        <!-- Profile Management Section -->
        <div class="col-md-4 dashboard-section">
            <div class="dashboard-card">
                <h2>Profile Management</h2>
                <a href="editProfile.jsp" class="btn btn-secondary">Edit Profile</a>
            </div>
        </div>

        <!-- Managing Staff Management Section -->
        <div class="col-md-4 dashboard-section">
            <div class="dashboard-card">
                <h2>Manage All Staff</h2>
                <a href="addStaff.jsp" class="btn btn-primary">Add New Staff</a>
                <a href="ViewStaff" class="btn btn-info">View & Manage Staff</a>
            </div>
        </div>

        <!-- Work Rota Management Section -->
        <div class="col-md-4 dashboard-section">
            <div class="dashboard-card">
                <h2>Work Rota</h2>
                <a href="PrepareWeeklyRota" class="btn btn-success">Create Work Rota</a>
                <a href="ViewRotas" class="btn btn-info">View Rotas</a>
            </div>
        </div>
    </div>

    <!-- Reporting Section -->
    <div class="dashboard-section">
        <div class="dashboard-card">
            <h2>Reports</h2>
            <a href="AppointmentsReport" class="btn btn-warning">Generate Appointment Reports</a>
        </div>
    </div>

    <!-- Logout Button -->
    <div class="mt-4">
        <a href="login.jsp" class="btn btn-secondary">Logout</a>
    </div>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
