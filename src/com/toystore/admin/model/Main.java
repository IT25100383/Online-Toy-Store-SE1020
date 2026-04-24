package com.toystore.admin.model;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AdminManager manager = new AdminManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nAdmin Management System");
            System.out.println("1. Add Admin");
            System.out.println("2. View Admins");
            System.out.println("3. Update Admin");
            System.out.println("4. Delete Admin");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Username: ");
                    String u = sc.nextLine();
                    System.out.print("Password: ");
                    String p = sc.nextLine();
                    System.out.print("Role: ");
                    String r = sc.nextLine();

                    manager.addAdmin(new Admin(u, p, r));
                    break;

                case 2:
                    manager.viewAdmins();
                    break;

                case 3:
                    System.out.print("Username to update: ");
                    String up = sc.nextLine();
                    System.out.print("New Password: ");
                    String np = sc.nextLine();
                    System.out.print("New Role: ");
                    String nr = sc.nextLine();

                    manager.updateAdmin(up, np, nr);
                    break;

                case 4:
                    System.out.print("Username to delete: ");
                    String del = sc.nextLine();

                    manager.deleteAdmin(del);
                    break;

                case 5:
                    System.exit(0);
            }
        }
    }
}