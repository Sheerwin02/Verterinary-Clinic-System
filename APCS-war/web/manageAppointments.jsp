<%-- 
    Document   : manageAppointments
    Created on : Mar 11, 2024, 2:56:23 PM
    Author     : Sheerwin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Appointments</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1>Manage Appointments</h1>
    <div class="mb-3">
        <a href="PrepareAddAppointment" class="btn btn-success">Add Appointment</a>
    </div>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>Appointment ID</th>
                <th>Vet</th>
                <th>Pet</th>
                <th>Date/Time</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="appointment" items="${appointments}">
                <tr>
                    <td>${appointment.appointmentId}</td>
                    <td>${appointment.vetId.name}</td>
                    <td>${appointment.petId.name}</td>
                    <td>${appointment.dateTime}</td>
                    <td>${appointment.status}</td>
                    <td>
                        <a href="editAppointment?appointmentId=${appointment.appointmentId}" class="btn btn-primary btn-sm">Edit</a>
                        <a href="cancelAppointment?appointmentId=${appointment.appointmentId}" class="btn btn-danger btn-sm">Cancel</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
