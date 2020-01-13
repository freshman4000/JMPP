package com.freshman4000.utility;

import com.freshman4000.dao.HibernateUserDAO;
import com.freshman4000.model.Role;
import com.freshman4000.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class AdminInit {
    @Autowired
    private HibernateUserDAO userRepository;

    private Role ad = new Role("ADMIN");

    private Role us = new Role("USER");

    private User admin = new User("admin", "admin", "admin@admin.com", "2000-01-01", "+70000000000", new HashSet<>(), "rootroot");
//    private User user = new User("user", "user", "user@user.com", "2000-01-01", "+70000000000", Arrays.asList(us), "rootroot");

    public AdminInit() {
    }

    @PostConstruct
    public void initAdmin() {
        if (userRepository.getUserByUserName(admin.getEmail()) == null) {
            admin.getRoles().add(ad);
            admin.getRoles().add(us);
            userRepository.addUser(admin);}
//        if (userRepository.getUserByUserName(user.getEmail()) == null) { userRepository.addUser(user);}
    }
}
