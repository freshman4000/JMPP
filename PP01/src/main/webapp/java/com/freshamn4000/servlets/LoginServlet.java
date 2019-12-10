package com.freshamn4000.servlets;

import com.freshamn4000.dao.UserDaoFactory;
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientService<User, Long> clientService = new UserDaoFactory().getDAO();
        try {
            //if there is user with such email in db
            if (!clientService.validateRegistration(req.getParameter("email"))) {
                //if password matches pass from database with such email
                if (clientService.validateLogin(req.getParameter("email"), req.getParameter("password"))) {
                    HttpSession session = req.getSession();
                    //getting role of user with such email from DB: true - admin, false - user and setting it to session
                    session.setAttribute("role", clientService.validateRole(req.getParameter("email")) ? "admin" : "user");
                    if (session.getAttribute("role").equals("user")) {
                        resp.sendRedirect("/user/start_page.jsp");
                    } else if (session.getAttribute("role").equals("admin")) {
                        resp.sendRedirect("/admin/admin_panel.jsp");
                    }
                } else {
                    //if pass doesn't match
                    req.setAttribute("message", "Wrong password!");
                    req.getRequestDispatcher("/index.jsp").forward(req, resp);
                }
                //if no user with such email is registered in db
            } else {
                req.setAttribute("message", "No user in DB! Try one more time!");
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
            //if no access to db - server side problem
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("message", "DB connectivity problem! Try one more time!");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
