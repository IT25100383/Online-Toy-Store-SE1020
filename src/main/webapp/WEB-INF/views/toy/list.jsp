<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setAttribute("pageTitle", "Browse Toys");
    request.setAttribute("isAdminPage", false);

    // ROLE CHECK LOGIC
    com.toystore.user.model.User loggedInUser = (com.toystore.user.model.User) session.getAttribute("loggedInUser");
    boolean isAdmin = loggedInUser != null && "ADMIN".equalsIgnoreCase(loggedInUser.getRole());
%>

<%@ include file="../partials/header.jsp" %>
<%@ include file="../partials/navbar.jsp" %>

<main class="container py-5">
    <div class="d-flex flex-column flex-lg-row justify-content-between align-items-lg-center gap-3 mb-5">
        <div>
            <h1 class="fw-bold display-5 mb-2">Explore Our Collection</h1>
            <p class="text-secondary fs-5 mb-0">Discover premium toys, collectibles, and creative experiences.</p>
        </div>

        <%-- ADD TOY BUTTON (Admin Only) --%>
        <% if (isAdmin) { %>
        <a href="${pageContext.request.contextPath}/toy?action=addPage" class="btn btn-warning btn-lg rounded-pill px-4">
            <i class="bi bi-plus-lg me-2"></i> Add New Toy
        </a>
        <% } %>
    </div>

    <div class="d-flex flex-wrap gap-3 mb-5">
        <a href="${pageContext.request.contextPath}/toy?action=view"
           class="badge ${empty param.category ? 'bg-warning text-dark' : 'bg-dark'} fs-6 px-4 py-3 text-decoration-none">
            All Toys
        </a>
        <a href="${pageContext.request.contextPath}/toy?action=view&category=Soft"
           class="badge ${param.category == 'Soft' ? 'bg-warning text-dark' : 'bg-dark'} fs-6 px-4 py-3 text-decoration-none">
            Soft
        </a>
        <a href="${pageContext.request.contextPath}/toy?action=view&category=Creative"
           class="badge ${param.category == 'Creative' ? 'bg-warning text-dark' : 'bg-dark'} fs-6 px-4 py-3 text-decoration-none">
            Creative
        </a>
        <a href="${pageContext.request.contextPath}/toy?action=view&category=Puzzles"
           class="badge ${param.category == 'Puzzles' ? 'bg-warning text-dark' : 'bg-dark'} fs-6 px-4 py-3 text-decoration-none">
            Puzzles
        </a>
        <a href="${pageContext.request.contextPath}/toy?action=view&category=Sports"
           class="badge ${param.category == 'Sports' ? 'bg-warning text-dark' : 'bg-dark'} fs-6 px-4 py-3 text-decoration-none">
            Sports
        </a>
        <a href="${pageContext.request.contextPath}/toy?action=view&category=Board Games"
           class="badge ${param.category == 'Board Games' ? 'bg-warning text-dark' : 'bg-dark'} fs-6 px-4 py-3 text-decoration-none">
            Board Games
        </a>
    </div>

    <div class="row g-4">
        <c:choose>
            <c:when test="${empty toyList}">
                <div class="col-12">
                    <div class="alert alert-warning text-center p-5">
                        <h3 class="fw-bold mb-3">No Toys Available</h3>
                        <p class="mb-0">The inventory is currently empty or no items match this category.</p>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach var="toy" items="${toyList}">
                    <div class="col-md-6 col-lg-4 col-xl-3">
                        <div class="card bg-dark text-white border-0 shadow-lg h-100">
                            <div class="position-relative">
                                <img src="${pageContext.request.contextPath}/images/toys/${toy.toyId}.jpeg"
                                     class="card-img-top"
                                     alt="${toy.name}"
                                     style="height: 260px; object-fit: cover;">
                                <span class="badge bg-warning text-dark position-absolute top-0 start-0 m-3">${toy.category}</span>
                            </div>
                            <div class="card-body d-flex flex-column">
                                <h5 class="fw-bold mb-1">${toy.name}</h5>
                                <p class="text-secondary small mb-2">Age: ${toy.ageGroup}+</p>

                                <div class="mb-3">
                                    <span class="fs-4 fw-bold text-warning">$${toy.price}</span>
                                    <div class="small ${toy.stock > 5 ? 'text-success' : 'text-danger'}">
                                            ${toy.stock} in stock
                                    </div>
                                </div>

                                <div class="mt-auto">
                                        <%-- PUBLIC ACTION: ORDER NOW --%>
                                    <a href="${pageContext.request.contextPath}/order?action=checkout&toyId=${toy.toyId}"
                                       class="btn btn-warning w-100 fw-bold mb-2">
                                        <i class="bi bi-cart-plus me-2"></i>Order Now
                                    </a>

                                        <%-- ADMIN ACTIONS: EDIT/DELETE --%>
                                    <% if (isAdmin) { %>
                                    <div class="d-flex gap-2">
                                        <a href="${pageContext.request.contextPath}/toy?action=editPage&toyId=${toy.toyId}"
                                           class="btn btn-sm btn-outline-light flex-grow-1">Edit</a>
                                        <a href="${pageContext.request.contextPath}/toy?action=delete&toyId=${toy.toyId}"
                                           class="btn btn-sm btn-outline-danger"
                                           onclick="return confirm('Delete this toy?');">
                                            <i class="bi bi-trash"></i>
                                        </a>
                                    </div>
                                    <% } %>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</main>

<%@ include file="../partials/footer.jsp" %>