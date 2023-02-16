package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import com.estore.api.estoreapi.model.Shoe;

/**
 * Defines interface for cart object persistence
 */
public interface CartDAO {
    /**
     * Retrieves all items in cart
     * 
     * @return array of Shoe objects, may be empty
     * 
     * @throws IOException if issue with underlying storage
     */
    Shoe[] getCart() throws IOException;

    /**
     * Delete a given shoe by id
     * @return True if delete was succesful, false if id not in cart
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteShoe(int id) throws IOException;

    /**
     * Add a given shoe to the cart by id
     * @return True if succesful, false if id not in inventory
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean addShoe(int id) throws IOException;
}
