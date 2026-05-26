<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/views/partials/header.jsp">
    <jsp:param name="pageTitle" value="User Management" />
</jsp:include>

<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<main class="container py-5">

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1 class="fw-bold">Registered Users</h1>
        <span class="badge bg-warning text-dark fs-6">
            ${userList.size()} Users
        </span>
    </div>

    <div class="card shadow-lg border-0">
        <div class="card-body p-0">
            <table class="table table-hover align-middle mb-0">
                <thead class="table-dark">
                <tr>
                    <th>User ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Role</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <td class="text-secondary fw-bold">
                                ${user.userId}
                        </td>
                        <td>
                                ${user.name}
                        </td>
                        <td>
                                ${user.email}
                        </td>
                        <td>
                                <span class="badge ${user.role == 'ADMIN' ? 'bg-danger' : 'bg-primary'}">
                                        ${user.role}
                                </span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</main>

<jsp:include page="/WEB-INF/views/partials/footer.jsp" />