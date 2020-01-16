package com.freshman4000.restserver.controllers;

import com.freshman4000.restserver.models.Role;
import com.freshman4000.restserver.models.User;
import com.freshman4000.restserver.repositories.RolesRepository;
import com.freshman4000.restserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@RestController
public class CRUDController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;

    @PostMapping({"/api/getAllUsers"})
    public @ResponseBody
    Iterable<User> returnAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/api/deleteUser/{id}")
    public @ResponseBody
    HttpStatus deleteUserById(@PathVariable("id") Long id) {
        HttpStatus httpStatus;
        try {
            userRepository.deleteById(id);
            httpStatus = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return httpStatus;
    }
    @PostMapping("/api/addOrUpdateUser")
    public @ResponseBody
    HttpStatus addOrUpdateUser(@RequestBody User user) {
        HttpStatus httpStatus;
        Optional<User> optionalUser = userRepository.findById(user.getId());
        User userForUpdate;
            if (optionalUser.isPresent()) {
                userForUpdate = optionalUser.get();
                String previousPassword = userForUpdate.getPassword();
                if (userRepository.findByEmail(user.getEmail()) == null || userRepository.findByEmail(user.getEmail()).getId() == userForUpdate.getId()) {

                        userForUpdate.setFirstname(user.getFirstname());
                        userForUpdate.setLastname(user.getLastname());
                        userForUpdate.setBirthdate(user.getBirthdate());
                        userForUpdate.setEmail(user.getEmail());
                        userForUpdate.setPhone(user.getPhone());
                        userForUpdate.setPassword(user.getPassword().equals("") ? previousPassword : user.getPassword());
                        Set<Role> setForUpdate = new HashSet<>();
                        Set<Role> newSet = user.getRoles();
                        for (Role entity : newSet) {
                            setForUpdate.add(rolesRepository.findByName(entity.getName()));
                        }
                        userForUpdate.setRoles(setForUpdate);

                    userRepository.save(userForUpdate);
                    httpStatus = HttpStatus.ACCEPTED;
                } else {
                    httpStatus = HttpStatus.BAD_REQUEST;
                }
            } else {
                Set<Role> setForAddition = new HashSet<>();
                Set<Role> newSet = user.getRoles();
                for (Role entity : newSet) {
                    setForAddition.add(rolesRepository.findByName(entity.getName()));
                }
                user.setRoles(setForAddition);
                userRepository.save(user);
                httpStatus = HttpStatus.ACCEPTED;
            }
        return httpStatus;
    }

    @PostMapping({"/api/getAllRoles"})
    public @ResponseBody
    Iterable<Role> returnAllRoles() {
        return rolesRepository.findAll();
    }

    @PostMapping({"/api/getAuth/{email}"})
    public @ResponseBody
    User returnUser(@PathVariable("email") String email) {
        return userRepository.findByEmail(email);
    }

}
