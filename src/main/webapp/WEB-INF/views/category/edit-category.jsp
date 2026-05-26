<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageTitle", "Edit Category");
    request.setAttribute("isAdminPage", true);
%>

<%@ include file="../partials/header.jsp" %>
<%@ include file="../partials/navbar.jsp" %>

<main class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-7">
            <div class="card bg-dark text-white border-0 shadow-lg">
                <div class="card-body p-5">
                    <div class="mb-5 text-center">
                        <h1 class="fw-bold text-warning mb-3">Edit Category</h1>
                        <p class="text-secondary fs-5">Update category information within the ToyVerse system.</p>
                    </div>

                    <form method="post" action="${pageContext.request.contextPath}/category">
                        <input type="hidden" name="action" value="update">

                        <div class="mb-4">
                            <label class="form-label">Category ID</label>
                            <input type="text" class="form-control form-control-lg" name="id" value="${categoryId}" readonly>
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Category Name</label>
                            <input type="text" class="form-control form-control-lg" name="name" required>
                        </div>

                        <div class="mb-5">
                            <label class="form-label">Description</label>
                            <textarea class="form-control form-control-lg" rows="4" name="description" required></textarea>
                        </div>

                        <div class="d-flex gap-3">
                            <button type="submit" class="btn btn-warning btn-lg flex-grow-1">Save Changes</button>
                            <a href="${pageContext.request.contextPath}/category?action=view" class="btn btn-outline-light btn-lg">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>

<%@ include file="../partials/footer.jsp" %>