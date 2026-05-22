package com.toystore.order.service;

public class OrderService {

    private static final String ORDERS_FILE = "data/orders.txt";
    private static final String TOYS_FILE = "data/toys.txt";

    // ========== FILE HELPERS ==========

    private List<String> readAllLines(String filePath) {
        List<String> lines = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("File create error: " + e.getMessage());
            }
            return lines;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Read error: " + e.getMessage());
        }
        return lines;
    }

    private void writeAllLines(String filePath, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Write error: " + e.getMessage());
        }
    }

    private void appendLine(String filePath, String line) {
        File file = new File(filePath);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Append error: " + e.getMessage());
        }
    }

    // ========== CREATE (Updated for Controller) ==========

  
    public void placeOrder(Order order) {
        if (order != null) {
            appendLine(ORDERS_FILE, order.toFileString());
            System.out.println("Order Saved to File: " + order.getOrderId());
        }
    }

    //  READ

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


     // Selection Sort  (Low to High)

    public List<Order> getOrdersSortedByPriceSelectionSort() {
        List<Order> orders = getAllOrders();
        int n = orders.size();
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (orders.get(j).getTotalPrice() < orders.get(min_idx).getTotalPrice()) {
                    min_idx = j;
                }
            }
            // Object Swap
            Order temp = orders.get(min_idx);
            orders.set(min_idx, orders.get(i));
            orders.set(i, temp);
        }
        return orders;
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

    //  UPDATE STATUS

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

    // DELETE

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
    // filter order
    public List<Order> getOrdersByUserId(String userId) {
        List<Order> allOrders = getAllOrders();
        List<Order> userOrders = new java.util.ArrayList<>();

        for (Order order : allOrders) {
            if (order.getUserId().equals(userId)) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }
}
