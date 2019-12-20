package com.freshman4000.validators;

import com.freshman4000.model.User;
import com.freshman4000.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator implements UDValidator<User> {
    @Autowired
    private ClientService clientService;

    @Override
    public boolean validate(User user) {
        boolean result = false;
        if (clientService.getUserByUserName(user.getEmail()) == null) {
            result = true;
        }
        return result;
    }
}