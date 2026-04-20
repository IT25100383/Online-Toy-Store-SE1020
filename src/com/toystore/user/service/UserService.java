package com.toystore.user.service;

import com.toystore.user.model.User;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class UserService {
    private final Path path = Paths.get("src/main/resources/users.txt");

    public void register(User user) throws IOException {
        String data = user.toCSV() + "\n";
        Files.writeString(path, data, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public boolean login(String username, String password) throws IOException {
        if (!Files.exists(path)) return false;
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
                return true;
            }
        }
        return false;
    }
}