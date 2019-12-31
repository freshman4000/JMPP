package com.freshman4000.controllers;

import com.freshman4000.model.Role;
import com.freshman4000.model.User;
import com.freshman4000.service.ClientService;
import com.freshman4000.validators.UDValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class UserRestController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private UDValidator<User> udValidator;

    @GetMapping("/api/admin/show_all_users")
    public List<User> getAllUsers() {
        return clientService.showAllUsers();
    }
    @PostMapping("/api/admin/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        clientService.deleteUser(id);
    }
    @PostMapping("/api/admin/add_user")
    public void addUser(@RequestBody User user) {
        System.out.println(user);
            clientService.addUser(user);
        }
    }
