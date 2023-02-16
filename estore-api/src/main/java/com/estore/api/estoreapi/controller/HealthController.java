package com.estore.api.estoreapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller will allow the frontend to check if the server is alive or not
 */
@RestController
public class HealthController {

    @GetMapping("/ping")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("pong!", HttpStatus.OK);
    }
}
