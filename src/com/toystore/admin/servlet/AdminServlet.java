package com.toystore.admin.servlet;

import com.toystore.user.service.UserService;
import com.toystore.toy.service.ToyService;
import com.toystore.order.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private final UserService userService = new UserService();
    private final ToyService toyService = new ToyService();
    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "dashboard";
        }

        switch (action) {
            case "dashboard":
                loadDashboard(request);
                request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
                break;

            case "sales":
                loadDashboard(request);
                request.getRequestDispatcher("/WEB-INF/views/admin/sales-report.jsp").forward(request, response);
                break;

            case "inventory":
                loadDashboard(request);
                request.getRequestDispatcher("/WEB-INF/views/admin/inventory-status.jsp").forward(request, response);
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/admin?action=dashboard");
                break;
        }
    }

    private void loadDashboard(HttpServletRequest request) throws IOException {
        int totalUsers = userService.getAllUsers().size();
        int totalToys = toyService.getToyList().size();

        int totalOrders = orderService.getTotalOrders();
        double estimatedRevenue = orderService.calculateTotalRevenue();
        int lowStockItems = toyService.getLowStockCount();

        request.setAttribute("isAdminPage", true);
        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("totalToys", totalToys);
        request.setAttribute("totalOrders", totalOrders);
        request.setAttribute("estimatedRevenue", estimatedRevenue);
        request.setAttribute("lowStockItems", lowStockItems);
    }
}