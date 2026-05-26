<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageTitle", "Order Confirmed");
%>

<%@ include file="../partials/header.jsp" %>
<%@ include file="../partials/navbar.jsp" %>

<main class="container py-5 text-center">
    <div class="card border-0 shadow-lg p-5">
        <div class="card-body">
            <i class="bi bi-check-circle-fill text-success display-1"></i>

            <h1 class="fw-bold mt-4">Order Confirmed!</h1>

            <p class="fs-5 text-secondary mt-3">
                Thank you for shopping with ToyVerse.
            </p>

            <a href="${pageContext.request.contextPath}/order?action=view" class="btn btn-warning btn-lg mt-4">
                View Orders
            </a>
        </div>
    </div>
</main>

<%@ include file="../partials/footer.jsp" %>
