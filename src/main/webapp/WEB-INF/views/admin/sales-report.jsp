<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageTitle", "Sales Report");
    request.setAttribute("isAdminPage", true);
%>

<%@ include file="../partials/header.jsp" %>
<%@ include file="../partials/navbar.jsp" %>

<main class="container py-5">
    <h1 class="fw-bold text-warning mb-5">Sales Report</h1>

    <div class="row g-4">
        <div class="col-md-6">
            <div class="card bg-dark text-white border-0 shadow-lg">
                <div class="card-body p-5">
                    <h3 class="text-secondary">Total Revenue</h3>
                    <h1 class="fw-bold text-warning">$${estimatedRevenue}</h1>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="card bg-dark text-white border-0 shadow-lg">
                <div class="card-body p-5">
                    <h3 class="text-secondary">Orders Processed</h3>
                    <h1 class="fw-bold text-warning">${totalOrders}</h1>
                </div>
            </div>
        </div>
    </div>
</main>

<%@ include file="../partials/footer.jsp" %>