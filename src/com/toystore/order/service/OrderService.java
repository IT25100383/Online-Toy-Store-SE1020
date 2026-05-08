package com.toystore.order.service;

import com.toystore.order.model.Order;
import com.toystore.toy.model.toy;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService {

    // File paths
    private static final String ORDERS_FILE = "data/orders.txt";
    private static final String TOYS_FILE = "data/toys.txt";

    // READ ALL LINES FROM FILE
    private List<String> readAllLines(String filePath) {

        List<String> lines = new ArrayList<>();

        File file = new File(filePath);

        // Create file if it doesn't exist
        if (!file.exists()) {

            try {

                if (file.getParentFile() != null) {

                    file.getParentFile().mkdirs();
                }

                file.createNewFile();

            } catch (IOException e) {

                System.out.println("File creation error.");
            }

            return lines;
        }

        // Read file
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (!line.trim().isEmpty()) {

                    lines.add(line.trim());
                }
            }

        } catch (IOException e) {

            System.out.println("Read error.");
        }

        return lines;
    }

    // WRITE ALL LINES TO FILE
    private void writeAllLines(String filePath, List<String> lines) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(filePath))) {

            for (String line : lines) {

                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println("Write error.");
        }
    }

    // APPEND ONE LINE TO FILE
    private void appendLine(String filePath, String line) {

        File file = new File(filePath);

        try {

            if (file.getParentFile() != null) {

                file.getParentFile().mkdirs();
            }

        } catch (Exception e) {

            System.out.println("Folder creation error.");
        }

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(file, true))) {

            writer.write(line);
            writer.newLine();

        } catch (IOException e) {

            System.out.println("Append error.");
        }
    }

    // GET toy BY ID
    private toy getToyById(String toyId) {

        List<String> lines = readAllLines(TOYS_FILE);

        for (String line : lines) {

            toy currentToy = toy.fromFileString(line);

            if (currentToy != null && currentToy.getToyId().equals(toyId)) {

                return currentToy;
            }
        }

        return null;
    }

    // UPDATE toy STOCK
    private void updateToyStock(toy toy) {

        List<String> lines = readAllLines(TOYS_FILE);

        for (int i = 0; i < lines.size(); i++) {

            toy currentToy = toy.fromFileString(lines.get(i));

            if (currentToy != null &&
                    currentToy.getToyId().equals(toy.getToyId())) {

                lines.set(i, toy.toFileString());
                break;
            }
        }

        writeAllLines(TOYS_FILE, lines);
    }

    // CREATE ORDER
    public String placeOrder(String userId,
                             String toyId,
                             int quantity) {

        // Find toy
        toy toy = getToyById(toyId);

        // Validate toy
        if (toy == null) {

            return "ERROR: Toy not found!";
        }

        // Validate quantity
        if (quantity <= 0) {

            return "ERROR: Quantity must be greater than 0!";
        }

        // Check stock
        if (toy.getStock() < quantity) {

            return "ERROR: Insufficient stock!";
        }

        // Generate order ID
        String orderId = "ORD-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();

        // Calculate total
        double totalPrice = toy.getPrice() * quantity;

        // Current date
        String orderDate = LocalDate.now().toString();

        // Create order object
        Order order = new Order(
                orderId,
                userId,
                toyId,
                toy.getName(),
                quantity,
                totalPrice,
                orderDate,
                "PENDING"
        );

        // Save order
        appendLine(ORDERS_FILE, order.toFileString());

        // Update stock
        toy.setStock(toy.getStock() - quantity);
        updateToyStock(toy);

        return "SUCCESS: Order placed!";
    }

    // GET ALL ORDERS
    public List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();

        List<String> lines = readAllLines(ORDERS_FILE);

        for (String line : lines) {

            Order order = Order.fromFileString(line);

            if (order != null) {

                orders.add(order);
            }
        }

        return orders;
    }

    // GET ORDER BY ID
    public Order getOrderById(String orderId) {

        for (Order order : getAllOrders()) {

            if (order.getOrderId().equals(orderId)) {

                return order;
            }
        }

        return null;
    }

    // UPDATE ORDER STATUS
    public boolean updateOrderStatus(String orderId,
                                     String newStatus) {

        List<String> lines = readAllLines(ORDERS_FILE);

        boolean found = false;

        for (int i = 0; i < lines.size(); i++) {

            String[] parts = lines.get(i).split(",");

            if (parts.length >= 8 &&
                    parts[0].equals(orderId)) {

                parts[7] = newStatus;

                lines.set(i, String.join(",", parts));

                found = true;
                break;
            }
        }

        if (found) {

            writeAllLines(ORDERS_FILE, lines);
        }

        return found;
    }

    // DELETE ORDER
    public boolean deleteOrder(String orderId) {

        List<String> lines = readAllLines(ORDERS_FILE);

        List<String> updated = new ArrayList<>();

        boolean found = false;

        for (String line : lines) {

            String[] parts = line.split(",");

            if (parts.length > 0 &&
                    parts[0].equals(orderId)) {

                found = true;

            } else {

                updated.add(line);
            }
        }

        if (found) {

            writeAllLines(ORDERS_FILE, updated);
        }

        return found;
    }
}