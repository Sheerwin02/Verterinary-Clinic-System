<%-- 
    Document   : viewWeeklyRotas
    Created on : Mar 14, 2024, 10:26:48 PM
    Author     : Sheerwin
--%>

<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Weekly Shift</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Weekly Shift</h2>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Date</th>
                <th>Shift</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${weeklyRotas}" var="rota">
                <tr>
                    <td><c:out value="${rota.dateTime}"/></td>
                    <td><c:out value="${rota.shift}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="ViewVetRotas" class="btn btn-secondary">Back</a>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>

