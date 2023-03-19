package com.estore.api.estoreapi.persistence;

import com.estore.api.estoreapi.model.Shoe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartFileDAO implements CartDAO {
    private final List<Shoe> shoesInCart = new ArrayList<>();

    @Override
    public void addToCart(Shoe shoe) throws IOException {
        shoesInCart.add(shoe);
    }

    @Override
    public void removeFromCart(Shoe shoe) throws IOException {
        shoesInCart.remove(shoe);
    }

    @Override
    public double checkout() {
        double total = 0;
        for (Shoe shoe : shoesInCart) {
            total += shoe.getPrice();
        }
        return total;
    }
}