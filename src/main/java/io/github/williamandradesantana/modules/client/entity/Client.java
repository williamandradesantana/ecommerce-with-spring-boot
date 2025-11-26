package io.github.williamandradesantana.modules.client.entity;

import io.github.williamandradesantana.modules.client.dto.ClientRequestDTO;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "client_id", nullable = false, length = 150)
    private String clientId;

    @Column(name = "client_secret", nullable = false, length = 400)
    private String clientSecret;

    @Column(name = "redirect_uri", nullable = false, length = 200)
    private String redirectURI;

    @Column(name = "scope", length = 50)
    private String scope;

    public Client(){}
    public Client(UUID id, String clientId, String clientSecret, String redirectURI, String scope) {
        this.id = id;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectURI = redirectURI;
        this.scope = scope;
    }

    public Client(ClientRequestDTO dto) {
        this.clientId = dto.getClientId();
        this.clientSecret = dto.getClientSecret();
        this.redirectURI = dto.getRedirectURI();
        this.scope = dto.getScope();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectURI() {
        return redirectURI;
    }

    public void setRedirectURI(String redirectURI) {
        this.redirectURI = redirectURI;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
