package com.toystore.admin.service;

import com.toystore.admin.model.Admin;

import java.io.*;
import java.util.*;

public class AdminService {
    private static final String FILE_NAME = "C:/Users/USER/Documents/EDU/Y1/Y1S2/OOP/Project/Toy Store Sandbox/data/admins.txt";

    public void addAdmin(Admin admin) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(admin.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error adding admin.");
        }
    }

    public void viewAdmins() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                System.out.println("Username: " + data[0] + ", Role: " + data[2]);
            }
        } catch (IOException e) {
            System.out.println("Error reading admins.");
        }
    }

    public void updateAdmin(String username, String newPassword, String newRole) {
        List<String> admins = new ArrayList<>();
        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(username)) {
                    admins.add(username + "," + newPassword + "," + newRole);
                    found = true;
                } else {
                    admins.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error updating admin.");
        }

        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            for (String admin : admins) fw.write(admin + "\n");
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    public void deleteAdmin(String username) {
        List<String> admins = new ArrayList<>();
        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (!data[0].equals(username)) admins.add(line);
                else found = true;
            }
        } catch (IOException e) {
            System.out.println("Error deleting admin.");
        }

        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            for (String admin : admins) fw.write(admin + "\n");
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }
}