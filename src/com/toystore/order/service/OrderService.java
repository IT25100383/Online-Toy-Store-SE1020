package com.toystore.order.service;

import com.toystore.order.model.Order;
import com.toystore.toy.model.toy;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService {

    private static final String ORDERS_FILE = "C:/Users/USER/Documents/EDU/Y1/Y1S2/OOP/Project/Toy Store Sandbox/data/orders.txt";
    private static final String TOYS_FILE = "C:/Users/USER/Documents/EDU/Y1/Y1S2/OOP/Project/Toy Store Sandbox/data/toys.txt";

    private List<String> readAllLines(String filePath) {
        List<String> lines = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return lines;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) lines.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Read error.");
        }
        return lines;
    }

    private void writeAllLines(String filePath, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Write error.");
        }
    }

    private void appendLine(String filePath, String line) {
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Append error.");
        }
    }

    private toy getToyById(String toyId) {
        List<String> lines = readAllLines(TOYS_FILE);
        for (String line : lines) {
            toy currentToy = toy.fromFileString(line);
            if (currentToy != null && currentToy.getToyId().equals(toyId)) return currentToy;
        }
        return null;
    }

    private void updateToyStock(toy toy) {
        List<String> lines = readAllLines(TOYS_FILE);
        for (int i = 0; i < lines.size(); i++) {
            toy currentToy = toy.fromFileString(lines.get(i));
            if (currentToy != null && currentToy.getToyId().equals(toy.getToyId())) {
                lines.set(i, toy.toFileString());
                break;
            }
        }
        writeAllLines(TOYS_FILE, lines);
    }

    public String placeOrder(String userId, String toyId, int quantity) {
        toy toy = getToyById(toyId);
        if (toy == null) return "ERROR: Toy not found!";
        if (quantity <= 0) return "ERROR: Quantity must be greater than 0!";
        if (toy.getStock() < quantity) return "ERROR: Insufficient stock!";

        String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        double totalPrice = toy.getPrice() * quantity;
        String orderDate = LocalDate.now().toString();

        Order order = new Order(orderId, userId, toyId, toy.getName(), quantity, totalPrice, orderDate, "PENDING");
        appendLine(ORDERS_FILE, order.toFileString());

        toy.setStock(toy.getStock() - quantity);
        updateToyStock(toy);

        return "SUCCESS: Order placed!";
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        List<String> lines = readAllLines(ORDERS_FILE);
        for (String line : lines) {
            Order order = Order.fromFileString(line);
            if (order != null) orders.add(order);
        }
        return orders;
    }

    public Order getOrderById(String orderId) {
        for (Order order : getAllOrders()) {
            if (order.getOrderId().equals(orderId)) return order;
        }
        return null;
    }

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

    public boolean deleteOrder(String orderId) {
        List<String> lines = readAllLines(ORDERS_FILE);
        List<String> updated = new ArrayList<>();
        boolean found = false;
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length > 0 && parts[0].equals(orderId)) found = true;
            else updated.add(line);
        }
        if (found) writeAllLines(ORDERS_FILE, updated);
        return found;
    }

    public int getTotalOrders() {
        return getAllOrders().size();
    }

    public double calculateTotalRevenue() {
        double total = 0;
        for (Order order : getAllOrders()) {
            total += order.getTotalPrice();
        }
        return total;
    }
}