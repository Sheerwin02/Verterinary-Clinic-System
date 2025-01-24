<%-- 
    Document   : viewDiagnosis
    Created on : Mar 11, 2024, 9:57:33 PM
    Author     : Sheerwin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Diagnoses</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1>Diagnoses</h1>
    <!-- Success message display -->
    <c:if test="${not empty param.successMessage}">
        <div class="alert alert-success" role="alert">
            ${param.successMessage}
        </div>
    </c:if>
    <a href="EnterDiagnosis" class="btn btn-primary mb-3">Add New Diagnosis</a>
    <!-- Back to Vet Dashboard Button -->
    <a href="vetDashboard.jsp" class="btn btn-secondary mb-3">Back to Vet Dashboard</a>
    <table class="table">
        <thead>
            <tr>
                <th>Diagnosis ID</th>
                <th>Pet Name</th>
                <th>Diagnosis</th>
                <th>Date</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${diagnoses}" var="diagnosis">
            <tr>
                <td>${diagnosis.diagnosisId}</td>
                <td>${diagnosis.appointmentId.petId.name}</td>
                <td>${diagnosis.diagnosis}</td>
                <td>${diagnosis.createdAt}</td>
                <td>
                    <c:if test="${prognosisExistsMap[diagnosis.diagnosisId]}">
                        <a href="ViewPrognosis?diagnosisId=${diagnosis.diagnosisId}" class="btn btn-sm btn-success">View Prognosis</a>
                    </c:if>
                    <c:if test="${not prognosisExistsMap[diagnosis.diagnosisId]}">
                        <a href="EnterPrognosis?diagnosisId=${diagnosis.diagnosisId}" class="btn btn-sm btn-info">Enter Prognosis</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
