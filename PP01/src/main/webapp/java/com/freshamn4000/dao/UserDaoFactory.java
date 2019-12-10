package com.freshamn4000.dao;

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

/**
 * This class has method? that loads and checks properties file for "fetching.type" attribute and
 * supplies UserService, needed for DB connectivity. For example, if fetching.type is jdbc - this class will produce
 * JDBCClientService, in case of "hibernate" - HibernateClientService.
 */
public class UserDaoFactory extends DAOFactory<User, Long> {

    @Override
    public ClientService<User, Long> getDAO() {
        return createDAO();
    }
    @Override
    public ClientService<User, Long> createDAO() {
        ClientService<User, Long> clientService = null;
        try {
            Properties properties = new Properties();
            InputStream inputStream = UserDaoFactory.class.getClassLoader().getResourceAsStream("hibernate.properties");
            properties.load(inputStream);
            switch (properties.getProperty("dao.type")) {
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
        } catch (SQLException | ClassNotFoundException | IOException e) {
        e.printStackTrace();
        }
        return clientService;
    }
}