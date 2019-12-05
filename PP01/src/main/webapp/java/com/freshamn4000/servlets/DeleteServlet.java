package com.freshamn4000.servlets;

import com.freshamn4000.services.ClientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/deleteUser")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //avoided validation for ip inlining button delete into appearance
        ClientService.getInstance().deleteUser(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect("/show");
    }
}
