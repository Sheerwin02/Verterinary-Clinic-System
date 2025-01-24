<%-- 
    Document   : viewAppointments
    Created on : Mar 11, 2024, 10:36:22 PM
    Author     : Sheerwin
--%>

<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Appointments</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1>Appointments</h1>
    <!-- Add Appointment Button -->
    <div class="mb-3">
        <a href="PrepareAddAppointment" class="btn btn-success mb-3">Add New Appointment</a>
    </div>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Appointment ID</th>
                <th>Vet Name</th>
                <th>Pet Name</th>
                <th>Date/Time</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${appointments}" var="appointment">
                <tr>
                    <td>${appointment.appointmentId}</td>
                    <td>${appointment.vetId.name}</td>
                    <td>${appointment.petId.name}</td>
                    <td><fmt:formatDate value="${appointment.dateTime}" pattern="dd MMM yyyy HH:mm" /></td>
                    <td>${appointment.status}</td>
                    <td>
                        <a href="EditAppointment?appointmentId=${appointment.appointmentId}" class="btn btn-sm btn-primary">Edit</a>
                        <c:if test="${appointment.status == 'Scheduled'}">
                            <a href="CompleteAppointment?appointmentId=${appointment.appointmentId}" class="btn btn-sm btn-success">Complete</a>
                            <a href="CancelAppointment?appointmentId=${appointment.appointmentId}" class="btn btn-sm btn-warning">Cancel</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="receiptionistDashboard.jsp" class="btn btn-secondary">Back to Receptionist Dashboard</a>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
