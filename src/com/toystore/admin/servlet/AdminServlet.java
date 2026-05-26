package com.toystore.admin.servlet;

import com.toystore.admin.model.Admin;
import com.toystore.admin.service.AdminService;
import com.toystore.order.service.OrderService;
import com.toystore.toy.service.ToyService;
import com.toystore.user.model.User;
import com.toystore.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private final AdminService adminService = new AdminService();
    private final UserService userService = new UserService();
    private final ToyService toyService = new ToyService();
    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ROLE PROTECTION
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

        if (loggedInUser == null || !"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/user?action=loginPage");
            return;
        }

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

            case "viewAdmins":
                request.setAttribute("adminList", adminService.getAllAdmins());
                request.getRequestDispatcher("/WEB-INF/views/admin/view-admins.jsp").forward(request, response);
                break;

            case "addPage":
                request.getRequestDispatcher("/WEB-INF/views/admin/add-admin.jsp").forward(request, response);
                break;

            case "editPage":
                String username = request.getParameter("username");
                request.setAttribute("admin", adminService.getAdminByUsername(username));
                request.getRequestDispatcher("/WEB-INF/views/admin/edit-admin.jsp").forward(request, response);
                break;

            case "delete":
                String usernameToDelete = request.getParameter("username");
                adminService.deleteAdmin(usernameToDelete);
                response.sendRedirect(request.getContextPath() + "/admin?action=viewAdmins");
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/admin?action=dashboard");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ROLE PROTECTION
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

        if (loggedInUser == null || !"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/user?action=loginPage");
            return;
        }

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            Admin admin = new Admin(
                    request.getParameter("username"),
                    request.getParameter("password"),
                    request.getParameter("role")
            );
            adminService.addAdmin(admin);
            response.sendRedirect(request.getContextPath() + "/admin?action=viewAdmins");

        } else if ("update".equals(action)) {
            adminService.updateAdmin(
                    request.getParameter("username"),
                    request.getParameter("password"),
                    request.getParameter("role")
            );
            response.sendRedirect(request.getContextPath() + "/admin?action=viewAdmins");
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