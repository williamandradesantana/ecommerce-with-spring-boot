package io.github.williamandradesantana.modules.client.controller;

import io.github.williamandradesantana.common.controller.GenericController;
import io.github.williamandradesantana.modules.client.dto.ClientRequestDTO;
import io.github.williamandradesantana.modules.client.services.ClientServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/clients")
@Tag(name = "Clients")
public class ClientController implements GenericController {

    @Autowired
    private ClientServices services;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> create(@RequestBody ClientRequestDTO dto) {
        var client = services.create(dto);
        URI location = createHeaderLocation(client.getId());
        return ResponseEntity.created(location).build();
    }
}
