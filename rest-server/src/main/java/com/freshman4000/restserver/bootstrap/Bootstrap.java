package com.freshman4000.restserver.bootstrap;

import com.freshman4000.restserver.models.Role;
import com.freshman4000.restserver.models.User;
import com.freshman4000.restserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    loadAdmin();
    }

    private void loadAdmin() {
        Role ad = new Role("ADMIN");
        Role us = new Role("USER");
//        Role tl = new Role("TeamLead");
        User admin = new User("admin", "admin", "admin@admin.com", "2000-01-01", "+70000000000", new HashSet<>(), "rootroot");
        if (userRepository.findByEmail(admin.getEmail()) == null) {
            admin.getRoles().add(ad);
            admin.getRoles().add(us);
//            admin.getRoles().add(tl);
            userRepository.save(admin);
        }
    }
}
