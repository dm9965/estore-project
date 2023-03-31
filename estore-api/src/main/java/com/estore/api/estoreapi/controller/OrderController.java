package com.estore.api.estoreapi.controller;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Order;
import com.estore.api.estoreapi.persistence.OrderDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderDAO orderDAO;
    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @PostMapping("/orders/{username}")
    public ResponseEntity<String> checkout(@PathVariable String username) {
        System.out.println("Console log for checkout method");
        try {
            Order order = orderDAO.checkout(username);
            return new ResponseEntity<>("Checkout completed successfully. Total cost: " + order, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to checkout: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<Order> getOrder(@PathVariable String username) {
        try {
            Order order = orderDAO.getOrder(username);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders() {
        try {
            List<Order> orderList = orderDAO.getAllOrders();
            return new ResponseEntity<>(orderList, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
