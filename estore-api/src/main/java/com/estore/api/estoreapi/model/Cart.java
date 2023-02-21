package com.estore.api.estoreapi.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A class that represents a user's shopping cart
 */
public class Cart {

    /**
     * The total cost of the cart
     */
    @JsonProperty("total")
    private double total;

    /**
     * All items in cart
     */
    @JsonProperty("items")
    private ArrayList<Shoe> items;

    /**
     * Update the total cost of the cart
     * @param change amount to change cart by, pos or neg
     */
    private void updateTotal(double change)
    {
        this.total = this.total + change;
    }

    /**
     * Remove a shoe from the cart and update total
     * @param item shoe we want to remove
     */
    public void removeItem(Shoe item)
    {
        this.items.remove(item);
        this.updateTotal(-item.getPrice());
    }

    /**
     * Add a shoe to the cart and update total
     * @param item shoe we want to add
     */
    public void addItem(Shoe item)
    {
        this.items.add(item);
        this.updateTotal(item.getPrice());
    }

    /**
     * Return the total cost of the cart
     * @return total cost of cart returns double
     */
    public double getTotal()
    {
        return total;
    }

    /**
     * Return an ArrayList of items in the cart
     * @return ArrayList of items
     */
    public ArrayList<Shoe> getItems()
    {
        return items;
    }

    
}
