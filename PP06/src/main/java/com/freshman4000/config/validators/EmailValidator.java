package com.freshman4000.config.validators;

import com.freshman4000.model.User;
import com.freshman4000.service.ClientService;
import com.freshman4000.utility.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator implements UDValidator<User> {
    @Autowired
    private ClientService clientService;

    @Override
    public boolean validate(User user) throws CustomException {
        if (clientService.getUserByUserName(user.getEmail()) == null) {
            return true;
        } else throw new CustomException("There is an account with that email address: " + user.getEmail());
    }
}
