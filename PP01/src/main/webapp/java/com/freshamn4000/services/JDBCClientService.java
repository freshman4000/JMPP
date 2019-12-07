package com.freshamn4000.services;

import com.freshamn4000.dao.UserJDBCDao;
import com.freshamn4000.interfaces.ClientService;
import com.freshamn4000.models.User;
import com.freshamn4000.utility.DBHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * This class represents methods, that call UserJDBCDAO methods, providing connection instance object.
 */
public class JDBCClientService implements ClientService<User, Long> {
    private static JDBCClientService jdbcClientService;
    private Connection connection;

    private JDBCClientService(Connection connection) {
        this.connection = connection;
    }

    public static JDBCClientService getInstance(String driverName, String connectionUrl, String login, String pass) throws SQLException, ClassNotFoundException {
        if (jdbcClientService == null) {
            jdbcClientService = new JDBCClientService(DBHelper.getInstance().getConnection(driverName, connectionUrl, login, pass));
        }
        return jdbcClientService;
    }

    @Override
    public List<User> showAllUsers() throws SQLException {
        return new UserJDBCDao(connection).findAllUsers();
    }

    @Override
    public Long addUser(User entity) throws SQLException {
      return new UserJDBCDao(connection).addUser(entity);
    }

    @Override
    public void deleteUser(Long field) throws SQLException {
        new UserJDBCDao(connection).deleteUser(field);
    }

    @Override
    public void updateUser(Long field, User entity) throws SQLException {
        new UserJDBCDao(connection).updateUser(field, entity);
    }

    @Override
    public void setPassword(Long field, String password, User user) throws SQLException {
        new UserJDBCDao(connection).setPassword(field, password, user);
    }

    @Override
    public void updatePassword(Long field, String password) throws SQLException {
        new UserJDBCDao(connection).updatePassword(field, password);
    }

    @Override
    public boolean validateRegistration(String email) throws SQLException {
        return new UserJDBCDao(connection).validateRegistration(email);
    }

    @Override
    public boolean validateLogin(String email, String password) throws SQLException {
        return new UserJDBCDao(connection).validateLogin(email, password);
    }

    @Override
    public boolean validateRole(String email, String password) throws SQLException {
        return new UserJDBCDao(connection).validateRole(email, password);
    }
}
