package com.freshman4000.service;

import com.freshman4000.model.User;

import java.util.List;

/**
 * This interface represents clients service methods.
 */
public interface ClientService {
    List<User> showAllUsers();

    void addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User getUserByUserName(String username);
}
