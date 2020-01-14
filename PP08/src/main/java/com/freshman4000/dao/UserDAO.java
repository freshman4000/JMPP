package com.freshman4000.dao;

import com.freshman4000.model.Role;
import com.freshman4000.model.User;

import java.util.List;
import java.util.Set;

/**
 * This interface represents DAO methods.
 */
public interface UserDAO {
    List<User> findAllUsers();

    void addUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);

    User getUserByUserName(String username);
    Set<Role> getRoles();
    void addRole(String name);
    Role getRoleByName(String name);
}