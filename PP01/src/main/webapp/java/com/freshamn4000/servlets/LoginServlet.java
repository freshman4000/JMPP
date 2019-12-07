package com.freshamn4000.servlets;

import com.freshamn4000.controllers.UserDaoFactory;
import com.freshamn4000.interfaces.ClientService;
import com.freshamn4000.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientService<User, Long> clientService = UserDaoFactory.getClientService(req, resp);
        try {
            if (!clientService.validateRegistration(req.getParameter("email"))) {
                if (clientService.validateLogin(req.getParameter("email"), req.getParameter("password"))) {
                    HttpSession session = req.getSession();
                    session.setAttribute("role", clientService.validateRole(req.getParameter("email"), req.getParameter("password")) ? "admin" : "user");
                    if (session.getAttribute("role").equals("user")) {
                        resp.sendRedirect("/start_page.jsp");
                    } else if (session.getAttribute("role").equals("admin")) {
                        resp.sendRedirect("/admin_panel.jsp");
                    }
                } else {
                    req.setAttribute("message", "Wrong password!");
                    req.getRequestDispatcher("/index.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("message", "No user in DB! Try one more time!");
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("message", "DB connectivity problem! Try one more time!");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
