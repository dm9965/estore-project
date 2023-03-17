package com.estore.api.estoreapi.persistence;

import com.estore.api.estoreapi.model.Shoe;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;

import java.io.IOException;
import java.util.ArrayList;

public interface CartDAO {
    void addToCart(Shoe shoe) throws IOException;
    void removeFromCart(Shoe shoe) throws IOException;
    ArrayList<Shoe> getCart() throws IOException;
    double checkout();
}
