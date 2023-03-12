package com.estore.api.estoreapi.persistence;

import com.estore.api.estoreapi.model.Shoe;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;

import java.io.IOException;

public interface CartDAO {
    void addToCart(Shoe shoe) throws IOException;
    void removeFromCart(Shoe shoe) throws IOException;
    double checkout();
}
