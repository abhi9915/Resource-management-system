package com.example.rms;

public class UserDetails {
    public String username, email, password,approved;

    public String id;

    public UserDetails() {
    }

    public UserDetails(String username, String email, String password,String approved) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.approved = approved;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
