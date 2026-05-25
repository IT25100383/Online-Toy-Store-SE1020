<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>

<jsp:include page="/WEB-INF/views/partials/header.jsp">
    <jsp:param name="pageTitle"
               value="ToyVerse Admin Dashboard" />
</jsp:include>

<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<main class="container-fluid py-4">

    <!-- HEADER -->

    <div class="mb-5">

        <h1 class="display-5 fw-bold text-warning">

            ToyVerse Admin Dashboard

        </h1>

        <p class="text-secondary fs-5">

            Monitor inventory, users, orders,
            and overall store performance.

        </p>

    </div>

    <!-- METRIC CARDS -->

    <div class="row g-4 mb-5">

        <!-- USERS -->

        <div class="col-md-6 col-xl-3">

            <div class="card bg-dark text-white border-0 shadow-lg h-100">

                <div class="card-body">

                    <div class="d-flex
                                justify-content-between
                                align-items-center">

                        <div>

                            <h6 class="text-secondary">

                                Registered Users

                            </h6>

                            <h2 class="fw-bold">

                                ${totalUsers}

                            </h2>

                        </div>

                        <i class="bi bi-people-fill
                                  fs-1 text-warning"></i>

                    </div>

                </div>

            </div>

        </div>

        <!-- TOYS -->

        <div class="col-md-6 col-xl-3">

            <div class="card bg-dark text-white border-0 shadow-lg h-100">

                <div class="card-body">

                    <div class="d-flex
                                justify-content-between
                                align-items-center">

                        <div>

                            <h6 class="text-secondary">

                                Inventory Units

                            </h6>

                            <h2 class="fw-bold">

                                ${totalToys}

                            </h2>

                        </div>

                        <i class="bi bi-box-seam
                                  fs-1 text-warning"></i>

                    </div>

                </div>

            </div>

        </div>

        <!-- ORDERS -->

        <div class="col-md-6 col-xl-3">

            <div class="card bg-dark text-white border-0 shadow-lg h-100">

                <div class="card-body">

                    <div class="d-flex
                                justify-content-between
                                align-items-center">

                        <div>

                            <h6 class="text-secondary">

                                Orders Processed

                            </h6>

                            <h2 class="fw-bold">

                                ${totalOrders}

                            </h2>

                        </div>

                        <i class="bi bi-cart-check-fill
                                  fs-1 text-warning"></i>

                    </div>

                </div>

            </div>

        </div>

        <!-- REVENUE -->

        <div class="col-md-6 col-xl-3">

            <div class="card bg-dark text-white border-0 shadow-lg h-100">

                <div class="card-body">

                    <div class="d-flex
                                justify-content-between
                                align-items-center">

                        <div>

                            <h6 class="text-secondary">

                                Estimated Revenue

                            </h6>

                            <h2 class="fw-bold">

                                $${estimatedRevenue}

                            </h2>

                        </div>

                        <i class="bi bi-currency-dollar
                                  fs-1 text-warning"></i>

                    </div>

                </div>

            </div>

        </div>

    </div>

    <!-- OPERATIONS -->

    <div class="row g-4">

        <!-- QUICK ACTIONS -->

        <div class="col-lg-4">

            <div class="card bg-dark
                        text-white
                        border-0
                        shadow-lg
                        h-100">

                <div class="card-body">

                    <h3 class="fw-bold mb-4 text-warning">

                        Quick Actions

                    </h3>

                    <div class="d-grid gap-3">

                        <a href="${pageContext.request.contextPath}/toy?action=addPage"
                           class="btn btn-warning btn-lg">

                            Add New Toy

                        </a>

                        <a href="${pageContext.request.contextPath}/toy?action=view"
                           class="btn btn-outline-light btn-lg">

                            Manage Inventory

                        </a>

                        <a href="${pageContext.request.contextPath}/user?action=view"
                           class="btn btn-outline-light btn-lg">

                            View Users

                        </a>

                    </div>

                </div>

            </div>

        </div>

        <!-- SALES ANALYTICS -->

        <div class="col-lg-8">

            <div class="card bg-dark
                        text-white
                        border-0
                        shadow-lg
                        h-100">

                <div class="card-body">

                    <h3 class="fw-bold mb-4 text-warning">

                        Sales Analytics

                    </h3>

                    <!-- SALES GOAL -->

                    <div class="mb-4">

                        <div class="d-flex
                                    justify-content-between">

                            <span>

                                Monthly Sales Goal

                            </span>

                            <span class="fw-bold text-success">

                                68%

                            </span>

                        </div>

                        <div class="progress mt-2"
                             style="height:10px;">

                            <div class="progress-bar
                                        bg-success"
                                 style="width:68%;">
                            </div>

                        </div>

                    </div>

                    <!-- INVENTORY -->

                    <div class="mb-4">

                        <div class="d-flex
                                    justify-content-between">

                            <span>

                                Inventory Capacity

                            </span>

                            <span class="fw-bold text-info">

                                82%

                            </span>

                        </div>

                        <div class="progress mt-2"
                             style="height:10px;">

                            <div class="progress-bar
                                        bg-info"
                                 style="width:82%;">
                            </div>

                        </div>

                    </div>

                    <!-- LOW STOCK -->

                    <div class="mb-4">

                        <div class="d-flex
                                    justify-content-between">

                            <span>

                                Low Stock Products

                            </span>

                            <span class="fw-bold text-warning">

                                ${lowStockItems} Items

                            </span>

                        </div>

                        <div class="progress mt-2"
                             style="height:10px;">

                            <div class="progress-bar
                                        bg-warning"
                                 style="width:35%;">
                            </div>

                        </div>

                    </div>

                    <!-- STATUS -->

                    <div class="alert alert-warning
                                mt-4">

                        <strong>
                            Store Status:
                        </strong>

                        Inventory database operational.
                        Store systems running normally.

                    </div>

                </div>

            </div>

        </div>

    </div>

</main>

<jsp:include page="/WEB-INF/views/partials/footer.jsp" />