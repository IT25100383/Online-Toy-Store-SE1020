<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageTitle", "Checkout");
    // Get the User object from session
    com.toystore.user.model.User currentUser = (com.toystore.user.model.User) session.getAttribute("loggedInUser");
    // Get toyId passed from the servlet
    String toyId = (String) request.getAttribute("toyId");
%>

<%@ include file="../partials/header.jsp" %>
<%@ include file="../partials/navbar.jsp" %>

<main class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-7">
            <div class="card border-0 shadow-lg">
                <div class="card-body p-5">
                    <h1 class="fw-bold mb-4">Checkout</h1>
                    <p class="text-secondary mb-5">Complete your ToyVerse purchase.</p>

                    <form action="${pageContext.request.contextPath}/order" method="post">
                        <input type="hidden" name="action" value="place">

                        <div class="mb-4">
                            <label class="form-label fw-bold">User ID</label>
                            <input type="text" class="form-control form-control-lg bg-light" name="userId"
                                   value="<%= (currentUser != null) ? currentUser.getUserId() : "" %>"
                                   readonly required>
                            <div class="form-text">Ordering as: <%= (currentUser != null) ? currentUser.getName() : "Guest" %></div>
                        </div>

                        <div class="mb-4">
                            <label class="form-label fw-bold">Toy ID</label>
                            <input type="text" class="form-control form-control-lg bg-light" name="toyId"
                                   value="<%= (toyId != null) ? toyId : "" %>"
                                   readonly required>
                        </div>

                        <div class="mb-5">
                            <label class="form-label fw-bold">Quantity</label>
                            <input type="number" min="1" class="form-control form-control-lg" name="quantity" value="1" required>
                        </div>

                        <div class="mb-5">
                            <label class="form-label fw-bold">Payment Method</label>
                            <select class="form-select form-select-lg">
                                <option>Credit / Debit Card</option>
                                <option>PayPal</option>
                                <option>Cash on Delivery</option>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-warning btn-lg w-100 fw-bold shadow-sm">
                            <i class="bi bi-bag-check me-2"></i>Confirm Order
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>

<%@ include file="../partials/footer.jsp" %>