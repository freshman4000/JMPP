package com.freshamn4000.dao;

import com.freshamn4000.interfaces.UserDAO;
import com.freshamn4000.models.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
/**
 * This class is responsible for DB relationship, using Hibernate connection.
 */
public class UserHibernateDAO implements UserDAO<User, Long> {
    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    public List<User> findAllUsers() {
        try {
            Query query = session.createQuery("from User");
            return (List<User>) query.list();
        } finally {
            session.close();
        }
    }

    public void addUser(User user) {
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
    }

    public void deleteUser(Long userId) {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User WHERE id = :id").setLong("id", userId);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public void updateUser(Long userId, User user) {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("update User set first_name = :nameParam, last_name = :lastNameParam" +
                ", email = :emailParam, birth_day = :birthDateParam, phone_number = :phoneParam" +
                " where id = :idParam");
        Query query1 = session.createQuery("FROM User WHERE id = :id").setLong("id", userId);
        User existingUser = (User) query1.uniqueResult();
        query.setParameter("idParam", userId);
        query.setParameter("nameParam", user.getFirstName().isEmpty() || user.getFirstName() == null ? existingUser.getFirstName() : user.getFirstName());
        query.setParameter("lastNameParam", user.getLastName().isEmpty() || user.getLastName() == null ? existingUser.getLastName() : user.getLastName());
        query.setParameter("emailParam", user.getEmail().isEmpty() || user.getEmail() == null ? existingUser.getEmail() : user.getEmail());
        query.setParameter("birthDateParam", user.getBirthDate().isEmpty() || user.getBirthDate() == null ? existingUser.getBirthDate() : user.getBirthDate());
        query.setParameter("phoneParam", user.getPhoneNumber().isEmpty() || user.getPhoneNumber() == null ? existingUser.getPhoneNumber() : user.getPhoneNumber());
        query.executeUpdate();
        tx.commit();
        session.close();
    }
}