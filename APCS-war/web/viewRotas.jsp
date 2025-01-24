<%-- 
    Document   : viewRotas
    Created on : Mar 12, 2024, 5:10:45 PM
    Author     : Sheerwin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Rotas</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Weekly Rotas</h2>
    <!-- Filter Form -->
    <form class="mb-4" action="ViewRotas" method="GET">
        <div class="form-row align-items-center">
            <div class="col-auto">
                <label for="weekOf">Week Of:</label>
                <input type="week" class="form-control mb-2" id="weekOf" name="weekOf">
            </div>
            <div class="col-auto">
                <label for="searchTerm">Search Vet:</label>
                <input type="text" class="form-control mb-2" id="searchTerm" name="searchTerm" placeholder="Vet Name">
            </div>
            <div class="col-auto">
                <label for="shiftFilter">Shift:</label>
                <select class="form-control mb-2" id="shiftFilter" name="shiftFilter">
                    <option value="">Any Shift</option>
                    <option value="Morning">Morning</option>
                    <option value="Afternoon">Afternoon</option>
                </select>
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-primary mb-2">Apply Filter</button>
                <a href="ViewRotas" class="btn btn-secondary mb-2">Clear Filter</a>
            </div>
        </div>
    </form>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Vet Name</th>
                <th>Date</th>
                <th>Shift</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${rotas}" var="rota">
                <tr>
                    <td>${rota.vetId.name}</td>
                    <td><fmt:formatDate value="${rota.dateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>${rota.shift}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="d-flex justify-content-between">
        <a href="PrepareWeeklyRota" class="btn btn-primary">Back to Weekly Rota</a>
        <a href="managingStaffDashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
    </div>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
</body>
</html>
