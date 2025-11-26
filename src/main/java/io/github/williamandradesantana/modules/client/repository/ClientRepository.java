package io.github.williamandradesantana.modules.client.repository;

import io.github.williamandradesantana.modules.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findByClientId(String clientId);
}
