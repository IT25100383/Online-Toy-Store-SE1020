package com.toystore.order.servlet;

import com.toystore.order.service.OrderService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "view";
        }

        switch (action) {
            case "view":
                request.setAttribute("orderList", orderService.getAllOrders());
                request.getRequestDispatcher("/WEB-INF/views/order/list.jsp").forward(request, response);
                break;

            case "checkout":
                // UPDATED: Capture toyId from request and set it as an attribute for checkout.jsp
                request.setAttribute("toyId", request.getParameter("toyId"));
                request.getRequestDispatcher("/WEB-INF/views/order/checkout.jsp").forward(request, response);
                break;

            case "confirmation":
                request.getRequestDispatcher("/WEB-INF/views/order/order-confirmation.jsp").forward(request, response);
                break;

            case "delete":
                orderService.deleteOrder(request.getParameter("id"));
                response.sendRedirect(request.getContextPath() + "/order?action=view");
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/order?action=view");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // Match the 'place' action from checkout.jsp
        if ("place".equals(action)) {
            String userId = request.getParameter("userId");
            String toyId = request.getParameter("toyId");

            // Basic null/empty check before parsing
            String qtyStr = request.getParameter("quantity");
            int quantity = (qtyStr != null) ? Integer.parseInt(qtyStr) : 1;

            // Process the order using Service logic
            String result = orderService.placeOrder(userId, toyId, quantity);

            // Store the result in session temporarily so it survives the redirect
            HttpSession session = request.getSession();
            session.setAttribute("orderResult", result);

            // REDIRECT to the confirmation page
            response.sendRedirect(request.getContextPath() + "/order?action=confirmation");
        }
    }
}