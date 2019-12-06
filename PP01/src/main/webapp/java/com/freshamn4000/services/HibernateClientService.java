package com.freshamn4000.services;

import com.freshamn4000.dao.UserHibernateDAO;
import com.freshamn4000.interfaces.ClientService;
import com.freshamn4000.models.User;
import com.freshamn4000.utility.DBLoader;
import org.hibernate.SessionFactory;
import java.util.List;

/**
 * This class represents methods, that call UserHibernateDAO methods, providing session instance object.
 */
public class HibernateClientService implements ClientService<User, Long> {
    private static HibernateClientService hibernateClientService;
    private SessionFactory sessionFactory;

    private HibernateClientService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static HibernateClientService getInstance() {
        if (hibernateClientService == null) {
            hibernateClientService = new HibernateClientService(DBLoader.getSessionFactory());
        }
        return hibernateClientService;
    }

    public List<User> showAllUsers() {
        return new UserHibernateDAO(HibernateClientService.getInstance().sessionFactory.openSession()).findAllUsers();
    }

    public void addUser(User user) {
        new UserHibernateDAO(HibernateClientService.getInstance().sessionFactory.openSession()).addUser(user);
    }

    public void deleteUser(Long userId) {
        new UserHibernateDAO(HibernateClientService.getInstance().sessionFactory.openSession()).deleteUser(userId);
    }

    public void updateUser(Long userId, User user) {
        new UserHibernateDAO(HibernateClientService.getInstance().sessionFactory.openSession()).updateUser(userId, user);
    }
}
