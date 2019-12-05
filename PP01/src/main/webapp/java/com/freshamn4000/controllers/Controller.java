package com.freshamn4000.controllers;

import com.freshamn4000.interfaces.ClientService;
import com.freshamn4000.models.User;
import com.freshamn4000.services.HibernateClientService;
import com.freshamn4000.services.JDBCClientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.Properties;

public class Controller {

    public static ClientService<User, Long> getClientService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientService<User, Long> clientService = null;
        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
        try {
            Properties properties = new Properties();
            InputStream inputStream = Controller.class.getClassLoader().getResourceAsStream("hibernate.properties");
            properties.load(inputStream);
            switch (properties.getProperty("fetching.type")) {
                case "jdbc":
                    clientService = JDBCClientService.getInstance(
                            properties.getProperty("hibernate.connection.driver_class"),
                            properties.getProperty("hibernate.connection.url"),
                            properties.getProperty("hibernate.connection.username"),
                            properties.getProperty("hibernate.connection.password"));
                    break;
                default:
                    clientService = HibernateClientService.getInstance();
            }
        } catch (SQLException e) {
            req.setAttribute("message", "DB connectivity problem.");
            rd.forward(req, resp);
        } catch (ClassNotFoundException e) {
            req.setAttribute("message", "Check driver property.");
            rd.forward(req, resp);
        }
        return clientService;
    }
}