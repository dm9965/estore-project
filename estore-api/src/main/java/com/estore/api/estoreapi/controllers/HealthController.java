package com.estore.api.estoreapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller will allow the frontend to check if the server is alive or not
 */
@RestController
public class HealthController {

    @GetMapping("/ping")
    public String index() {
        return "pong!";
    }
}
