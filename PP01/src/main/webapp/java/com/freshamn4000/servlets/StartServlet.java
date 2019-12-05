package com.freshamn4000.servlets;

import com.freshamn4000.controllers.Controller;
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

@WebServlet("/add_user")
public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("add_user.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientService<User, Long> clientService = Controller.getClientService(req, resp);
        String firstName = req.getParameter("username");
        String lastName = req.getParameter("lastname");
        String email = req.getParameter("email");
        String birthDate = req.getParameter("birthdate");
        String phone = req.getParameter("phone");
        if (!firstName.isEmpty()
                && !lastName.isEmpty()
                && !email.isEmpty()
                && !birthDate.isEmpty()
                && !phone.isEmpty()) {
            User user = new User(firstName, lastName, email, birthDate, phone);
            try {
                clientService.addUser(user);
            } catch (SQLException e) {
                req.setAttribute("message", "DB access problem! TRy one more time!");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
            resp.sendRedirect("/show");
        } else {
            req.setAttribute("message", "All fields should be filled. Please repeat entry!");
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            rd.forward(req, resp);
        }
    }
}
