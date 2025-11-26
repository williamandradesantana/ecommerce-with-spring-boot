package io.github.williamandradesantana.modules.client.dto;


import io.github.williamandradesantana.modules.client.entity.Client;

import java.util.UUID;

public class ClientResponseDTO {
    private UUID id;
    private String clientId;
    private String clientSecret;
    private String redirectURI;
    private String scope;

    public ClientResponseDTO(){}
    public ClientResponseDTO(UUID id, String clientId, String clientSecret, String redirectURI, String scope) {
        this.id = id;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectURI = redirectURI;
        this.scope = scope;
    }

    public ClientResponseDTO(Client entity) {
        this.clientId = entity.getClientId();
        this.clientSecret = entity.getClientSecret();
        this.redirectURI = entity.getRedirectURI();
        this.scope = entity.getScope();
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
