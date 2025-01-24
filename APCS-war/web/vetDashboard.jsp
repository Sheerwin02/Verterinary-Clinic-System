<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Vet Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Link to the external CSS file -->
    <link rel="stylesheet" href="css/vet-dashboard.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Vet Dashboard</h1>
        <h2>Welcome, ${user.name}!</h2>
    </div>
    
    <div class="today-shift">
        <h3>Today's Shift</h3>
        <c:choose>
            <c:when test="${empty todayRotas}">
                <p>You have no shifts today.</p>
            </c:when>
            <c:otherwise>
                <c:forEach items="${todayRotas}" var="rota">
                    <p>${rota.shift} shift</p>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="weekly-shift">
        <a href="ViewWeeklyRotas?vetId=${user.userId}" class="btn btn-primary">View Weekly Shift</a>
    </div>

    
    <div class="section">
        <h3>Profile Management</h3>
        <a href="editProfile.jsp" class="btn btn-info btn-custom">Edit Your Profile</a>
    </div>
    
    <div class="section">
        <h3>Your Appointments</h3>
        <a href="VetAppointments" class="btn btn-success btn-custom">View Appointments</a>
    </div>
    
    <div class="section">
        <h3>Diagnosis and Prognosis</h3>
        <a href="ViewDiagnosis" class="btn btn-primary btn-custom">View Diagnosis</a>
    </div>
    
    <div class="section">
        <a href="LogOut" class="btn btn-warning logout-btn">Log Out</a>
    </div>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
