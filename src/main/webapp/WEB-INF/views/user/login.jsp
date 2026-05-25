<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageTitle", "Sign In");
%>

<%@ include file="../partials/header.jsp" %>
<%@ include file="../partials/navbar.jsp" %>

<main class="container py-5">
    <div class="row justify-content-center align-items-center">
        <div class="col-lg-5">
            <div class="card border-0 shadow-lg">
                <div class="card-body p-5">
                    <div class="text-center mb-5">
                        <i class="bi bi-controller display-3 text-warning"></i>
                        <h1 class="fw-bold mt-3">Welcome Back</h1>
                        <p class="text-secondary fs-5">Sign in to continue your ToyVerse experience.</p>
                    </div>

                    <form method="post" action="${pageContext.request.contextPath}/user">
                        <input type="hidden" name="action" value="login">

                        <div class="mb-4">
                            <label class="form-label">Username</label>
                            <input type="text" class="form-control form-control-lg" name="username" placeholder="Enter username" required>
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Password</label>
                            <input type="password" class="form-control form-control-lg" name="password" placeholder="Enter password" required>
                        </div>

                        <button type="submit" class="btn btn-warning btn-lg w-100">Sign In</button>
                    </form>

                    <div class="text-center mt-4">
                        <p class="text-secondary mb-0">Don't have an account?</p>
                        <a href="${pageContext.request.contextPath}/user?action=registerPage" class="fw-bold text-warning text-decoration-none">
                            Create Account
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<%@ include file="../partials/footer.jsp" %>