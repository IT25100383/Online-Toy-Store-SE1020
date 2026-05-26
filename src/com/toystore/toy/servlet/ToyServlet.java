package com.toystore.toy.servlet;

import com.toystore.toy.model.toy;
import com.toystore.toy.service.ToyService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

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

        switch (action) {
            case "view":
                // UPDATED FILTER LOGIC
                String category = request.getParameter("category");

                if (category != null && !category.isEmpty()) {
                    request.setAttribute(
                            "toyList",
                            toyService.getToysByCategory(category)
                    );
                } else {
                    request.setAttribute(
                            "toyList",
                            toyService.getToyList()
                    );
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
                String toyId = request.getParameter("toyId");
                toyService.deleteToy(toyId);
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