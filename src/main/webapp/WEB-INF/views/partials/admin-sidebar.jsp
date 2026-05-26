<div class="admin-sidebar d-flex flex-column p-4 text-white"
     style="min-height:100vh;background:#111827;width:260px;">

    <h4 class="fw-bold mb-4 text-warning">
        <i class="bi bi-cpu-fill"></i>
        Nexus Labs
    </h4>

    <ul class="nav flex-column gap-2">

        <li class="nav-item">
            <a class="nav-link text-white" href="#">
                <i class="bi bi-grid-fill me-2"></i>
                Admin Console
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link text-white"
               href="${pageContext.request.contextPath}/toy?action=view">
                <i class="bi bi-box-seam me-2"></i>
                Inventory Management
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link text-white"
               href="${pageContext.request.contextPath}/order?action=view">
                <i class="bi bi-cart-check me-2"></i>
                Orders
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link text-white"
               href="${pageContext.request.contextPath}/category?action=view">
                <i class="bi bi-tags-fill me-2"></i>
                Category Management
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link text-white"
               href="${pageContext.request.contextPath}/review?action=view">
                <i class="bi bi-chat-square-text-fill me-2"></i>
                Customer Reviews
            </a>
        </li>

    </ul>

    <div class="mt-auto pt-4">

        <a class="btn btn-outline-warning w-100 rounded-pill"
           href="${pageContext.request.contextPath}/">
            Return to Storefront
        </a>

    </div>

</div>