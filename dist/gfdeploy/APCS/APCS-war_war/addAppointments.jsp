<%-- 
    Document   : addAppointments
    Created on : Mar 11, 2024, 3:40:25 PM
    Author     : Sheerwin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Appointment</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<div class="container mt-5">
    <h2>Add New Appointment</h2>
    <form action="AddAppointment" method="POST">
        <div class="mb-3">
            <label for="vetId" class="form-label">Vet:</label>
            <select id="vetId" name="vetId" class="form-select" onchange="getVetExpertise(this.value)">
                <option value="">Select a Vet</option>
                <c:forEach items="${vets}" var="vet">
                    <option value="${vet.userId}">${vet.name}</option>
                </c:forEach>
            </select>
            <div id="vetExpertise"></div>
        </div>
        <div class="mb-3">
            <label for="petId" class="form-label">Pet:</label>
            <select id="petId" name="petId" class="form-select" onchange="getPetSpecies(this.value)">
                <option value="">Select a Pet</option>
                <c:forEach items="${pets}" var="pet">
                    <option value="${pet.petId}">${pet.name}</option>
                </c:forEach>
            </select>
            <div id="petSpecies"></div>
        </div>
        <div class="mb-3">
            <label for="dateTime" class="form-label">Date and Time:</label>
            <input type="datetime-local" id="dateTime" name="dateTime" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="status" class="form-label">Status:</label>
            <select id="status" name="status" class="form-select">
                <option value="Scheduled">Scheduled</option>
                <option value="Completed">Completed</option>
                <option value="Cancelled">Canceled</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Add Appointment</button>
    </form>
    <a href="ViewAppointments" class="btn btn-info mt-3">View Appointments</a>
</div>
<script>
function getVetExpertise(vetId) {
    if (vetId) {
        $.get('GetVetExpertise', {vetId: vetId}, function(response) {
            $('#vetExpertise').html('Expertise: ' + response.experties);
        });
    } else {
        $('#vetExpertise').html('');
    }
}

function getPetSpecies(petId) {
    if (petId) {
        $.get('GetPetSpecies', {petId: petId}, function(response) {
            $('#petSpecies').html('Species: ' + response.species);
        });
    } else {
        $('#petSpecies').html('');
    }
}
</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>

