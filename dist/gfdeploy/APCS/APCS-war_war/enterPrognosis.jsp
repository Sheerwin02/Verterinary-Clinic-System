<%-- 
    Document   : enterPrognosis
    Created on : Mar 13, 2024, 1:28:56 AM
    Author     : Sheerwin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Enter Prognosis</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Enter Prognosis</h2>
    <form action="EnterPrognosis" method="POST">
        <input type="hidden" name="diagnosisId" value="${param.diagnosisId}"/>
        <div class="mb-3">
            <label for="prognosis" class="form-label">Prognosis:</label>
            <textarea id="prognosis" name="prognosis" class="form-control" rows="3" required></textarea>
        </div>
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="followedUpRequired" name="followedUpRequired">
            <label class="form-check-label" for="followedUpRequired">Follow-up Required?</label>
        </div>
        <button type="submit" class="btn btn-primary">Submit Prognosis</button>
    </form>
    <a href="ViewDiagnosis" class="btn btn-secondary mt-3">Back to Diagnoses List</a>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>