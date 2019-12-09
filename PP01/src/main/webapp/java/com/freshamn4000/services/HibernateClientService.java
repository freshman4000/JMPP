package com.freshamn4000.services;

import com.freshamn4000.dao.UserHibernateDAO;
import com.freshamn4000.interfaces.ClientService;
import com.freshamn4000.models.User;
import com.freshamn4000.utility.DBHelper;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.SQLException;
import java.util.List;

/**
 * This class represents methods, that call UserHibernateDAO methods, providing session instance object.
 */
public class HibernateClientService implements ClientService<User, Long> {
    private static HibernateClientService hibernateClientService;
    private static Configuration configuration = DBHelper.getInstance().getConfiguration();
    private static SessionFactory sessionFactory = getSessionFactory();

    public static HibernateClientService getInstance() {
        if (hibernateClientService == null) {
            hibernateClientService = new HibernateClientService();
        }
        return hibernateClientService;
    }

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
            builder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = builder.build();
            return configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

    public List<User> showAllUsers() {
        HibernateClientService.getInstance();
        return new UserHibernateDAO(sessionFactory.openSession()).findAllUsers();
    }

    public Long addUser(User user) {
        HibernateClientService.getInstance();
      return new UserHibernateDAO(sessionFactory.openSession()).addUser(user);
    }

    public void deleteUser(Long userId) {
        HibernateClientService.getInstance();
        new UserHibernateDAO(sessionFactory.openSession()).deleteUser(userId);
    }

    public void updateUser(Long userId, User user) {
        HibernateClientService.getInstance();
        new UserHibernateDAO(sessionFactory.openSession()).updateUser(userId, user);
    }

    @Override
    public void setPassword(Long field, String password, User user) throws SQLException {
    new UserHibernateDAO(sessionFactory.openSession()).setPassword(field, password, user);
    }

    @Override
    public void updatePassword(Long field, String password) throws SQLException {
    new UserHibernateDAO(sessionFactory.openSession()).updatePassword(field, password);
    }

    @Override
    public boolean validateRegistration(String email) throws SQLException {
        return new UserHibernateDAO(sessionFactory.openSession()).validateRegistration(email);
    }

    @Override
    public boolean validateLogin(String email, String password) throws SQLException {
        return new UserHibernateDAO(sessionFactory.openSession()).validateLogin(email, password);
    }

    @Override
    public boolean validateRole(String email) throws SQLException {
        return new UserHibernateDAO(sessionFactory.openSession()).validateRole(email);
    }
}