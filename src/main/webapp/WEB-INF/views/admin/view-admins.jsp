<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Admins</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="fw-bold">Admin Management</h2>
        <a href="${pageContext.request.contextPath}/admin?action=addPage" class="btn btn-primary">Add Admin</a>
    </div>

    <div class="card shadow-sm border-0">
        <div class="card-body">
            <table class="table table-hover align-middle">
                <thead class="table-dark">
                <tr>
                    <th>Username</th>
                    <th>Role</th>
                    <th class="text-center">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="admin" items="${adminList}">
                    <tr>
                        <td>${admin.username}</td>
                        <td>${admin.role}</td>
                        <td class="text-center">
                            <a href="${pageContext.request.contextPath}/admin?action=editPage&username=${admin.username}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="${pageContext.request.contextPath}/admin?action=delete&username=${admin.username}" class="btn btn-danger btn-sm" onclick="return confirm('Delete this admin?');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>