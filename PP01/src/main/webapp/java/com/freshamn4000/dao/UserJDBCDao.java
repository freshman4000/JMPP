package com.freshamn4000.dao;

import com.freshamn4000.interfaces.UserDAO;
import com.freshamn4000.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for DB relationship, using JDBC connection.
 */
public class UserJDBCDao implements UserDAO<User, Long> {
    private Connection connection;

    public UserJDBCDao(Connection connection) {
        this.connection = connection;
    }

    private void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, birth_day varchar(256), email varchar(256), first_name varchar(256), last_name varchar(256),  phone_number varchar(256), role varchar(256), primary key (id))");
        stmt.close();
    }

    private void createPassTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists registered (id bigint auto_increment, password varchar(256), user_id bigint, PRIMARY KEY (id), FOREIGN KEY (user_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE);");
        stmt.close();
    }
    @Override
    public List<User> findAllUsers() throws SQLException {
        createTable();
        List<User> list = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rst = st.executeQuery("SELECT * FROM users");
            while (rst.next()) {
                User fetchedUser = new User(
                        rst.getLong(1),
                        rst.getString(4),
                        rst.getString(5),
                        rst.getString(3),
                        rst.getString(2),
                        rst.getString(6),
                        rst.getString(7));
                list.add(fetchedUser);
            }
            rst.close();
            return list;
        }
    }

    private Long getMaxId() throws SQLException {
        Long result;
        try (Statement st = connection.createStatement()) {
            ResultSet rst = st.executeQuery("SELECT MAX(id) FROM users");
            rst.next();
            result = rst.getLong(1);
            rst.close();
        }
        return result;
    }
    @Override
    public Long addUser(User user) throws SQLException {
        createTable();
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO users (first_name, last_name, email, birth_day, phone_number, role) VALUES (?, ?, ?, ?, ?, ?)")) {
            pst.setString(1, user.getFirstName());
            pst.setString(2, user.getLastName());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getBirthDate());
            pst.setString(5, user.getPhoneNumber());
            pst.setString(6, user.getRole());
            pst.executeUpdate();
        }
        return getMaxId();
    }
    @Override
    public void deleteUser(Long userId) throws SQLException {
        createTable();
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            pst.setLong(1, userId);
            pst.executeUpdate();
        }
    }
    @Override
    public void updateUser(Long userId, User user) throws SQLException {
        createTable();
        try (PreparedStatement pst = connection.prepareStatement("UPDATE users SET first_name = ?, last_name = ?, email = ?, birth_day = ?, phone_number = ?, role = ? WHERE id = ?");
             PreparedStatement pst0 = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            pst0.setLong(1, userId);
            ResultSet rst = pst0.executeQuery();
            rst.next();
            pst.setLong(7, userId);
            pst.setString(1, user.getFirstName().isEmpty() || user.getFirstName() == null ? rst.getString(4) : user.getFirstName());
            pst.setString(2, user.getLastName().isEmpty() || user.getLastName() == null ? rst.getString(5) : user.getLastName());
            pst.setString(3, user.getEmail().isEmpty() || user.getEmail() == null ? rst.getString(3) : user.getEmail());
            pst.setString(4, user.getBirthDate().isEmpty() || user.getBirthDate() == null ? rst.getString(2) : user.getBirthDate());
            pst.setString(5, user.getPhoneNumber().isEmpty() || user.getPhoneNumber() == null ? rst.getString(6) : user.getPhoneNumber());
            pst.setString(6, user.getRole().isEmpty() || user.getRole() == null ? rst.getString(7) : user.getRole());
            pst.executeUpdate();
            rst.close();
        }
    }
    @Override
    public void setPassword(Long field, String password, User user) throws SQLException {
        createPassTable();
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO registered SET user_id = ?, password = ?")) {
            pst.setLong(1, field);
            pst.setString(2, password);
            pst.executeUpdate();
        }
    }
    @Override
    public void updatePassword(Long field, String password) throws SQLException {
        createPassTable();
        try (PreparedStatement pst = connection.prepareStatement("UPDATE registered SET password = ? WHERE user_id = ?")) {
            pst.setLong(2, field);
            pst.setString(1, password);
            pst.executeUpdate();
        }
    }

    @Override
    public boolean validateRegistration(String email) throws SQLException {
        createTable();
        createPassTable();
        boolean result = false;
        try (PreparedStatement pst = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE email = ?")) {
            pst.setString(1, email);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                result = rst.getLong(1) == 0;
            }
            rst.close();
        }
        return result;
    }

    @Override
    public boolean validateLogin(String email, String password) throws SQLException {
        createTable();
        createPassTable();
        boolean result = false;
        try (PreparedStatement pst = connection.prepareStatement("SELECT password FROM registered WHERE user_id = (" +
                "SELECT id FROM users WHERE email = ?)")) {
            pst.setString(1, email);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                result = rst.getString(1).equals(password);
            }
            rst.close();
        }
        return result;
    }

    @Override
    public boolean validateRole(String email) throws SQLException {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT role FROM users WHERE email = ?")) {
            preparedStatement.setString(1, email);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                result = rst.getString(1).equals("admin");
            }
            rst.close();
        }
        return result;
    }
}