package io.github.williamandradesantana.modules.client.dto;


import io.github.williamandradesantana.modules.client.entity.Client;

public class ClientRequestDTO {
    private String clientId;
    private String clientSecret;
    private String redirectURI;
    private String scope;

    public ClientRequestDTO(){}
    public ClientRequestDTO(String clientId, String clientSecret, String redirectURI, String scope) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectURI = redirectURI;
        this.scope = scope;
    }

    public ClientRequestDTO(Client entity) {
        this.clientId = entity.getClientId();
        this.clientSecret = entity.getClientSecret();
        this.redirectURI = entity.getRedirectURI();
        this.scope = entity.getScope();
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
