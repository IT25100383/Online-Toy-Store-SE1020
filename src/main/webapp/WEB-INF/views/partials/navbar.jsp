<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Boolean adminPageFlag =
            (Boolean) request.getAttribute("isAdminPage");

    boolean isAdminNav =
            adminPageFlag != null && adminPageFlag;

    String navBackground;

    if (isAdminNav) {
        navBackground = "bg-dark navbar-dark";
    } else {
        navBackground = "bg-warning navbar-light";
    }
%>
<nav class="navbar navbar-expand-lg <%= navBackground %> shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/">Toy Store</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarContent">
            <ul class="navbar-nav ms-auto align-items-center">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/toy?action=view">Toys</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/order?action=view">Orders</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/review?action=view">Reviews</a>
                </li>

                <% if (session.getAttribute("loggedInUser") == null) { %>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user?action=loginPage">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user?action=registerPage">Register</a>
                </li>
                <% } else { %>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user?action=profile">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-danger" href="${pageContext.request.contextPath}/user?action=logout">Logout</a>
                </li>
                <% } %>

                <%
                    com.toystore.user.model.User user = (com.toystore.user.model.User) session.getAttribute("loggedInUser");
                    if (user != null && "ADMIN".equalsIgnoreCase(user.getRole())) {
                %>
                <li class="nav-item">
                    <a class="nav-link fw-bold" href="${pageContext.request.contextPath}/admin?action=dashboard">Dashboard</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link fw-bold" href="${pageContext.request.contextPath}/admin?action=viewAdmins">Manage Admins</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link fw-bold" href="${pageContext.request.contextPath}/user?action=view">Manage Users</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link fw-bold" href="${pageContext.request.contextPath}/category?action=view">Categories</a>
                </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>