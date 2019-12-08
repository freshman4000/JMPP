package com.freshamn4000.dao;

import com.freshamn4000.interfaces.UserDAO;
import com.freshamn4000.models.Registration;
import com.freshamn4000.models.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * This class is responsible for DB relationship, using Hibernate connection.
 */
public class UserHibernateDAO implements UserDAO<User, Long> {
    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }
    @Override
    public List<User> findAllUsers() {
        try {
            Query query = session.createQuery("from User");
            return (List<User>) query.list();
        } finally {
            session.close();
        }
    }

    private Long getMaxId() {
        Query query = session.createQuery("SELECT MAX(id) FROM User");
        return (Long) query.uniqueResult();
    }
    @Override
    public Long addUser(User user) {
        try {
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            return getMaxId();
        } finally {
            session.close();
        }
    }
    @Override
    public void deleteUser(Long userId) {
        Transaction tx = session.beginTransaction();
        Query query0 = session.createQuery("DELETE FROM Registration WHERE user.id = :id").setLong("id", userId);
        query0.executeUpdate();
        Query query = session.createQuery("DELETE FROM User WHERE id = :id").setLong("id", userId);
        query.executeUpdate();
        tx.commit();
        session.close();
    }
    @Override
    public void updateUser(Long userId, User user) {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("update User set first_name = :nameParam, last_name = :lastNameParam" +
                ", email = :emailParam, birth_day = :birthDateParam, phone_number = :phoneParam, role = :roleParam" +
                " where id = :idParam");
        Query query1 = session.createQuery("FROM User WHERE id = :id").setLong("id", userId);
        User existingUser = (User) query1.uniqueResult();
        query.setParameter("idParam", userId);
        query.setParameter("nameParam", user.getFirstName().isEmpty() || user.getFirstName() == null ? existingUser.getFirstName() : user.getFirstName());
        query.setParameter("lastNameParam", user.getLastName().isEmpty() || user.getLastName() == null ? existingUser.getLastName() : user.getLastName());
        query.setParameter("emailParam", user.getEmail().isEmpty() || user.getEmail() == null ? existingUser.getEmail() : user.getEmail());
        query.setParameter("birthDateParam", user.getBirthDate().isEmpty() || user.getBirthDate() == null ? existingUser.getBirthDate() : user.getBirthDate());
        query.setParameter("phoneParam", user.getPhoneNumber().isEmpty() || user.getPhoneNumber() == null ? existingUser.getPhoneNumber() : user.getPhoneNumber());
        query.setParameter("roleParam", user.getRole().isEmpty() || user.getRole() == null ? existingUser.getRole() : user.getRole());
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    @Override
    public void setPassword(Long field, String password, User user) throws SQLException {
        Transaction tx = session.beginTransaction();
        session.save(new Registration(password, user));
        tx.commit();
        session.close();
    }
    @Override
    public void updatePassword(Long field, String password) throws SQLException {
        Transaction tx = session.beginTransaction();
        Registration fetchedRegistration = (Registration)session.get(Registration.class, field);
        fetchedRegistration.setPassword(password);
        session.update(fetchedRegistration);
        tx.commit();
        session.close();
    }

    @Override
    public boolean validateRegistration(String email) throws SQLException {
        boolean result = (Long)session.createQuery("SELECT COUNT(*) FROM User WHERE email = :emailParam").setString("emailParam", email).uniqueResult() == 0;
        System.out.println(email);
        System.out.println(result);
        session.close();
        return result;
    }

    @Override
    public boolean validateLogin(String email, String password) throws SQLException {
        String fetchedPass = (String)session.createQuery("SELECT password FROM Registration WHERE user.id = (SELECT id FROM User WHERE email = :emailParam)").setParameter("emailParam", email).uniqueResult();
        session.close();
        return fetchedPass.equals(password);
    }

    @Override
    public boolean validateRole(String email, String password) throws SQLException {
        Query query = session.createQuery("SELECT role FROM User WHERE email = :emailParam AND id = (SELECT user.id FROM Registration WHERE password = :passwordParam)");
        query.setString("emailParam", email);
        query.setString("passwordParam", password);
        boolean result = query.uniqueResult().equals("admin");
        session.close();
        return result;
    }
}