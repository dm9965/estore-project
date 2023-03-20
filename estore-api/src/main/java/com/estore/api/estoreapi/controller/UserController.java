package com.estore.api.estoreapi.controller;
import com.estore.api.estoreapi.persistence.UserDAO;
import com.estore.api.estoreapi.model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

@RestController
@RequestMapping("user")

/*
 * Controller for the User
 * 
 * @author Connor Bastian, crb1759@rit.edu
 */
public class UserController {
    
    private final UserDAO userDAO;
    private User user = new User();
    
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping ("/")
    public ResponseEntity<ArrayList<User>> getByEmail(@PathVariable String username) {
        try {
            user = userDAO.findByEmail(username);
            if (user.getEmail() == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping ("/")
    public ResponseEntity<ArrayList<User>> getByUsername(@PathVariable String username) {
        try {
            user = userDAO.findByUsername(username);
            if (user.getUsername() == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping ("/")
    public ResponseEntity<ArrayList<User>> updateUser(@PathVariable User user) {
        try {
            userDAO.updateUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping ("/")
    public ResponseEntity<ArrayList<User>> isAdmin(@PathVariable String username, String password) {
        try {
            userDAO.isAdmin(username, password);
            if (username == "admin") {
                return true;
            } 
            elsif (password == "admin") {
                return true;
            }
            else {
                return false;
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<ArrayList<User>> deleteUser(@PathVariable User user) {
        try {
            userDAO.remove(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
    }
}