package com.toystore.order.model;

public class Order {

    private String orderId;
    private String userId;
    private String toyId;
    private String toyName;
    private int quantity;
    private double totalPrice;
    private String orderDate;
    private String status; // PENDING, COMPLETED, CANCELLED

    // Default Constructor
    public Order() {}

    // Parameterized Constructor
    public Order(String orderId, String userId, String toyId,
                 String toyName, int quantity, double totalPrice,
                 String orderDate, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.toyId = toyId;
        this.toyName = toyName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.status = status;
    }

    // Getters & Setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getToyId() { return toyId; }
    public void setToyId(String toyId) { this.toyId = toyId; }

    public String getToyName() { return toyName; }
    public void setToyName(String toyName) { this.toyName = toyName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Convert object to one line for file storage
    // Example: ORD-001,USR-001,TOY-001,LEGO Set,2,5000.00,2026-03-01,PENDING
    public String toFileString() {
        return String.format("%s,%s,%s,%s,%d,%.2f,%s,%s",
                orderId, userId, toyId, toyName, quantity, totalPrice, orderDate, status);
    }

    // Parse one line from file to Order Object
    public static Order fromFileString(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length >= 8) {
                return new Order(
                        parts[0].trim(), 
                        parts[1].trim(), 
                        parts[2].trim(), 
                        parts[3].trim(),
                        Integer.parseInt(parts[4].trim()),
                        Double.parseDouble(parts[5].trim()),
                        parts[6].trim(), 
                        parts[7].trim()
                );
            }
        } catch (Exception e) {
            System.err.println("Error parsing order line: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "Order [ID=" + orderId + ", Item=" + toyName + ", Total=" + totalPrice + ", Status=" + status + "]";
    }
}