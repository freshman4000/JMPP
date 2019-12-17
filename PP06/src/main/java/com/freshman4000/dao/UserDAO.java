package com.freshman4000.dao;

import com.freshman4000.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This interface represents DAO methods.
 */
public interface UserDAO {
    List<User> findAllUsers();

    void addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User getUserByUserName(String username);
}