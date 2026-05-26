package com.toystore.category.servlet;

import com.toystore.category.model.Category;
import com.toystore.category.service.CategoryService;
import com.toystore.user.model.User; // Added Import

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {

    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "view";
        }

        // ROLE PROTECTION: Admin Only for modifying categories
        if ("addPage".equals(action) || "editPage".equals(action) || "delete".equals(action)) {
            User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
            if (loggedInUser == null || !"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {
                response.sendRedirect(request.getContextPath() + "/");
                return;
            }
        }

        switch (action) {
            case "view":
                request.setAttribute("categories", categoryService.getAllCategories());
                request.getRequestDispatcher("/WEB-INF/views/category/view-categories.jsp").forward(request, response);
                break;

            case "addPage":
                request.getRequestDispatcher("/WEB-INF/views/category/add-category.jsp").forward(request, response);
                break;

            case "editPage":
                request.setAttribute("categoryId", request.getParameter("id"));
                request.getRequestDispatcher("/WEB-INF/views/category/edit-category.jsp").forward(request, response);
                break;

            case "delete":
                categoryService.deleteCategory(request.getParameter("id"));
                response.sendRedirect(request.getContextPath() + "/category?action=view");
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/category?action=view");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ROLE PROTECTION: Block unauthorized POST requests
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser == null || !"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String description = request.getParameter("description");

            Category newCategory = new Category(id, name, description);
            categoryService.addCategory(newCategory);

        } else if ("update".equals(action)) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String description = request.getParameter("description");

            Category updatedCategory = new Category(id, name, description);
            categoryService.updateCategory(updatedCategory);
        }

        response.sendRedirect(request.getContextPath() + "/category?action=view");
    }
}