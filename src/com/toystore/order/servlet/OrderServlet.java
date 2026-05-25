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

        if ("placeOrder".equals(action)) {
            String userId = request.getParameter("userId");
            String toyId = request.getParameter("toyId");
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            // USE EXISTING SERVICE LOGIC
            String result = orderService.placeOrder(userId, toyId, quantity);

            request.setAttribute("orderResult", result);
            request.getRequestDispatcher("/WEB-INF/views/order/order-confirmation.jsp").forward(request, response);
        }
    }
}