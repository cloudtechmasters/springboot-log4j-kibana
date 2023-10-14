package com.cloudtechmasters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendController {
    private static final Logger logger = LoggerFactory.getLogger(BackendController.class);
    @Value("${spring.application.name}")
    private String applicationName;
    // ...

    @GetMapping("/data")
    public ResponseEntity<String> getData() {
        logger.info("Incoming request at {} for request /path1 ", applicationName);
        // Simulate transient failure
        if (Math.random() < 0.2) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
        logger.info("Incoming request completed and sending response");
        // Return normal response
        return ResponseEntity.ok("Data from backend");
    }


}
