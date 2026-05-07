package com.example.toystoremanagementsystem.service;

import com.example.toystoremanagementsystem.model.Toy;
import com.example.toystoremanagementsystem.model.Order;
import com.example.toystoremanagementsystem.util.FileHandler;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private static final String ORDERS_FILE = "data/orders.txt";
    private static final String TOYS_FILE = "data/toys.txt";

    // = FILE HELPERS ===

    private List<String> readAllLines(String filePath) {
        List<String> lines = new ArrayList<>();
        java.io.File file = new java.io.File(filePath);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (java.io.IOException e) {
                System.err.println("File create error: " + e.getMessage());
            }
            return lines;
        }

        try (java.io.BufferedReader reader =
                     new java.io.BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line.trim());
                }
            }
        } catch (java.io.IOException e) {
            System.err.println("Read error: " + e.getMessage());
        }
        return lines;
    }

    private void writeAllLines(String filePath, List<String> lines) {
        try (java.io.BufferedWriter writer =
                     new java.io.BufferedWriter(
                             new java.io.FileWriter(filePath, false))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (java.io.IOException e) {
            System.err.println("Write error: " + e.getMessage());
        }
    }

    private void appendLine(String filePath, String line) {
        java.io.File file = new java.io.File(filePath);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        try (java.io.BufferedWriter writer =
                     new java.io.BufferedWriter(
                             new java.io.FileWriter(file, true))) {
            writer.write(line);
            writer.newLine();
        } catch (java.io.IOException e) {
            System.err.println("Append error: " + e.getMessage());
        }
    }

    //  GET TOY FROM FILE

    private Toy getToyById(String toyId) {
        List<String> lines = readAllLines(TOYS_FILE);
        for (String line : lines) {
            Toy toy = Toy.fromFileString(line);
            if (toy != null && toy.getToyId().equals(toyId)) {
                return toy;
            }
        }
        return null;
    }

    private void updateToyStock(Toy toy) {
        List<String> lines = readAllLines(TOYS_FILE);
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            if (parts.length > 0 && parts[0].equals(toy.getToyId())) {
                lines.set(i, toy.toFileString());
                break;
            }
        }
        writeAllLines(TOYS_FILE, lines);
    }

    // CREATE

    public String placeOrder(String userId, String toyId, int quantity) {

        // Toy get
        Toy toy = getToyById(toyId);

        // Toy exists check
        if (toy == null) {
            return "ERROR: Toy not found!";
        }

        // Quantity validate
        if (quantity <= 0) {
            return "ERROR: Quantity must be greater than 0!";
        }

        // Stock validate ✅
        if (toy.getQuantity() < quantity) {
            return "ERROR: Insufficient stock! Available: "
                    + toy.getQuantity() + " items only.";
        }

        // Order ID generate
        String orderId = "ORD-" + UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();

        // Total price calculate
        double totalPrice = toy.getPrice() * quantity;

        // Today date
        String orderDate = LocalDate.now().toString();

        // Order object create
        Order order = new Order(
                orderId, userId, toyId, toy.getName(),
                quantity, totalPrice, orderDate, "PENDING"
        );

        // orders.txt save
        appendLine(ORDERS_FILE, order.toFileString());

        // Toy stock update
        toy.setQuantity(toy.getQuantity() - quantity);
        updateToyStock(toy);

        return "SUCCESS: Order placed! Order ID: " + orderId;
    }

    // READ

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

    public List<Order> getOrdersSortedByPriceSelectionSort() {

        List<Order> orders = getAllOrders();

        int n = orders.size();
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                // getTotalPrice()
                if (orders.get(j).getTotalPrice() < orders.get(min_idx).getTotalPrice()) {
                    min_idx = j;
                }
            }
            // Swap
            Order temp = orders.get(min_idx);
            orders.set(min_idx, orders.get(i));
            orders.set(i, temp);
        }
        return orders;
    }

    public Order getOrderById(String orderId) {
        for (Order order : getAllOrders()) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    public int getPendingCount() {
        int count = 0;
        for (Order order : getAllOrders()) {
            if ("PENDING".equals(order.getStatus())) count++;
        }
        return count;
    }

    public int getCompletedCount() {
        int count = 0;
        for (Order order : getAllOrders()) {
            if ("COMPLETED".equals(order.getStatus())) count++;
        }
        return count;
    }

    //  UPDATE

    public boolean updateOrderStatus(String orderId, String newStatus) {
        List<String> lines = readAllLines(ORDERS_FILE);
        boolean found = false;

        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            if (parts.length >= 8 && parts[0].equals(orderId)) {
                parts[7] = newStatus;
                lines.set(i, String.join(",", parts));
                found = true;
                break;
            }
        }

        if (found) writeAllLines(ORDERS_FILE, lines);
        return found;
    }

    //DELETE

    public boolean deleteOrder(String orderId) {
        List<String> lines = readAllLines(ORDERS_FILE);
        boolean found = false;
        List<String> updated = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length > 0 && parts[0].equals(orderId)) {
                found = true;
            } else {
                updated.add(line);
            }
        }

        if (found) writeAllLines(ORDERS_FILE, updated);
        return found;
    }
}
