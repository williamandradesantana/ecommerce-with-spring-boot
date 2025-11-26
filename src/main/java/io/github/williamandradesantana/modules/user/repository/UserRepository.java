package io.github.williamandradesantana.modules.user.repository;

import io.github.williamandradesantana.modules.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String name);
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
