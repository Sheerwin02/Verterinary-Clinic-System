<%-- 
    Document   : vetAppointments
    Created on : Mar 13, 2024, 12:09:42 AM
    Author     : Sheerwin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Appointments</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1>Your Appointments</h1>
    
    <c:if test="${not empty param.success}">
        <div class="alert alert-success" role="alert">
            Appointment marked as completed successfully.
        </div>
    </c:if>
    
    <table class="table">
        <thead>
            <tr>
                <th>Appointment ID</th>
                <th>Pet Name</th>
                <th>Date/Time</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${appointments}" var="appointment">
                <tr>
                    <td>${appointment.appointmentId}</td>
                    <td>${appointment.petId.name}</td>
                    <td>${appointment.dateTime}</td>
                    <td>${appointment.status}</td>
                    <td>
                        <c:choose>
                            <c:when test="${appointment.status == 'Scheduled'}">
                                <a href="UpdateAppointmentStatus?appointmentId=${appointment.appointmentId}&newStatus=Completed" class="btn btn-sm btn-success">Mark as Completed</a>
                            </c:when>
                            <c:otherwise>
                                No further action required
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="vetDashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
