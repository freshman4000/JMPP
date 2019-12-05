package com.freshamn4000.services;

import com.freshamn4000.dao.UserDAO;
import com.freshamn4000.models.User;
import com.freshamn4000.utility.DBLoader;
import org.hibernate.SessionFactory;

import java.util.List;

public class ClientService {
    private static ClientService clientService;
    private SessionFactory sessionFactory;

    private ClientService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static ClientService getInstance() {
        if (clientService == null) {
            clientService = new ClientService(DBLoader.getSessionFactory());
        }
        return clientService;
    }

    public List<User> showAllUsers() {
       return new UserDAO(ClientService.getInstance().sessionFactory.openSession()).findAllUsers();
    }
    public void addUser(User user) {
        new UserDAO(ClientService.getInstance().sessionFactory.openSession()).addUser(user);
    }
    public void deleteUser(Long userId) {
        new UserDAO(ClientService.getInstance().sessionFactory.openSession()).deleteUser(userId);
    }
    public void updateUser(Long userId, User user) {
        new UserDAO(ClientService.getInstance().sessionFactory.openSession()).updateUser(userId, user);
    }
}
