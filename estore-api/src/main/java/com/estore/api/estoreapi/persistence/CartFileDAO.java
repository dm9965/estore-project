package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Shoe;

public class CartFileDAO implements CartDAO{

    @Override
    public Shoe[] getCart() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean deleteShoe(int id) throws IOException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addShoe(int id) throws IOException {
        // TODO Auto-generated method stub
        return false;
    }
    
}
