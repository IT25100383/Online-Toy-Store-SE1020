<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/partials/header.jsp">
    <jsp:param name="pageTitle" value="Initialize Review Packet" />
</jsp:include>
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-6">
            <div class="mb-4 text-center">
                <h3 class="text-white">Submit Field Report</h3>
                <p class="text-muted-custom">Provide feedback on your acquired assets for the collective registry.</p>
            </div>

            <div class="glass-panel p-4 p-md-5 rounded-4">
                <form action="${pageContext.request.contextPath}/review?action=add" method="POST">
                    <div class="mb-4">
                        <label class="form-label text-white small font-monospace text-uppercase">Identification</label>
                        <input type="text" class="form-control" name="guestName" placeholder="Enter Guest Label (if not registered)">
                    </div>

                    <div class="row mb-4">
                        <div class="col-md-6">
                            <label class="form-label text-white small font-monospace text-uppercase">Rating</label>
                            <select class="form-select" name="rating">
                                <option value="5">5 - Optimal</option>
                                <option value="4">4 - High Efficiency</option>
                                <option value="3">3 - Nominal</option>
                                <option value="2">2 - Subpar</option>
                                <option value="1">1 - Critical Failure</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label text-white small font-monospace text-uppercase">Asset ID</label>
                            <input type="text" class="form-control" name="toyId" required placeholder="T-001">
                        </div>
                    </div>

                    <div class="mb-5">
                        <label class="form-label text-white small font-monospace text-uppercase">Observation Details</label>
                        <textarea class="form-control" name="comment" rows="4" required placeholder="Type your intelligence report here..."></textarea>
                    </div>

                    <button type="submit" class="btn btn-premium w-100 py-3 text-uppercase font-monospace">
                        Transmit Review
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/partials/footer.jsp" />