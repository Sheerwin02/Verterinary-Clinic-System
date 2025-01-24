<%-- 
    Document   : viewPrognosis
    Created on : Mar 13, 2024, 11:13:26 AM
    Author     : Sheerwin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Prognosis</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1>Prognosis Details</h1>
    <c:choose>
        <c:when test="${not empty prognosis}">
            <p><strong>Diagnosis ID:</strong> ${prognosis.diagnosisId.diagnosisId}</p>
            <p><strong>Prognosis:</strong> ${prognosis.prognosis}</p>
            <p><strong>Follow-up Required:</strong> ${prognosis.followedUpRequired ? 'Yes' : 'No'}</p>
            <p><strong>Created At:</strong> ${prognosis.createdAt}</p>
            <!-- Edit button -->
            <a href="EditPrognosis?prognosisId=${prognosis.prognosisId}" class="btn btn-primary mt-3">Edit Prognosis</a>
        </c:when>
        <c:otherwise>
            <p>${errorMessage}</p>
        </c:otherwise>
    </c:choose>
    <a href="ViewDiagnosis" class="btn btn-secondary mt-3">Back to Diagnoses List</a>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
