package com.toystore.user.model;

public class User {
    private final String username;
    private final String password;
    private final String email;
    private final String fullName; // Required 4th field

    // Updated constructor to accept exactly 4 arguments
    public User(String username, String password, String email, String fullName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }

    // Getters required for your CRUD and file operations
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getFullName() { return fullName; }

    // Formats data into 4 comma-separated fields for flat-file storage
    public String toCSV() {
        return username + "," + password + "," + email + "," + fullName;
    }
}