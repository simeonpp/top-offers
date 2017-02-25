package com.topoffers.topoffers.common.models;

import com.orm.SugarRecord;

public class AuthenticationCookie extends SugarRecord {
    private String username;
    private String role;

    // Sugar need default constructor
    public AuthenticationCookie() {
    }

    public AuthenticationCookie(long id, String username, String role) {
        this.setId(id);
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
