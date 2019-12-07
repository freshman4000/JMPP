package com.freshamn4000.servlets;

import com.freshamn4000.controllers.UserDaoFactory;
import com.freshamn4000.interfaces.ClientService;
import com.freshamn4000.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientService<User, Long> clientService = UserDaoFactory.getClientService(req, resp);
        try {
        if (clientService.validateRegistration(req.getParameter("email"))) {
            if (!req.getParameter("password1").equalsIgnoreCase(req.getParameter("password2"))) {
                req.setAttribute("message", "Password should match! Try one more time!");
                req.getRequestDispatcher("/registration.jsp").forward(req, resp);
            } else {

                String firstName = req.getParameter("username");
                String lastName = req.getParameter("lastname");
                String email = req.getParameter("email");
                String birthDate = req.getParameter("birthdate");
                String phone = req.getParameter("phone");
                String password = req.getParameter("password1");
                if (!firstName.isEmpty()
                        && !lastName.isEmpty()
                        && !email.isEmpty()
                        && !birthDate.isEmpty()
                        && !phone.isEmpty()
                        && !password.isEmpty()) {
                    User user = new User(firstName, lastName, email, birthDate, phone, "user");
                    try {
                        Long id = clientService.addUser(user);
                        clientService.setPassword(id, password, user);
                        req.getRequestDispatcher("/login.jsp").forward(req, resp);
                    } catch (SQLException e) {
                        req.setAttribute("message", "DB access problem! Try one more time!");
                        req.getRequestDispatcher("/index.jsp").forward(req, resp);
                    }
                } else {
                    req.setAttribute("message", "All fields should be filled. Please repeat entry!");
                    RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
                    rd.forward(req, resp);
                }
            }
        } else {
            req.setAttribute("message", "This email is registered already!");
            RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
            rd.forward(req, resp);
        }
    } catch (SQLException e) {
            req.setAttribute("message", "DB access problem! Try one more time!");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/registration.jsp");
    }
}
