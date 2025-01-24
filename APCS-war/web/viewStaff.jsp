<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Staff Management</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script>
        function confirmDelete(userId) {
            if (confirm('Are you sure you want to delete this user?')) {
                window.location.href = 'DeleteStaff?userId=' + userId;
            }
        }
    </script>
</head>
<body>
<div class="container mt-5">
    <h2>Staff Management</h2>
    
    <!-- Alert Messages -->
    <c:if test="${not empty param.editSuccess || not empty param.deleteSuccess}">
        <div class="alert alert-success" role="alert">
            <c:if test="${param.editSuccess == 'true'}">Staff profile successfully updated.</c:if>
            <c:if test="${param.deleteSuccess == 'true'}">Staff profile successfully deleted.</c:if>
        </div>
    </c:if>
    
    <!-- Search and Filter Form -->
    <div class="mb-4">
        <form class="form-inline" action="ViewStaff" method="GET">
            <input type="text" class="form-control mr-2" name="searchQuery" placeholder="Search by username or name..." value="${param.searchQuery}">
            <select name="roleFilter" class="form-control mr-2">
                <option value="">All Roles</option>
                <option value="Managing Staff" ${param.roleFilter == 'Managing Staff' ? 'selected' : ''}>Managing Staff</option>
                <option value="Vet" ${param.roleFilter == 'Vet' ? 'selected' : ''}>Vet</option>
                <option value="Receptionist" ${param.roleFilter == 'Receptionist' ? 'selected' : ''}>Receptionist</option>
            </select>
            <button type="submit" class="btn btn-outline-primary">Search</button>
            <a href="ViewStaff" class="btn btn-outline-secondary ml-2">Clear</a>
        </form>
    </div>
    
    <!-- Pending Approval Section -->
    <c:if test="${empty param.searchQuery and empty param.roleFilter}">
        <div class="pending-approval-section mt-5">
            <h3>Pending Approval</h3>
            <c:if test="${empty pendingUsers}">
                <p>There's no pending users for approval.</p>
            </c:if>
            <c:if test="${not empty pendingUsers}">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Username</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${pendingUsers}" var="user">
                            <tr>
                                <td>${user.username}</td>
                                <td>${user.name}</td>
                                <td>${user.email}</td>
                                <td>${user.role}</td>
                                <td>
                                    <a href="ApproveStaff?userId=${user.userId}" class="btn btn-sm btn-success">Approve</a>
                                    <button type="button" class="btn btn-sm btn-danger" onclick="confirmDelete('${user.userId}')">Delete</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </c:if>
    
    <!-- Staff List Section -->
    <c:if test="${not empty staffList}">
        <!-- Dynamic Title for Staff List based on Role Filter -->
        <h4>
            <c:choose>
                <c:when test="${param.roleFilter == 'Managing Staff'}">Managing Staff List</c:when>
                <c:when test="${param.roleFilter == 'Vet'}">Vet List</c:when>
                <c:when test="${param.roleFilter == 'Receptionist'}">Receptionist List</c:when>
                <c:otherwise>Staff List</c:otherwise>
            </c:choose>
        </h4>
        <table class="table table-bordered">
            <thead class="thead-light">
                <tr>
                    <th>Username</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Address</th>
                    <th>Role</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${staffList}" var="user">
                    <c:if test="${user.status != 'Pending'}">
                        <tr>
                            <td>${user.username}</td>
                            <td>${user.name}</td>
                            <td>${user.email}</td>
                            <td>${user.phoneNumber}</td>
                            <td>${user.address}</td>
                            <td>${user.role}</td>
                            <td>${user.status}</td>
                            <td>
                                <div class="btn-group">
                                    <a href="EditStaff?userId=${user.userId}" class="btn btn-sm btn-info">Edit</a>
                                    <button type="button" class="btn btn-sm btn-danger" onclick="confirmDelete('${user.userId}')">Delete</button>
                                </div>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    
    <a href="managingStaffDashboard.jsp" class="btn btn-primary">Back to Dashboard</a>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
