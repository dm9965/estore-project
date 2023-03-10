package com.estore.api.estoreapi;

import com.estore.api.estoreapi.controller.HealthController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

@Tag("Controller-tier")
public class HealthControllerTest {
    private HealthController healthController;

    public HealthControllerTest() {
    }

    @BeforeEach
    public void setupShoeController() {
        this.healthController = new HealthController();
    }

    @Test
    public void testPing() {
        ResponseEntity<String> response = this.healthController.index();
        Assertions.assertEquals("pong!", response.getBody());
    }

}
