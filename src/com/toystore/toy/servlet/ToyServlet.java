package com.toystore.toy.servlet;

import com.toystore.user.model.User;
import com.toystore.toy.model.toy;
import com.toystore.toy.service.ToyService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/toy")
public class ToyServlet extends HttpServlet {

    private final ToyService toyService = new ToyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "view";
        }

        // SECURITY CHECK for Admin actions (Add, Edit, Delete)
        if ("addPage".equals(action) || "editPage".equals(action) || "delete".equals(action)) {
            User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

            if (loggedInUser == null || !"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {
                response.sendRedirect(request.getContextPath() + "/");
                return;
            }
        }

        switch (action) {
            case "view":
                String category = request.getParameter("category");
                if (category != null && !category.isEmpty()) {
                    request.setAttribute("toyList", toyService.getToysByCategory(category));
                } else {
                    request.setAttribute("toyList", toyService.getToyList());
                }
                request.getRequestDispatcher("/WEB-INF/views/toy/list.jsp").forward(request, response);
                break;

            case "addPage":
                request.getRequestDispatcher("/WEB-INF/views/toy/add.jsp").forward(request, response);
                break;

            case "editPage":
                request.setAttribute("toyId", request.getParameter("toyId"));
                request.getRequestDispatcher("/WEB-INF/views/toy/edit.jsp").forward(request, response);
                break;

            case "delete":
                String idToDelete = request.getParameter("toyId");
                toyService.deleteToy(idToDelete);
                response.sendRedirect(request.getContextPath() + "/toy?action=view");
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/toy?action=view");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // SECURITY CHECK for sensitive POST actions
        if ("add".equals(action) || "update".equals(action)) {
            User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

            if (loggedInUser == null || !"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {
                response.sendRedirect(request.getContextPath() + "/");
                return;
            }
        }

        // ADD TOY
        if ("add".equals(action)) {
            toy newToy = new toy(
                    request.getParameter("toyId"),
                    request.getParameter("name"),
                    request.getParameter("category"),
                    Double.parseDouble(request.getParameter("price")),
                    Integer.parseInt(request.getParameter("ageGroup")),
                    Integer.parseInt(request.getParameter("stock"))
            );

            toyService.addToy(newToy);
            response.sendRedirect(request.getContextPath() + "/toy?action=view");
        }
        // UPDATE TOY
        else if ("update".equals(action)) {
            String toyId = request.getParameter("toyId");
            double price = Double.parseDouble(request.getParameter("price"));
            int stock = Integer.parseInt(request.getParameter("stock"));

            toyService.updateToy(toyId, price, stock);
            response.sendRedirect(request.getContextPath() + "/toy?action=view");
        }
    }
}