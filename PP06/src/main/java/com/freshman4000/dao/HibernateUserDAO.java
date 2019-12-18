package com.freshman4000.dao;

import com.freshman4000.model.Role;
import com.freshman4000.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
@Repository
@Transactional
public class HibernateUserDAO implements UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    public void initAdmin() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        User admin = new User("admin", "admin", "admin@admin.com", "2000-01-01", "+70000000000", Arrays.asList( new Role("USER"), new Role("ADMIN")), "rootroot");
        User user = new User("user", "user", "user@user.com", "2000-01-01", "+70000000000", Arrays.asList( new Role("USER")), "rootroot");
        admin.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        session.save(admin);
        session.save(user);
        tx.commit();
        session.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public void updateUser(User user) {
        if (user.getPassword().endsWith("same")) {
            user.setPassword(user.getPassword().substring(0, user.getPassword().length() - 4));
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public User getUserByUserName(String email) {
        return (User) sessionFactory.getCurrentSession().createQuery("from User where email = : emailParam").setParameter("emailParam", email).uniqueResult();
    }
}