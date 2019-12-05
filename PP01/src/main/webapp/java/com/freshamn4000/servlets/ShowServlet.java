package com.freshamn4000.servlets;

import com.freshamn4000.controllers.Controller;
import com.freshamn4000.interfaces.ClientService;
import com.freshamn4000.models.User;
import com.freshamn4000.utility.FormGenerator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/show")
public class ShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientService<User, Long> clientService = Controller.getClientService(req, resp);
        List<User> result = new ArrayList<>();
        try {
            result = clientService.showAllUsers();
        } catch (SQLException e) {
            req.setAttribute("message", "DB access problem! TRy one more time!");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
        StringBuilder sb = new StringBuilder();
        result.forEach(x -> sb
                .append(FormGenerator.getDeleteForm(x))
                .append(FormGenerator.getUpdateForm(x))
                .append(x.toString()).append("<br />"));
        String answer = sb.toString();
        req.setAttribute("message", answer.isEmpty() ? "Users DB is empty" : answer);
        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
        rd.forward(req, resp);
    }
}
