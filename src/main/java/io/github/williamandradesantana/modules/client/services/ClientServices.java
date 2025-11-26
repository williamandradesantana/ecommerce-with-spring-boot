package io.github.williamandradesantana.modules.client.services;

import io.github.williamandradesantana.modules.client.dto.ClientRequestDTO;
import io.github.williamandradesantana.modules.client.entity.Client;
import io.github.williamandradesantana.modules.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientServices {

    @Autowired
    private ClientRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Client create(ClientRequestDTO dto) {
        var client = new Client(dto);
        var clientSecret = client.getClientSecret();

        client.setClientSecret(passwordEncoder.encode(clientSecret));
        return repository.save(client);
    }

    public Client getByClientId(String clientId) {
        return repository.findByClientId(clientId);
    }
}
