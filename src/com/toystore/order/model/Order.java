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



}
