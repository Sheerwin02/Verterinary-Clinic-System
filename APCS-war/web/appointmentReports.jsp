<%-- 
    Document   : appointmentReports
    Created on : Mar 13, 2024, 1:32:25 PM
    Author     : Sheerwin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Appointments Report</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

    </head>
    <body>
        <div class="container mt-5">
            <h1>Appointments Report</h1>

            <!-- Daily Appointments Summary -->
            <h2>Today's Appointments</h2>
            <c:if test="${not empty dailyAppointments}">
                <p>Total Daily Appointments: ${fn:length(dailyAppointments)}</p>
            </c:if>
            <c:choose>
                <c:when test="${not empty dailyAppointments}">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Appointment ID</th>
                                <th>Pet Name</th>
                                <th>Vet Assigned</th>
                                <th>Time Slot</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${dailyAppointments}" var="appointment">
                                <tr>
                                    <td>${appointment.appointmentId}</td>
                                    <td>${appointment.petId.name}</td>
                                    <td>${appointment.vetId.name}</td>
                                    <td><fmt:formatDate value="${appointment.dateTime}" pattern="HH:mm"/></td>
                                    <td>${appointment.status}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>There are no appointments scheduled for today.</p>
                </c:otherwise>
            </c:choose>
                    
            <div class="chart-container" style="position: relative; height:40vh; width:80vw">
                <canvas id="dailyAppointmentsChart"></canvas>
            </div>
                    
            <!-- Monthly Appointments Overview -->
            <h2>Monthly Appointments Overview</h2>
            <c:set var="totalMonthlyAppointments" value="0"/>
            <c:forEach items="${monthlyData}" var="data">
                <c:set var="totalMonthlyAppointments" value="${totalMonthlyAppointments + data[1]}"/>
            </c:forEach>
            <p>Total Monthly Appointments: ${totalMonthlyAppointments}</p>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Total Appointments</th>
                        <th>Completed</th>
                        <th>Canceled</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${monthlyData}" var="data">
                        <tr>
                            <td><fmt:formatDate value="${data[0]}" pattern="yyyy-MM-dd"/></td>
                            <td>${data[1]}</td>
                            <td>${data[2]}</td>
                            <td>${data[3]}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Charts for Visual Representation -->
            <div class="chart-container" style="position: relative; height:40vh; width:80vw">
                <canvas id="monthlyAppointmentsChart"></canvas>
            </div>

            <!-- Back to Managing Staff Dashboard Button -->
            <a href="managingStaffDashboard.jsp" class="btn btn-secondary mt-3">Back to Managing Staff Dashboard</a>
            
            <div class="download-buttons mt-3">
                <button id="downloadDailyChart" class="btn btn-success">Download Daily Chart <i class="fas fa-download"></i></button>
                <button id="downloadMonthlyChart" class="btn btn-info">Download Monthly Chart <i class="fas fa-download"></i></button>
            </div>

        </div>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
        <script>
            // Prepare daily appointments data for the chart
            var dailyAppointmentsLabels = [];
            var dailyAppointmentsCounts = {'Scheduled': 0, 'Completed': 0, 'Cancelled': 0};

            <c:forEach items="${dailyAppointments}" var="appointment">
                var status = "${appointment.status}";
                if (!dailyAppointmentsLabels.includes(status)) {
                    dailyAppointmentsLabels.push(status);
                }
                dailyAppointmentsCounts[status]++;
            </c:forEach>

            var dailyAppointmentsData = {
                labels: dailyAppointmentsLabels,
                datasets: [{
                    label: 'Status',
                    data: Object.values(dailyAppointmentsCounts),
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)', // Scheduled
                        'rgba(54, 162, 235, 0.2)', // Completed
                        'rgba(255, 206, 86, 0.2)'  // Cancelled
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)'
                    ],
                    borderWidth: 1
                }]
            };

            // Initialization of counters for the whole month
            var totalMonthlyAppointments = 0;
            var monthlyCompletedAppointments = 0;
            var monthlyCancelledAppointments = 0;

            // Iteration over the fetched monthly data to aggregate it
            <c:forEach items="${monthlyData}" var="data">
                totalMonthlyAppointments += ${data[1]};
                monthlyCompletedAppointments += ${data[2]};
                monthlyCancelledAppointments += ${data[3]};
            </c:forEach>

            // Data for the chart
            var monthlyOverviewData = {
                labels: ["Total Appointments", "Completed", "Cancelled"],
                datasets: [{
                    label: 'Monthly Overview',
                    data: [totalMonthlyAppointments, monthlyCompletedAppointments, monthlyCancelledAppointments],
                    backgroundColor: [
                        'rgba(153, 102, 255, 0.2)', // Total
                        'rgba(75, 192, 192, 0.2)',  // Completed
                        'rgba(255, 99, 132, 0.2)'   // Cancelled
                    ],
                    borderColor: [
                        'rgba(153, 102, 255, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(255, 99, 132, 1)'
                    ],
                    borderWidth: 1
                }]
            };
         

            var ctxDaily = document.getElementById('dailyAppointmentsChart').getContext('2d');
            var dailyAppointmentsChart = new Chart(ctxDaily, {
                type: 'pie',
                data: dailyAppointmentsData,
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top',
                        },
                        title: {
                            display: true,
                            text: 'Daily Appointments Breakdown'
                        }
                    }
                },
            });

            // Creating the chart
            var ctxMonthly = document.getElementById('monthlyAppointmentsChart').getContext('2d');
            var monthlyAppointmentsChart = new Chart(ctxMonthly, {
                type: 'doughnut',
                data: monthlyOverviewData,
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top',
                        },
                        title: {
                            display: true,
                            text: 'Monthly Appointments Overview'
                        }
                    }
                }
            });
            
            
        </script>
        <script>
        document.getElementById('downloadMonthlyChart').addEventListener('click', function() {
            var canvas = document.getElementById('monthlyAppointmentsChart');
            var dataURL = canvas.toDataURL('image/png');
            downloadImage(dataURL, 'monthly-appointments-chart.png');
        });

        document.getElementById('downloadDailyChart').addEventListener('click', function() {
            var canvas = document.getElementById('dailyAppointmentsChart');
            var dataURL = canvas.toDataURL('image/png');
            downloadImage(dataURL, 'daily-appointments-chart.png');
        });

        function downloadImage(dataURL, filename) {
            var a = document.createElement('a');
            a.href = dataURL;
            a.download = filename;
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
        }
        </script>
    </body>
</html>
