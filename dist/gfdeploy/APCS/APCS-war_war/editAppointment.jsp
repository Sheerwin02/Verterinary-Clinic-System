<%-- 
    Document   : editAppointment
    Created on : Mar 12, 2024, 8:46:44 PM
    Author     : Sheerwin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Appointment</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Edit Appointment</h2>
    <form action="EditAppointment" method="POST">
        <input type="hidden" name="appointmentId" value="${appointment.appointmentId}" />

        <div class="form-group">
            <label for="vetId">Vet:</label>
            <select class="form-control" id="vetId" name="vetId">
                <c:forEach items="${vets}" var="vet">
                    <option value="${vet.userId}" ${appointment.vetId.userId == vet.userId ? 'selected' : ''}>${vet.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="petId">Pet:</label>
            <select class="form-control" id="petId" name="petId">
                <c:forEach items="${pets}" var="pet">
                    <option value="${pet.petId}" ${appointment.petId.petId == pet.petId ? 'selected' : ''}>${pet.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="dateTime">Date/Time:</label>
            <input type="datetime-local" class="form-control" id="dateTime" name="dateTime" value="<c:out value="${fn:substring(appointment.dateTime, 0, 16)}"/>">
        </div>

        <div class="form-group">
            <label for="status">Status:</label>
            <select class="form-control" id="status" name="status">
                <option value="Scheduled" ${appointment.status == 'Scheduled' ? 'selected' : ''}>Scheduled</option>
                <option value="Completed" ${appointment.status == 'Completed' ? 'selected' : ''}>Completed</option>
                <option value="Cancelled" ${appointment.status == 'Cancelled' ? 'selected' : ''}>Cancelled</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Update Appointment</button>
        <a href="ViewAppointments" class="btn btn-secondary">Cancel</a>
    </form>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
