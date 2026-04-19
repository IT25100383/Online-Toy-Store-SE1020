package com.toystore.order.model;

public class Order {

    private String orderID;
    private String userID;
    private String toyID;
    private String toyName;
    private int quantity;
    private double totalPrice;
    private String orderDate;
    private String status; // pending , completed, cancelled

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
    // Geter & Setter
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

    // File format:
    // ORD-001,USR-001,TOY-001,LEGO Set,2,5000.0,2026-03-01,PENDING
    public String toFileString() {
        return orderId + "," + userId + "," + toyId + "," +
                toyName + "," + quantity + "," + totalPrice + "," +
                orderDate + "," + status;
    }

    public static Order fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length >= 8) {
            return new Order(
                    parts[0], parts[1], parts[2], parts[3],
                    Integer.parseInt(parts[4]),
                    Double.parseDouble(parts[5]),
                    parts[6], parts[7]
            );
        }
        return null;
    }

    @Override
    public String toString() {
        return "Order{orderId=" + orderId + ", status=" + status + "}";
    }


}
