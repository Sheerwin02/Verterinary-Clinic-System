<%-- 
    Document   : editPrognosis
    Created on : Mar 13, 2024, 11:50:39 AM
    Author     : Sheerwin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Prognosis</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Edit Prognosis</h2>
    <form action="EditPrognosis" method="POST">
        <input type="hidden" name="prognosisId" value="${prognosis.prognosisId}"/>
        
        <div class="mb-3">
            <label for="prognosis" class="form-label">Prognosis:</label>
            <textarea id="prognosis" name="prognosis" class="form-control" rows="3" required>${prognosis.prognosis}</textarea>
        </div>
        
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="followedUpRequired" name="followedUpRequired" ${prognosis.followedUpRequired ? 'checked' : ''}>
            <label class="form-check-label" for="followedUpRequired">Follow-up Required?</label>
        </div>
        
        <button type="submit" class="btn btn-primary">Update Prognosis</button>
        <a href="viewDiagnosis.jsp" class="btn btn-secondary">Cancel</a>
    </form>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>

