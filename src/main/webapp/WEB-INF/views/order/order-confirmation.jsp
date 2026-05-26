<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageTitle", "Order Confirmed");
%>

<%@ include file="../partials/header.jsp" %>
<%@ include file="../partials/navbar.jsp" %>

<main class="container py-5 text-center">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card border-0 shadow-lg p-5">
                <div class="card-body">
                    <i class="bi bi-check-circle-fill text-success display-1"></i>

                    <h1 class="fw-bold mt-4">Order Confirmed!</h1>

                    <p class="fs-5 text-secondary mt-3">
                        Thank you for shopping with ToyVerse. Your order has been successfully processed.
                    </p>

                    <div class="d-grid gap-2 d-sm-flex justify-content-sm-center mt-5">
                        <a href="${pageContext.request.contextPath}/order?action=view" class="btn btn-warning btn-lg px-4 fw-bold">
                            View Orders
                        </a>

                        <a href="${pageContext.request.contextPath}/review?action=addPage"
                           class="btn btn-outline-warning btn-lg px-4 fw-bold">
                            Leave Review
                        </a>
                    </div>

                    <div class="mt-4">
                        <a href="${pageContext.request.contextPath}/toy?action=view" class="text-decoration-none text-muted small">
                            <i class="bi bi-arrow-left me-1"></i> Continue Shopping
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<%@ include file="../partials/footer.jsp" %>