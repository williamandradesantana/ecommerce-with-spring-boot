package io.github.williamandradesantana.modules.user.validators;

import io.github.williamandradesantana.exceptions.ValueAlreadyExists;
import io.github.williamandradesantana.modules.user.entity.User;
import io.github.williamandradesantana.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidators {

    @Autowired
    private UserRepository repository;

    public void validate(User user) {
        if (existsByEmail(user)) throw new ValueAlreadyExists("email");
    }

    public boolean existsByEmail(User user) {
        return repository.existsByEmail(user.getEmail());
    }
}
