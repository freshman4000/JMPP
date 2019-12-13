package com.freshman4000.service;

import com.freshman4000.dao.UserDAO;
import com.freshman4000.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> showAllUsers() {
        return userDAO.findAllUsers();
    }

    @Override
    public void addUser(User entity) {
        userDAO.addUser(entity);
    }

    @Override
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    @Override
    public void updateUser(User entity) {
        userDAO.updateUser(entity);
    }
}