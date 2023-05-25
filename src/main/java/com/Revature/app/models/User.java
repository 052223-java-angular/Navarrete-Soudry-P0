package com.Revature.app.models;

import java.util.UUID;

public class User {
    private String id;
    private String username;
    private String password;

    public User () {
        this.id = "";
        this.username = "";
        this.password = "";
    }

    public User (String username, String password) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return this.id;
    }
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
}
