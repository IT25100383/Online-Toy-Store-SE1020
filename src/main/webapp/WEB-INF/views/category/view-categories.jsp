<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/views/partials/header.jsp">
    <jsp:param name="pageTitle" value="Nexus Labs - Taxonomy Management" />
</jsp:include>
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<div class="container py-5">
    <div class="row align-items-center mb-5">
        <div class="col-md-6">
            <h2 class="text-white mb-1">Taxonomy Matrix</h2>
            <p class="text-muted-custom mb-0">Structural classification for inventory assets.</p>
        </div>
        <div class="col-md-6 text-md-end">
            <a href="${pageContext.request.contextPath}/category?action=addPage" class="btn btn-premium">
                <i class="bi bi-plus-lg me-2"></i>Provision Category
            </a>
        </div>
    </div>

    <div class="glass-panel overflow-hidden rounded-4 p-0">
        <table class="table table-dark table-hover mb-0 font-monospace" style="--bs-table-bg: transparent;">
            <thead class="text-gold small border-bottom">
            <tr>
                <th class="ps-4 py-3">Vector ID</th>
                <th class="py-3">Designation</th>
                <th class="py-3">Description Metadata</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${not empty categories}">
                    <c:forEach var="cat" items="${categories}">
                        <tr>
                            <td class="ps-4 py-3 text-white">${fn:escapeXml(cat.id)}</td>
                            <td class="py-3 text-white fw-bold">${fn:escapeXml(cat.name)}</td>
                            <td class="py-3 small text-muted-custom">${fn:escapeXml(cat.description)}</td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="3" class="text-center py-5">
                            <i class="bi bi-database-exclamation d-block mb-2 fs-2"></i>
                            No classification vectors identified.
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/views/partials/footer.jsp" />