<%-- 
    Document   : enterDiagnosis
    Created on : Mar 11, 2024, 9:47:48 PM
    Author     : Sheerwin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Enter Diagnosis</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Enter Diagnosis for Appointment</h2>
    <form action="EnterDiagnosis" method="POST">
        <div class="mb-3">
            <label for="appointmentId" class="form-label">Appointment:</label>
            <select id="appointmentId" name="appointmentId" class="form-select" required>
                <c:forEach items="${appointments}" var="appointment">
                    <option value="${appointment.appointmentId}">Appointment ID: ${appointment.appointmentId} - Date/Time: ${appointment.dateTime}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="diagnosis" class="form-label">Diagnosis:</label>
            <textarea id="diagnosis" name="diagnosis" class="form-control" rows="3" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Submit Diagnosis</button>
        <!-- Back to Vet Dashboard Button -->
        <a href="ViewDiagnosis" class="btn btn-secondary">Back to Diagnosis Management</a>
    </form>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
