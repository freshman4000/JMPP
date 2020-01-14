package com.freshman4000.utility;

import com.freshman4000.dao.HibernateUserDAO;
import com.freshman4000.model.Role;
import com.freshman4000.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.HashSet;

@Component
public class AdminInit {
    @Autowired
    private HibernateUserDAO userRepository;

    private Role ad = new Role("ADMIN");
    private Role us = new Role("USER");
    private Role ma = new Role("MAN");
    private Role di = new Role("DIR");

    private User admin = new User("admin", "admin", "admin@admin.com", "2000-01-01", "+70000000000", new HashSet<>(), "rootroot");

    public AdminInit() {
    }

    @PostConstruct
    public void initAdmin() {

        if (userRepository.getUserByUserName(admin.getEmail()) == null) {
            admin.getRoles().add(ad);
            admin.getRoles().add(us);
            admin.getRoles().add(ma);
            admin.getRoles().add(di);

            userRepository.addUser(admin);}
    }
}
