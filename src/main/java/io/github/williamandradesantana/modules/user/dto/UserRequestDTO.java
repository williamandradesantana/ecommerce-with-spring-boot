package io.github.williamandradesantana.modules.user.dto;

import io.github.williamandradesantana.modules.user.entity.User;

public record UserRequestDTO(
        String username,
        String email,
        String password,
        String role
) {
    public UserRequestDTO(User user) {
        this(user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
    }
}
