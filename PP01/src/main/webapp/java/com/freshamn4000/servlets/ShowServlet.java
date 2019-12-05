package com.freshamn4000.servlets;

import com.freshamn4000.models.User;
import com.freshamn4000.services.ClientService;
import com.freshamn4000.utility.FormGenerator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/show")
public class ShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> result = ClientService.getInstance().showAllUsers();
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
