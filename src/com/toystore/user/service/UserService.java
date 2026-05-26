package com.toystore.user.service;

import com.toystore.user.model.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final String FILE_PATH = "C:/Users/USER/Documents/EDU/Y1/Y1S2/OOP/Project/Toy Store Sandbox/data/users.txt";
    private List<User> userList = new ArrayList<>();

    public UserService() {
        loadUsers();
    }


    public void register(User user) {
        userList.add(user);
        saveUsers();
    }


    public List<User> getAllUsers() {
        return userList;
    }

    public boolean login(String username, String password) {
        for (User user : userList) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : userList) {
                writer.write(user.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
    private void loadUsers() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                try {
                    User user = User.fromFileString(line);
                    if (user != null) {
                        userList.add(user);
                    }
                } catch (Exception e) {
                    System.out.println("FAILED TO LOAD USER: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}