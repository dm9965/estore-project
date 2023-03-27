package com.estore.api.estoreapi.controller;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.persistence.CartDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOError;
import java.io.IOException;

@RestController
@RequestMapping("cart")
public class CartController {

    private final CartDAO cartDAO;

    public CartController(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    @GetMapping("/{username}")
    public ResponseEntity<Cart> getAllItems(@PathVariable String username) {
        try {
            Cart cart = cartDAO.getCart(username);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{username}")
    public ResponseEntity<Cart> addItem(@PathVariable String username, @RequestBody Shoe shoe) {
        try {
            cartDAO.addToCart(username, shoe);
            Cart cart = cartDAO.getCart(username);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<Cart> removeItem(@PathVariable String username, @PathVariable int id) {
        try {
            cartDAO.removeFromCart(username, id);
            Cart cart = cartDAO.getCart(username);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<String> clearCart(@PathVariable String username) {
        try {
            cartDAO.clearCart(username);
            return new ResponseEntity<>("Cart successfully cleared.", HttpStatus.OK);
        }
        catch (IOException e) {
            return new ResponseEntity<>("Error occurred while clearing cart", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{username}/checkout")
    public ResponseEntity<String> checkout(@PathVariable String username) {
        try {
            double totalCost = cartDAO.checkout(username);
            return new ResponseEntity<>("Checkout completed successfully. Total cost: " + totalCost, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to checkout: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
