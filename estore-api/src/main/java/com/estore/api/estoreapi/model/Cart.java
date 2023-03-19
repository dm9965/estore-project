package com.estore.api.estoreapi.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A class that represents a user's shopping cart
 */
public class Cart {
    /** All items in cart */
    @JsonProperty("items")
    private ArrayList<Shoe> items;

    /** Total price of all shoes in the cart */
    @JsonProperty("cartID")
    private int cartID;

    /** The total cost of the cart */
    @JsonProperty("total")
    private double total;

}
