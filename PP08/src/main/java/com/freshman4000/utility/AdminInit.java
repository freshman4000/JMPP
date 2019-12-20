package com.freshman4000.utility;

import com.freshman4000.dao.HibernateUserDAO;
import com.freshman4000.model.Role;
import com.freshman4000.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.Arrays;

@Component
public class AdminInit {
    @Autowired
    private HibernateUserDAO userRepository;

    private User admin = new User("admin", "admin", "admin@admin.com", "2000-01-01", "+70000000000", Arrays.asList(new Role("USER"), new Role("ADMIN")), "rootroot");
    private User user = new User("user", "user", "user@user.com", "2000-01-01", "+70000000000", Arrays.asList(new Role("USER")), "rootroot");

    public AdminInit() {
    }

    @PostConstruct
    public void initAdmin() {
        if (userRepository.getUserByUserName(admin.getEmail()) == null) { userRepository.addUser(admin);}
        if (userRepository.getUserByUserName(user.getEmail()) == null) { userRepository.addUser(user);}
    }
}
