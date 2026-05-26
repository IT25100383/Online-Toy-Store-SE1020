package com.toystore.user.model;

/**
 * Model representing a User in the Toy Store system.
 * Handles both normal customers and administrators via the role field.
 */
public class User {

    private String userId;
    private String name;
    private String email;
    private String password;
    private String role;

    // CONSTRUCTOR
    public User(
            String userId,
            String name,
            String email,
            String password,
            String role
    ) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // GETTERS AND SETTERS
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // FILE CONVERSION METHODS

    /**
     * Converts the User object into a comma-separated string for file storage.
     * Format: userId,name,email,password,role
     */
    public String toFileString() {
        return userId + ","
                + name + ","
                + email + ","
                + password + ","
                + role;
    }

    /**
     * Creates a User object from a comma-separated string line from the text file.
     * @param line A line from users.txt
     * @return A new User object
     */
    public static User fromFileString(String line) {
        String[] parts = line.split(",");

        // Basic validation to prevent ArrayIndexOutOfBoundsException
        if (parts.length < 5) {
            return null;
        }

        return new User(
                parts[0], // userId
                parts[1], // name
                parts[2], // email
                parts[3], // password
                parts[4]  // role
        );
    }

    // Compatibility method for legacy Service calls
    public String toCSV() {
        return toFileString();
    }
}