package com.freshman4000.service;

import com.freshman4000.model.Role;
import com.freshman4000.model.User;

import java.util.List;
import java.util.Set;

/**
 * This interface represents clients service methods.
 */
public interface ClientService {
    List<User> showAllUsers();

    void addUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);

    User getUserByUserName(String username);
    Set<Role> getRoles();
}
