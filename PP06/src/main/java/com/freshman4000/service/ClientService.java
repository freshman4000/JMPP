package com.freshman4000.service;

import com.freshman4000.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This interface represents clients service methods.
 */
public interface ClientService {
    List<User> showAllUsers();

    void addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);
}
