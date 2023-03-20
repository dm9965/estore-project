package com.estore.api.estoreapi.persistence;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.utils.FlatFileOps;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class CartFileDAO implements CartDAO {
    private final ArrayList<Shoe> shoesInCart = new ArrayList<>();
    public static int nextId;
    private static final Logger LOG = Logger.getLogger(CartFileDAO.class.getName());
    private final ObjectMapper objectMapper;
    private final String filename;
    Map<Integer, Cart> cartMap = new HashMap<>();


    public CartFileDAO(@Value("${dao.carts}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the heroes from the file
    }


    @Override
    public void addToCart(Shoe shoe) throws IOException {
        shoesInCart.add(shoe);
    }

    @Override
    public void removeFromCart(Shoe shoe) throws IOException {
        shoesInCart.remove(shoe);
    }

    @Override
    public ArrayList<Shoe> getCart() throws IOException {
        return shoesInCart;
    }

    @Override
    public double getTotal() {
        double total = 0;
        for (Shoe shoe : shoesInCart) {
            total += shoe.getPrice();
        }
        return total;
    }

    private boolean load() throws IOException {
        // Deserializes the JSON objects from the file into an array of shoes
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file

        FlatFileOps.ensureDataFileExists(filename);
        Cart[] carts = objectMapper.readValue(new File(filename), Cart[].class);

        // Add each shoe to the tree map and keep track of the greatest id
        for (Cart cart : carts) {
            cartMap.put(cart.getId(), cart);
            if (cart.getId() > nextId) nextId = cart.getId();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }
}