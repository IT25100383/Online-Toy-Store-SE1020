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
}
