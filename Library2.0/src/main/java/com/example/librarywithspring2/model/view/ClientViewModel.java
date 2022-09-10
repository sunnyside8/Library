package com.example.librarywithspring2.model.view;

import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "clients")
public class ClientViewModel {
    private Long id;
    private String clientName;
    private String email;
    private String username;

    public Long getId() {
        return id;
    }

    public ClientViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getClientName() {
        return clientName;
    }

    public ClientViewModel setClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ClientViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ClientViewModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
