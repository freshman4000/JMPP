package com.freshman4000.dao;

import com.freshman4000.model.Role;
import com.freshman4000.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Repository
@Transactional
public class HibernateUserDAO implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Query query = entityManager.createQuery("select u from User u");

        return query.getResultList();
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    @Override
    public void updateUser(User user) {
        if (user.getPassword().endsWith("same")) {
            user.setPassword(user.getPassword().substring(0, user.getPassword().length() - 4));
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        entityManager.merge(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByUserName(String email) {
        List<User> result =(List<User>)entityManager.createQuery("SELECT u from User u where u.email = : emailParam").setParameter("emailParam", email).getResultList();
        return result.size() == 0 ? null : result.get(0);
    }
}