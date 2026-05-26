<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageTitle", "Register");
%>

<%@ include file="../partials/header.jsp" %>
<%@ include file="../partials/navbar.jsp" %>

<main class="container py-5">
    <div class="row justify-content-center align-items-center">
        <div class="col-lg-6">
            <div class="card border-0 shadow-lg">
                <div class="card-body p-5">
                    <div class="text-center mb-5">
                        <i class="bi bi-stars display-3 text-warning"></i>
                        <h1 class="fw-bold mt-3">Join ToyVerse</h1>
                        <p class="text-secondary fs-5">
                            Create your account and start exploring the world of imagination.
                        </p>
                    </div>

                    <form method="post" action="${pageContext.request.contextPath}/user">
                        <input type="hidden" name="action" value="register">

                        <div class="mb-4">
                            <label class="form-label">Username</label>
                            <input type="text" class="form-control form-control-lg" name="username" placeholder="Choose a username" required>
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Email Address</label>
                            <input type="email" class="form-control form-control-lg" name="email" placeholder="Enter your email" required>
                        </div>

                        <div class="mb-5">
                            <label class="form-label">Password</label>
                            <input type="password" class="form-control form-control-lg" name="password" placeholder="Create a password" required>
                        </div>

                        <button type="submit" class="btn btn-warning btn-lg w-100">Create Account</button>
                    </form>

                    <div class="text-center mt-4">
                        <p class="text-secondary mb-0">Already have an account?</p>
                        <a href="${pageContext.request.contextPath}/user?action=loginPage" class="fw-bold text-warning text-decoration-none">
                            Sign In
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<%@ include file="../partials/footer.jsp" %>