<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageTitle", "My Profile");
%>

<%@ include file="../partials/header.jsp" %>
<%@ include file="../partials/navbar.jsp" %>

<main class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="card border-0 shadow-lg">
                <div class="card-body p-5">
                    <!-- HEADER -->
                    <div class="text-center mb-5">
                        <i class="bi bi-person-circle display-1 text-warning"></i>
                        <h1 class="fw-bold mt-3">Welcome Back, <%= session.getAttribute("loggedInUser") %></h1>
                        <p class="text-secondary fs-5">Manage your ToyVerse account and track your orders.</p>
                    </div>

                    <!-- ACCOUNT OPTIONS -->
                    <div class="row g-4">
                        <!-- ORDERS -->
                        <div class="col-md-6">
                            <div class="card bg-dark text-white border-0 h-100">
                                <div class="card-body p-4">
                                    <h3 class="fw-bold mb-3">My Orders</h3>
                                    <p class="text-secondary">View your purchases and order history.</p>
                                    <a href="${pageContext.request.contextPath}/order?action=view" class="btn btn-warning">View Orders</a>
                                </div>
                            </div>
                        </div>

                        <!-- STORE -->
                        <div class="col-md-6">
                            <div class="card bg-dark text-white border-0 h-100">
                                <div class="card-body p-4">
                                    <h3 class="fw-bold mb-3">Continue Shopping</h3>
                                    <p class="text-secondary">Explore the latest additions to ToyVerse.</p>
                                    <a href="${pageContext.request.contextPath}/toy?action=view" class="btn btn-warning">Browse Store</a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- LOGOUT -->
                    <div class="text-center mt-5">
                        <a href="${pageContext.request.contextPath}/user?action=logout" class="btn btn-outline-danger btn-lg">Logout</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<%@ include file="../partials/footer.jsp" %>