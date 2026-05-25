<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageTitle", "Edit Review");
    request.setAttribute("isAdminPage", true);
%>

<%@ include file="../partials/header.jsp" %>
<%@ include file="../partials/navbar.jsp" %>

<main class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-7">
            <div class="card bg-dark text-white border-0 shadow-lg">
                <div class="card-body p-5">
                    <div class="text-center mb-5">
                        <h1 class="fw-bold text-warning mb-3">Edit Review</h1>
                        <p class="text-secondary fs-5">Update review information and moderation details.</p>
                    </div>

                    <form method="post" action="${pageContext.request.contextPath}/review">
                        <input type="hidden" name="action" value="update">

                        <div class="mb-4">
                            <label class="form-label">Review ID</label>
                            <input type="text" class="form-control form-control-lg" name="reviewId" value="${param.reviewId}" readonly>
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Rating</label>
                            <select class="form-select form-select-lg" name="rating" required>
                                <option value="1">1 Star</option>
                                <option value="2">2 Stars</option>
                                <option value="3">3 Stars</option>
                                <option value="4">4 Stars</option>
                                <option value="5">5 Stars</option>
                            </select>
                        </div>

                        <div class="mb-5">
                            <label class="form-label">Review Comment</label>
                            <textarea class="form-control form-control-lg" rows="5" name="comment" required></textarea>
                        </div>

                        <div class="d-flex gap-3">
                            <button type="submit" class="btn btn-warning btn-lg flex-grow-1">Save Changes</button>
                            <a href="${pageContext.request.contextPath}/review?action=view" class="btn btn-outline-light btn-lg">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>

<%@ include file="../partials/footer.jsp" %>