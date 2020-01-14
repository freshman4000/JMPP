package com.freshman4000.dao;

import com.freshman4000.model.Role;
import com.freshman4000.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public void deleteUser(Long id) {
        entityManager.createQuery("DELETE from User u WHERE u.id = :idParam").setParameter("idParam", id).executeUpdate();
    }

    @Override
    public void updateUser(User user) {
        System.out.println("hello from dao");
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

    @Override
    public Set<Role> getRoles() {
        Query query = entityManager.createQuery("select u from Role u");

        List<Role> result = query.getResultList();
        return new HashSet<>(result);
    }
    @Override
    public void addRole(String name) {
        Role role = new Role(name);
        entityManager.persist(role);
    }
    @Override
    public Role getRoleByName(String name) {
        return entityManager.find(Role.class, name);
    }
}