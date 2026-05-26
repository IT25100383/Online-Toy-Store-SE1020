package com.toystore.admin.service;

import com.toystore.admin.model.Admin;
import java.io.*;
import java.util.*;

public class AdminService {

    private static final String FILE_NAME = "C:/Users/USER/Documents/EDU/Y1/Y1S2/OOP/Project/Toy Store Sandbox/data/admins.txt";

    // ADD ADMIN
    public void addAdmin(Admin admin) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(admin.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error adding admin.");
        }
    }

    // GET ALL ADMINS
    public List<Admin> getAllAdmins() {
        List<Admin> adminList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    Admin admin = new Admin(
                            data[0],
                            data[1],
                            data[2]
                    );
                    adminList.add(admin);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading admins.");
        }
        return adminList;
    }

    // GET ADMIN BY USERNAME
    public Admin getAdminByUsername(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(username)) {
                    return new Admin(
                            data[0],
                            data[1],
                            data[2]
                    );
                }
            }
        } catch (IOException e) {
            System.out.println("Error finding admin.");
        }
        return null;
    }

    // UPDATE ADMIN
    public void updateAdmin(String username, String newPassword, String newRole) {
        List<String> admins = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(username)) {
                    admins.add(username + "," + newPassword + "," + newRole);
                } else {
                    admins.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error updating admin.");
        }

        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            for (String admin : admins) {
                fw.write(admin + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    // DELETE ADMIN
    public void deleteAdmin(String username) {
        List<String> admins = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (!data[0].equals(username)) {
                    admins.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting admin.");
        }

        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            for (String admin : admins) {
                fw.write(admin + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }
}