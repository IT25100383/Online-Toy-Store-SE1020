<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/partials/header.jsp">
    <jsp:param name="pageTitle" value="Nexus Labs - Category Provisioning" />
</jsp:include>
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-5">
            <div class="premium-card p-4">
                <h4 class="text-white mb-3">Provision Category</h4>
                <form action="${pageContext.request.contextPath}/category?action=add" method="POST">
                    <div class="mb-3">
                        <label class="form-label text-gold small font-monospace">Category ID</label>
                        <input type="text" name="id" class="form-control bg-panel border-secondary text-white" placeholder="CAT-001" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label text-gold small font-monospace">Category Name</label>
                        <input type="text" name="name" class="form-control bg-panel border-secondary text-white" placeholder="Mecha / Static Models" required>
                    </div>
                    <div class="mb-4">
                        <label class="form-label text-gold small font-monospace">Description</label>
                        <textarea name="description" class="form-control bg-panel border-secondary text-white" rows="3" placeholder="Define category parameters..."></textarea>
                    </div>
                    <button type="submit" class="btn btn-premium w-100">Initialize Node</button>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/partials/footer.jsp" />