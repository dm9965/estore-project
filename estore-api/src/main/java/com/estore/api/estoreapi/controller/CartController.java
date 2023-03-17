package com.estore.api.estoreapi.controller;
import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.persistence.CartDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("cart")
public class CartController {

    private final CartDAO cartDAO;
    private ArrayList<Shoe> cart = new ArrayList<>();

    public CartController(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }
    @GetMapping ("/")
    public ResponseEntity<ArrayList<Shoe>> getAllItems() {
        try {
            cart = cartDAO.getCart();
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ArrayList<Shoe>> addItem(@RequestBody Shoe shoe) {
        try {
            cart.add(shoe);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<ArrayList<Shoe>> removeItem(@RequestBody Shoe shoe) {
        try {
            cart.remove(shoe);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        }
        catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Double> getTotal() {
        try {
            double total = 0;
            for (Shoe shoe : cart) {
                total += shoe.getPrice();
            }
            return new ResponseEntity<>(total, HttpStatus.OK);
        }
        catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
