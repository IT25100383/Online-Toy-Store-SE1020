package com.toystore.user.service;

import com.toystore.user.model.User;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    // Local storage file path inside resources folder
    private final Path path = Paths.get("data/users.txt");

    // =========================================================================
    // C - CREATE (Register)
    // =========================================================================
    public void register(User user) throws IOException {
        String data = user.toCSV() + "\n";
        // CREATE builds file if missing; APPEND safely adds data to the end
        Files.writeString(path, data, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    // =========================================================================
    // R - READ (Login)
    // =========================================================================
    public boolean login(String username, String password) throws IOException {
        if (!Files.exists(path)) return false;

        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            String[] parts = line.split(",");

            // Verifies structural integrity (4 fields) before matching credentials
            if (parts.length >= 4 && parts[0].equals(username) && parts[1].equals(password)) {
                return true;
            }
        }
        return false;
    }

    // =========================================================================
    // R - READ ALL (Fetch User Registry)
    // =========================================================================
    public List<User> getAllUsers() throws IOException {
        List<User> users = new ArrayList<>();

        if (!Files.exists(path)) {
            return users;
        }

        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            String[] parts = line.split(",");

            // Ensures line contains all 4 required fields before object instantiation
            if (parts.length >= 4) {
                User user = new User(
                        parts[0], // username
                        parts[1], // password
                        parts[2], // email
                        parts[3]  // fullName
                );
                users.add(user);
            }
        }
        return users;
    }

    // =========================================================================
    // U - UPDATE (Modify Profile)
    // =========================================================================
    public boolean updateUserProfile(String username, String newEmail, String newFullName) throws IOException {
        if (!Files.exists(path)) return false;

        List<User> allUsers = getAllUsers();
        List<String> updatedLines = new ArrayList<>();
        boolean isUpdated = false;

        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                // Reconstructs the row with updated parameters while retaining password
                String updatedLine = user.getUsername() + "," + user.getPassword() + "," + newEmail + "," + newFullName;
                updatedLines.add(updatedLine);
                isUpdated = true;
            } else {
                updatedLines.add(user.toCSV());
            }
        }

        if (isUpdated) {
            // Overwrites the entire file with the updated memory collection
            Files.write(path, updatedLines);
        }
        return isUpdated;
    }

    // =========================================================================
    // D - DELETE (Remove Account)
    // =========================================================================
    public boolean deleteUser(String username) throws IOException {
        if (!Files.exists(path)) return false;

        List<User> allUsers = getAllUsers();
        List<String> updatedLines = new ArrayList<>();
        boolean isDeleted = false;

        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                // Skips adding this specific user to the list to remove it from file data
                isDeleted = true;
            } else {
                updatedLines.add(user.toCSV());
            }
        }

        if (isDeleted) {
            Files.write(path, updatedLines);
        }
        return isDeleted;
    }
}