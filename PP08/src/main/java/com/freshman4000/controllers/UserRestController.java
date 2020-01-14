package com.freshman4000.controllers;

import com.freshman4000.model.DTO;
import com.freshman4000.model.Role;
import com.freshman4000.model.User;
import com.freshman4000.service.ClientService;
import com.freshman4000.validators.UDValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @GetMapping("/api/admin/get_roles")
    public List<Role> getRoles() {
        return new ArrayList<>(clientService.getRoles());
    }

    @PostMapping("/api/admin/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {

        clientService.deleteUser(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/api/admin/add_user")
    public ResponseEntity<HttpStatus> addUser(@RequestBody User user) {
        HttpStatus result;
        if (udValidator.validate(user)) {
            Set<Role> avRoles = clientService.getRoles();
            Set<Role> newRoleList = new HashSet<>();
            for (Role role : avRoles) {
                for (Role usRoles : user.getRoles()) {
                    if (role.getName().equals(usRoles.getName())) {
                        newRoleList.add(role);
                    }
                }
            }
            user.setRoles(newRoleList);
            System.out.println(user);
            clientService.addUser(user);
            result = HttpStatus.ACCEPTED;
        } else {
            result = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(result);
    }

    @PostMapping(value = "/api/admin/update_user")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody DTO dtoUser) {
        HttpStatus result;
        boolean validated = true;
        if (!dtoUser.getPrevEmail().equals(dtoUser.getEmail())) {
            validated = udValidator.validate(dtoUser);
        }
        if (validated) {
            Set<Role> avRoles = clientService.getRoles();
            Set<Role> newRoleList = new HashSet<>();
            for (Role role : avRoles) {
                for (Role usRoles : dtoUser.getRoles()) {
                    if (role.getName().equals(usRoles.getName())) {
                        newRoleList.add(role);
                    }
                }
            }
            dtoUser.setRoles(newRoleList);
            if (dtoUser.getPassword().equals("")) {
                dtoUser.setPassword(dtoUser.getPrevPass().concat("same"));
            }
            User user = new User();
            user.setFirstname(dtoUser.getFirstname());
            user.setLastname(dtoUser.getLastname());
            user.setRoles(dtoUser.getRoles());
            user.setPassword(dtoUser.getPassword());
            user.setPhone(dtoUser.getPhone());
            user.setEmail(dtoUser.getEmail());
            user.setBirthdate(dtoUser.getBirthdate());
            user.setId(dtoUser.getId());
            clientService.updateUser(user);
            result = HttpStatus.ACCEPTED;
        } else {
            result = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(result);
    }
}
