<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Boolean adminPageFlag = (Boolean) request.getAttribute("isAdminPage");
    boolean isAdminNav = adminPageFlag != null && adminPageFlag;

    // Cast session attribute to User model
    com.toystore.user.model.User loggedInUser = (com.toystore.user.model.User) session.getAttribute("loggedInUser");

    String navBackground;
    if (isAdminNav) {
        navBackground = "linear-gradient(90deg,#111827,#1f2937)";
    } else {
        navBackground = "linear-gradient(90deg,#ff6b35,#f7b731)";
    }
%>

<nav class="navbar navbar-expand-lg navbar-dark sticky-top shadow-sm py-3" style="background: <%= navBackground %>;">
    <div class="container">
        <a class="navbar-brand fw-bold d-flex align-items-center gap-2" href="${pageContext.request.contextPath}/">
            <i class="bi bi-controller"></i>
            <span>
                <% if (isAdminNav) { %>
                    ToyVerse Admin
                <% } else { %>
                    ToyVerse
                <% } %>
            </span>
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="mainNavbar">
            <ul class="navbar-nav ms-auto align-items-lg-center gap-lg-2">
                <% if (!isAdminNav) { %>
                <li class="nav-item"><a class="nav-link text-dark fw-semibold" href="${pageContext.request.contextPath}/">Home</a></li>
                <li class="nav-item"><a class="nav-link text-dark fw-semibold" href="${pageContext.request.contextPath}/toy?action=view">Browse Store</a></li>
                <li class="nav-item"><a class="nav-link text-dark fw-semibold" href="${pageContext.request.contextPath}/review?action=view">Reviews</a></li>

                <% if (loggedInUser == null) { %>
                <li class="nav-item ms-lg-3"><a class="btn btn-dark rounded-pill px-4" href="${pageContext.request.contextPath}/user?action=loginPage">Sign In</a></li>
                <li class="nav-item"><a class="btn btn-outline-dark rounded-pill px-4" href="${pageContext.request.contextPath}/user?action=registerPage">Register</a></li>
                <% } else { %>
                <li class="nav-item"><span class="nav-link fw-bold text-dark">Welcome, <%= loggedInUser.getName() %></span></li>
                <li class="nav-item"><a class="nav-link text-dark fw-semibold" href="${pageContext.request.contextPath}/order?action=view">My Orders</a></li>
                <li class="nav-item"><a class="btn btn-dark rounded-pill px-4" href="${pageContext.request.contextPath}/user?action=logout">Logout</a></li>
                <% } %>

                <% } else { %>
                <li class="nav-item"><a class="nav-link text-white" href="${pageContext.request.contextPath}/admin">Dashboard</a></li>
                <li class="nav-item"><a class="nav-link text-white" href="${pageContext.request.contextPath}/toy?action=view">Inventory</a></li>
                <li class="nav-item"><a class="nav-link text-white" href="${pageContext.request.contextPath}/user?action=view">Users</a></li>
                <li class="nav-item"><a class="nav-link text-white" href="${pageContext.request.contextPath}/order?action=view">Orders</a></li>
                <li class="nav-item"><a class="nav-link text-white" href="${pageContext.request.contextPath}/review?action=view">Reviews</a></li>
                <li class="nav-item ms-lg-3"><a class="btn btn-warning rounded-pill px-4" href="${pageContext.request.contextPath}/">Back to Store</a></li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>