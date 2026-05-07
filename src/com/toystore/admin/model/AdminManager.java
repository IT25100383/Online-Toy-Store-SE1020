package com.toystore.admin.model;

import java.io.*;
import java.util.*;

public class AdminManager {

    private static final String FILE_NAME = "admins.txt";

    // CREATE - Add new admin
    public void addAdmin(Admin admin) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(admin.toString() + "\n");
            System.out.println("Admin added successfully.");
        } catch (IOException e) {
            System.out.println("Error adding admin.");
        }
    }

    // READ - View all admins
    public void viewAdmins() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("Admin List:");
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                System.out.println("Username: " + data[0] +
                        ", Role: " + data[2]);
            }
        } catch (IOException e) {
            System.out.println("Error reading admins.");
        }
    }

    // UPDATE - Modify admin password or role
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

        // Rewrite file
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            for (String admin : admins) {
                fw.write(admin + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }

        if (found) {
            System.out.println("Admin updated successfully.");
        } else {
            System.out.println("Admin not found.");
        }
    }

    // DELETE - Remove admin
    public void deleteAdmin(String username) {
        List<String> admins = new ArrayList<>();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (!data[0].equals(username)) {
                    admins.add(line);
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting admin.");
        }

        // Rewrite file
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            for (String admin : admins) {
                fw.write(admin + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }

        if (found) {
            System.out.println("Admin deleted successfully.");
        } else {
            System.out.println("Admin not found.");
        }
    }
}