<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Weekly Rota</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/rotas.css"> 
</head>
<body>
<div class="container mt-5">
    <h2>Weekly Rota for Vets</h2>
    <%-- Success message display --%>
    <c:if test="${param.rotaSuccess == 'true'}">
        <div class="alert alert-success">
            The weekly rota was successfully submitted.
        </div>
    </c:if>
    <form action="ProcessWeeklyRota" method="POST">
        <div class="form-group">
            <label for="weekOf">Select the week:</label>
            <input type="week" class="form-control" id="weekOf" name="weekOf" required>
        </div>
        <%-- Dynamic day and shift selection --%>
        <c:forEach items="${daysOfWeek}" var="day" varStatus="dayIndex">
            <h3>${day}</h3>
            <c:forEach items="${shifts}" var="shift" varStatus="shiftIndex">
                <div class="form-group">
                    <label for="${day}${shift}">${shift} Shift:</label>
                    <select class="form-control" id="${day}${shift}" name="vetFor${dayIndex.index}${shiftIndex.index}">
                        <option value="">Select a Vet</option>
                        <c:forEach items="${vets}" var="vet">
                            <option value="${vet.userId}">${vet.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:forEach>
        </c:forEach>
        <button type="submit" class="btn btn-primary">Submit Rota</button>
    </form>
    <a href="managingStaffDashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
    <a href="ViewRotas" class="btn btn-info mt-3">View Rotas</a>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
