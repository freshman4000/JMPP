package com.freshamn4000.services;

import com.freshamn4000.dao.UserJDBCDao;
import com.freshamn4000.interfaces.ClientService;
import com.freshamn4000.models.User;
import com.freshamn4000.utility.DBLoader;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCClientService implements ClientService<User, Long> {
    private static JDBCClientService jdbcClientService;
    private Connection connection;

    private JDBCClientService(Connection connection) {
        this.connection = connection;
    }

    public static JDBCClientService getInstance(String driverName, String connectionUrl, String login, String pass) throws SQLException, ClassNotFoundException {
        if (jdbcClientService == null) {
            jdbcClientService = new JDBCClientService(DBLoader.getConnection(driverName, connectionUrl, login, pass));
        }
        return jdbcClientService;
    }

    @Override
    public List<User> showAllUsers() throws SQLException {
        return new UserJDBCDao(connection).findAllUsers();
    }

    @Override
    public void addUser(User entity) throws SQLException {
        new UserJDBCDao(connection).addUser(entity);
    }

    @Override
    public void deleteUser(Long field) throws SQLException {
        new UserJDBCDao(connection).deleteUser(field);
    }

    @Override
    public void updateUser(Long field, User entity) throws SQLException {
        new UserJDBCDao(connection).updateUser(field, entity);
    }
}
