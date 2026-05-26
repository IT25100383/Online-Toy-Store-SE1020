<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageTitle", "Edit Toy");
    request.setAttribute("isAdminPage", true);
%>

<%@ include file="../partials/header.jsp" %>
<%@ include file="../partials/navbar.jsp" %>

<main class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-7">
            <div class="card bg-dark text-white border-0 shadow-lg">
                <div class="card-body p-5">
                    <h1 class="fw-bold text-warning mb-4">Edit Inventory Item</h1>

                    <form method="post" action="${pageContext.request.contextPath}/toy">
                        <input type="hidden" name="action" value="update">

                        <div class="mb-4">
                            <label class="form-label">Toy ID</label>
                            <input type="text" class="form-control" name="toyId" value="${toyId}" readonly>
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Updated Price</label>
                            <input type="number" step="0.01" class="form-control" name="price" required>
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Updated Stock</label>
                            <input type="number" class="form-control" name="stock" required>
                        </div>

                        <div class="d-flex gap-3">
                            <button type="submit" class="btn btn-warning btn-lg flex-grow-1">Save Changes</button>
                            <a href="${pageContext.request.contextPath}/toy?action=view" class="btn btn-outline-light btn-lg">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>

<%@ include file="../partials/footer.jsp" %>