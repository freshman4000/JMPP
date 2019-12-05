package com.freshamn4000.dao;

import com.freshamn4000.interfaces.UserDAO;
import com.freshamn4000.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJDBCDao implements UserDAO<User, Long> {
    private Connection connection;

    public UserJDBCDao(Connection connection) {
        this.connection = connection;
    }

    private void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, birth_day varchar(256), email varchar(256), first_name varchar(256), last_name varchar(256),  phone_number varchar(256), primary key (id))");
        stmt.close();
    }

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
                        rst.getString(6));
                list.add(fetchedUser);
            }
            rst.close();
            return list;
        }
    }

    public void addUser(User user) throws SQLException {
        createTable();
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO users (first_name, last_name, email, birth_day, phone_number) VALUES (?, ?, ?, ?, ?)")) {
            pst.setString(1, user.getFirstName());
            pst.setString(2, user.getLastName());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getBirthDate());
            pst.setString(5, user.getPhoneNumber());
            pst.executeUpdate();
        }
    }

    public void deleteUser(Long userId) throws SQLException {
        System.out.println(userId);
        createTable();
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            pst.setLong(1, userId);
            pst.executeUpdate();
        }
    }

    public void updateUser(Long userId, User user) throws SQLException {
        createTable();
        try (PreparedStatement pst = connection.prepareStatement("UPDATE users SET first_name = ?, last_name = ?, email = ?, birth_day = ?, phone_number = ? WHERE id = ?");
             PreparedStatement pst0 = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            pst0.setLong(1, userId);
            ResultSet rst = pst0.executeQuery();
            rst.next();
            pst.setLong(6, userId);
            pst.setString(1, user.getFirstName().isEmpty() || user.getFirstName() == null ? rst.getString(4) : user.getFirstName());
            pst.setString(2, user.getLastName().isEmpty() || user.getLastName() == null ? rst.getString(5) : user.getLastName());
            pst.setString(3, user.getEmail().isEmpty() || user.getEmail() == null ? rst.getString(3) : user.getEmail());
            pst.setString(4, user.getBirthDate().isEmpty() || user.getBirthDate() == null ? rst.getString(2) : user.getBirthDate());
            pst.setString(5, user.getPhoneNumber().isEmpty() || user.getPhoneNumber() == null ? rst.getString(6) : user.getPhoneNumber());
            pst.executeUpdate();
        }
    }
}