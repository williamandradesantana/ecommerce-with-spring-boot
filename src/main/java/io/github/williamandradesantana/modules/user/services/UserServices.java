package io.github.williamandradesantana.modules.user.services;

import io.github.williamandradesantana.exceptions.EntityNotFoundException;
import io.github.williamandradesantana.exceptions.ValueAlreadyExists;
import io.github.williamandradesantana.modules.user.dto.UserRequestDTO;
import io.github.williamandradesantana.modules.user.entity.User;
import io.github.williamandradesantana.modules.user.repository.UserRepository;
import io.github.williamandradesantana.modules.user.validators.UserValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserValidators validators;

    public User create(UserRequestDTO dto) {
        var user = new User(dto);
        validators.validate(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        return repository.save(user);
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username);
    }
}
