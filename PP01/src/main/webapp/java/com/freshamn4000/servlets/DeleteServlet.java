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

@WebServlet("/deleteUser")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientService<User, Long> clientService = Controller.getClientService(req, resp);
        try {
            clientService.deleteUser(Long.parseLong(req.getParameter("id")));
        } catch (SQLException e) {
            req.setAttribute("message", "DB access problem! Try one more time!");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
        resp.sendRedirect("/show");
    }
}
