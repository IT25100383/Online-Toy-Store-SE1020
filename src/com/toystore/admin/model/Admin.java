package com.toystore.admin.model;

class Admin {
    private String username;
    private String password;
    private String role;

    // Constructor
    public Admin(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Convert to file format
    public String toString() {
        return username + "," + password + "," + role;
    }
}