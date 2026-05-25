<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageTitle", "Add New Toy");
    request.setAttribute("isAdminPage", true);
%>

<%@ include file="../partials/header.jsp" %>
<%@ include file="../partials/navbar.jsp" %>

<main class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="card bg-dark text-white border-0 shadow-lg">
                <div class="card-body p-5">

                    <!-- HEADER -->
                    <div class="mb-5 text-center">
                        <h1 class="fw-bold text-warning mb-3">Add New Toy</h1>
                        <p class="text-secondary fs-5">Add a new product to the ToyVerse inventory system.</p>
                    </div>

                    <!-- FORM -->
                    <form method="post" action="${pageContext.request.contextPath}/toy">
                        <input type="hidden" name="action" value="add">

                        <!-- TOY ID -->
                        <div class="mb-4">
                            <label class="form-label fw-semibold">Toy ID</label>
                            <input type="text" class="form-control form-control-lg" name="toyId" placeholder="T001" required>
                        </div>

                        <!-- TOY NAME -->
                        <div class="mb-4">
                            <label class="form-label fw-semibold">Toy Name</label>
                            <input type="text" class="form-control form-control-lg" name="name" placeholder="Turbo RC Drift Car" required>
                        </div>

                        <!-- CATEGORY -->
                        <div class="mb-4">
                            <label class="form-label fw-semibold">Category</label>
                            <select class="form-select form-select-lg" name="category" required>
                                <option value="">Select Category</option>
                                <option value="RC Vehicles">RC Vehicles</option>
                                <option value="Building Sets">Building Sets</option>
                                <option value="Plush Toys">Plush Toys</option>
                                <option value="Action Figures">Action Figures</option>
                                <option value="Board Games">Board Games</option>
                                <option value="Educational Toys">Educational Toys</option>
                                <option value="Outdoor Toys">Outdoor Toys</option>
                                <option value="Collector Figures">Collector Figures</option>
                                <option value="Puzzles">Puzzles</option>
                            </select>
                        </div>

                        <!-- PRICE -->
                        <div class="mb-4">
                            <label class="form-label fw-semibold">Price ($)</label>
                            <input type="number" step="0.01" class="form-control form-control-lg" name="price" placeholder="89.99" required>
                        </div>

                        <!-- AGE GROUP -->
                        <div class="mb-4">
                            <label class="form-label fw-semibold">Age Group</label>
                            <select class="form-select form-select-lg" name="ageGroup" required>
                                <option value="">Select Age Group</option>
                                <option value="3">3+</option>
                                <option value="5">5+</option>
                                <option value="8">8+</option>
                                <option value="10">10+</option>
                                <option value="12">12+</option>
                                <option value="14">14+</option>
                                <option value="16">16+</option>
                            </select>
                        </div>

                        <!-- STOCK -->
                        <div class="mb-5">
                            <label class="form-label fw-semibold">Stock Quantity</label>
                            <input type="number" class="form-control form-control-lg" name="stock" placeholder="20" required>
                        </div>

                        <!-- BUTTONS -->
                        <div class="d-flex gap-3">
                            <button type="submit" class="btn btn-warning btn-lg flex-grow-1">Add Toy</button>
                            <a href="${pageContext.request.contextPath}/toy?action=view" class="btn btn-outline-light btn-lg">Cancel</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</main>

<%@ include file="../partials/footer.jsp" %>