<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageTitle", "Inventory Status");
    request.setAttribute("isAdminPage", true);
%>

<%@ include file="../partials/header.jsp" %>
<%@ include file="../partials/navbar.jsp" %>

<main class="container py-5">
    <h1 class="fw-bold text-warning mb-5">Inventory Status</h1>

    <div class="row g-4">
        <div class="col-md-6">
            <div class="card bg-dark text-white border-0 shadow-lg">
                <div class="card-body p-5">
                    <h3 class="text-secondary">Total Inventory Units</h3>
                    <h1 class="fw-bold text-warning">${totalToys}</h1>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="card bg-dark text-white border-0 shadow-lg">
                <div class="card-body p-5">
                    <h3 class="text-secondary">Low Stock Alerts</h3>
                    <h1 class="fw-bold text-warning">${lowStockItems}</h1>
                </div>
            </div>
        </div>
    </div>
</main>

<%@ include file="../partials/footer.jsp" %>