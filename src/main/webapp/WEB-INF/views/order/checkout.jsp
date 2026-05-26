<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageTitle", "Checkout");
%>

<%@ include file="../partials/header.jsp" %>
<%@ include file="../partials/navbar.jsp" %>

<main class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-7">
            <div class="card border-0 shadow-lg">
                <div class="card-body p-5">
                    <!-- HEADER -->
                    <h1 class="fw-bold mb-4">Checkout</h1>
                    <p class="text-secondary mb-5">Complete your ToyVerse purchase.</p>

                    <!-- FORM -->
                    <form method="post" action="${pageContext.request.contextPath}/order">
                        <input type="hidden" name="action" value="placeOrder">

                        <!-- USER ID -->
                        <div class="mb-4">
                            <label class="form-label">User ID</label>
                            <input type="text" class="form-control form-control-lg" name="userId" placeholder="USR-001" required>
                        </div>

                        <!-- TOY ID -->
                        <div class="mb-4">
                            <label class="form-label">Toy ID</label>
                            <input type="text" class="form-control form-control-lg" name="toyId" placeholder="T001" required>
                        </div>

                        <!-- QUANTITY -->
                        <div class="mb-5">
                            <label class="form-label">Quantity</label>
                            <input type="number" min="1" class="form-control form-control-lg" name="quantity" placeholder="1" required>
                        </div>

                        <!-- PAYMENT METHOD -->
                        <div class="mb-5">
                            <label class="form-label">Payment Method</label>
                            <select class="form-select form-select-lg">
                                <option>Credit / Debit Card</option>
                                <option>PayPal</option>
                                <option>Cash on Delivery</option>
                            </select>
                        </div>

                        <!-- BUTTON -->
                        <button type="submit" class="btn btn-warning btn-lg w-100">Confirm Order</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>

<%@ include file="../partials/footer.jsp" %>