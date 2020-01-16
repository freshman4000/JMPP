package com.freshman4000.controllers;

import com.freshman4000.model.Role;
import com.freshman4000.model.User;
import com.freshman4000.utility.AuthGetter;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class UserRestController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/api/admin/show_all_users")
    public List<User> getAllUsers() {

        return restTemplate.postForObject("http://localhost:8090/api/getAllUsers", AuthGetter.getEntity("jsonObject"), List.class);
    }

    @GetMapping("/api/admin/get_roles")
    public List<Role> getRoles() {
        return restTemplate.postForObject("http://localhost:8090/api/getAllRoles", AuthGetter.getEntity("jsonObject"), List.class);
    }

    @PostMapping("/api/admin/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<HttpStatus>(restTemplate.postForObject("http://localhost:8090/api/deleteUser/" + id, AuthGetter.getEntity("jsonObject"), HttpStatus.class));
    }

    @PostMapping({"/api/admin/add_user", "/api/admin/update_user"})
    public ResponseEntity<HttpStatus> addUser(@RequestBody User user) {
        Gson g = new Gson();
        return new ResponseEntity<>(restTemplate.postForObject("http://localhost:8090/api/addOrUpdateUser", AuthGetter.getEntity(g.toJson(user)), HttpStatus.class));
    }
}
