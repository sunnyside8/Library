package com.example.librarywithspring2.model.validation;

import com.example.librarywithspring2.repository.ClientRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String>{

    private final ClientRepository clientRepository;

    public UniqueUsernameValidator(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return clientRepository.findClientByEmail(value).isEmpty();
    }
}
