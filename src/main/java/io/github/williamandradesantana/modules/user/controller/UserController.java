package io.github.williamandradesantana.modules.user.controller;

import io.github.williamandradesantana.common.controller.GenericController;
import io.github.williamandradesantana.modules.user.dto.UserRequestDTO;
import io.github.williamandradesantana.modules.user.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/login")
public class UserController implements GenericController {

    @Autowired
    private UserServices services;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/")
    public ResponseEntity<Void> create(@RequestBody UserRequestDTO dto) {
        var user = services.create(dto);
        URI location = createHeaderLocation(user.getId());
        return ResponseEntity.created(location).build();
    }
}
