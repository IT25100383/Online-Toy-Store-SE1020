package com.toystore.order.model;

public class Order {

    // Encapsulation
    private String orderId;
    private String userId;
    private String toyId;
    private String toyName;
    private int quantity;
    private double totalPrice;
    private String orderDate;
    private String status; // pending, completed, cancelled

    // Default Constructor
    public Order() {
    }

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

    // Getters
    public String getOrderId() {

        return orderId;
    }

    public String getUserId() {

        return userId;
    }

    public String getToyId() {

        return toyId;
    }

    public String getToyName() {

        return toyName;
    }

    public int getQuantity() {

        return quantity;
    }

    public double getTotalPrice() {

        return totalPrice;
    }

    public String getOrderDate() {

        return orderDate;
    }

    public String getStatus() {

        return status;
    }

    // Setters
    public void setOrderId(String orderId) {

        this.orderId = orderId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    public void setToyId(String toyId) {

        this.toyId = toyId;
    }

    public void setToyName(String toyName) {

        this.toyName = toyName;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    public void setTotalPrice(double totalPrice) {

        this.totalPrice = totalPrice;
    }

    public void setOrderDate(String orderDate) {

        this.orderDate = orderDate;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    // Convert object to one line for file storage
    // Example:
    // ORD-001,USR-001,TOY-001,LEGO Set,2,5000.0,2026-03-01,PENDING
    public String toFileString() {

        return orderId + "," + userId + "," + toyId + "," +
                toyName + "," + quantity + "," +
                totalPrice + "," + orderDate + "," + status;
    }

    // Convert one line from file back into Order object
    public static Order fromFileString(String line) {

        String[] parts = line.split(",");

        // Basic safety check
        if (parts.length < 8) {

            throw new IllegalArgumentException("Invalid order data");
        }

        return new Order(
                parts[0],
                parts[1],
                parts[2],
                parts[3],
                Integer.parseInt(parts[4]),
                Double.parseDouble(parts[5]),
                parts[6],
                parts[7]
        );
    }

    @Override
    public String toString() {

        return "Order{orderId=" + orderId +
                ", status=" + status + "}";
    }
}