<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/partials/header.jsp">
    <jsp:param name="pageTitle" value="System Dispatch Log Ledger" />
</jsp:include>
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<div class="container py-5">
    <div class="mb-5">
        <h2 class="text-white mb-1">Transactional Dispatch Logs</h2>
        <p class="text-muted-custom">Track verification and routing execution workflows for purchased collectibles instances.</p>
    </div>

    <div class="premium-card p-4">
        <div class="table-responsive">
            <table class="table table-dark-custom align-middle mb-0">
                <thead>
                <tr class="font-monospace text-uppercase text-muted-custom" style="font-size:0.8rem;">
                    <th>Order Hash</th>
                    <th>Associated Toy Asset</th>
                    <th>Allocation Count</th>
                    <th>Calculated Gross ($)</th>
                    <th>Status Flag</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${not empty orderList}">
                        <c:forEach var="o" items="${orderList}">
                            <tr>
                                <td class="font-monospace text-gold">#ORD-${o.orderId}</td>
                                <td>
                                    <div class="fw-semibold text-white">${o.toyName}</div>
                                    <div class="text-muted-custom small font-monospace" style="font-size:0.75rem;">ID Anchor: ${o.toyId}</div>
                                </td>
                                <td class="font-monospace">${o.quantity}</td>
                                <td class="font-monospace fw-bold text-white">$${o.totalPrice}</td>
                                <td>
                                        <span class="badge rounded-pill px-3 py-2 text-uppercase font-monospace
                                            ${o.status == 'Delivered' ? 'bg-success-subtle text-success border border-success' :
                                              o.status == 'Pending' ? 'bg-warning-subtle text-warning border border-warning' :
                                              'bg-primary-subtle text-info border border-info'}">
                                                ${o.status}
                                        </span>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <!-- Elegant fallback UI mockup for empty production rows -->
                        <tr>
                            <td colspan="5" class="text-center py-5 text-muted-custom">
                                <i class="bi bi-credit-card-2-back display-3 d-block mb-3 opacity-25"></i>
                                <span class="h6 text-white d-block mb-1">No Orders Located in Current Context Scope</span>
                                <p class="small text-muted-custom mb-0">When an item checkout configuration is triggered via the frontend system engine, execution data structures print here.</p>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/partials/footer.jsp" />