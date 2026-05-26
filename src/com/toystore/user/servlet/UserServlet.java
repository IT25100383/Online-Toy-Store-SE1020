package com.toystore.user.servlet;

import com.toystore.user.model.User;
import com.toystore.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "loginPage";
        }

        switch (action) {
            case "loginPage":
                request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
                break;

            case "registerPage":
                request.getRequestDispatcher("/WEB-INF/views/user/register.jsp").forward(request, response);
                break;

            case "view":
                // ROLE PROTECTION: Admin Only for viewing user list
                User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

                if (loggedInUser == null || !"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {
                    response.sendRedirect(request.getContextPath() + "/");
                    return;
                }

                request.setAttribute("userList", userService.getAllUsers());
                request.getRequestDispatcher("/WEB-INF/views/user/view-users.jsp").forward(request, response);
                break;

            case "profile":
                request.getRequestDispatcher("/WEB-INF/views/user/user-profile.jsp").forward(request, response);
                break;

            case "logout":
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                response.sendRedirect(request.getContextPath() + "/");
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/user?action=loginPage");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Existing doPost logic (handleLogin and handleRegister) remains unchanged
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/user?action=loginPage");
            return;
        }

        switch (action) {
            case "login":
                handleLogin(request, response);
                break;

            case "register":
                handleRegister(request, response);
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/user?action=loginPage");
                break;
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = null;
        try {
            for (User u : userService.getAllUsers()) {
                if (u.getName().equals(username) && u.getPassword().equals(password)) {
                    user = u;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", user);

            if (user.getRole().equalsIgnoreCase("ADMIN")) {
                response.sendRedirect(request.getContextPath() + "/admin");
            } else {
                response.sendRedirect(request.getContextPath() + "/toy?action=view");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/user?action=loginPage");
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String userId = "U-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        User user = new User(userId, name, email, password, "USER");
        userService.register(user);

        response.sendRedirect(request.getContextPath() + "/user?action=loginPage");
    }
}