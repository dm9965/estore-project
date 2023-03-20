package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * A class that represents a user's shopping cart
 */
public class Cart {
    /**
     * All items in cart
     */
    @JsonProperty("items")
    private ArrayList<Shoe> items;

    /**
     * Total price of all shoes in the cart
     */
    @JsonProperty("id")
    private int id;

    /**
     * The total cost of the cart
     */
    @JsonProperty("total")
    private double total;

    public ArrayList<Shoe> getItems() {
        return items;
    }

    public void setItems(ArrayList<Shoe> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
