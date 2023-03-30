package com.estore.api.estoreapi.model;

import java.util.List;

public class Order {
    private String username;
    private List<Shoe> items;
    private double totalCost;

    public Order (String username, List<Shoe> items, double totalCost) {
        this.username = username;
        this.items = items;
        this.totalCost = totalCost;
    }

    public String getUsername() {
        return this.username;
    }
}
