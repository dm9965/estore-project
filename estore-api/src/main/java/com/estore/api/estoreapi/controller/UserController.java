package com.estore.api.estoreapi.controller;

import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.estore.api.estoreapi.persistence.UserDAO;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserDAO userDAO;
    public UserController(UserDAO userDao) {
        this.userDAO = userDao;
    }

    @GetMapping("/")
    public ResponseEntity<ArrayList<User>> getAllUsers() {
        try {
            ArrayList<User> userList = userDAO.getUsers();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(@RequestParam String username, @RequestParam String password) {
        try {
            User user = userDAO.createUser(username, password);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        try {
            boolean isAdmin = userDAO.isAdmin(username, password);
            boolean isCustomer = userDAO.isCustomer(username, password);
            if (isAdmin) {
                return new ResponseEntity<>("Admin login successful", HttpStatus.OK);
            } else if (isCustomer) {
                return new ResponseEntity<>("Customer login successful", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        try {
            User user = userDAO.findByUsername(username);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            return new ResponseEntity<>("Error getting user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        try {
            userDAO.updateUser(user);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error updating user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
    

