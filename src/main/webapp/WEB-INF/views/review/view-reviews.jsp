<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/partials/header.jsp">
    <jsp:param name="pageTitle" value="Feedback Matrix - Nexus Labs" />
</jsp:include>
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<div class="container py-5">
    <div class="row align-items-center mb-5">
        <div class="col-md-6">
            <h2 class="text-white mb-1">User Feedback Matrix</h2>
            <p class="text-muted-custom mb-0">Synchronized stream of verified and guest intelligence.</p>
        </div>
        <div class="col-md-6 text-md-end">
            <a href="${pageContext.request.contextPath}/review?action=addPage" class="btn btn-premium">
                <i class="bi bi-chat-square-text me-2"></i>Submit Intelligence
            </a>
        </div>
    </div>

    <div class="row g-4">
        <c:choose>
            <c:when test="${not empty reviewList}">
                <c:forEach var="rev" items="${reviewList}">
                    <div class="col-md-6 col-lg-4">
                        <div class="premium-card p-4 h-100 d-flex flex-column">
                            <div class="d-flex justify-content-between mb-3">
                                <span class="font-monospace text-gold small">${rev.reviewId}</span>
                                <span class="badge bg-dark border border-secondary text-uppercase" style="font-size: 0.65rem;">
                                        ${rev.status}
                                </span>
                            </div>

                            <h6 class="text-white mb-3 lh-base">${rev.display()}</h6>

                            <div class="mt-auto pt-3 border-top border-secondary">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="text-gold">
                                        <c:forEach begin="1" end="${rev.rating}">★</c:forEach>
                                        <c:forEach begin="${rev.rating + 1}" end="5">☆</c:forEach>
                                    </div>
                                    <span class="text-muted-custom small font-monospace">${rev.date}</span>
                                </div>

                                <div class="d-flex gap-2 mt-3">
                                    <a href="${pageContext.request.contextPath}/review?action=editPage&reviewId=${rev.reviewId}" class="btn btn-outline-warning btn-sm flex-grow-1">Edit</a>
                                    <a href="${pageContext.request.contextPath}/review?action=delete&id=${rev.reviewId}" class="btn btn-outline-danger btn-sm flex-grow-1" onclick="return confirm('Delete this review?');">Delete</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="col-12 text-center py-5">
                    <p class="text-muted-custom italic">No review data packets detected in the current sector.</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<jsp:include page="/WEB-INF/views/partials/footer.jsp" />