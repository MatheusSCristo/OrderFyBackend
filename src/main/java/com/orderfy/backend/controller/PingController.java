package com.orderfy.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingController {

    public ResponseEntity<String> ping() {
        return ResponseEntity.ok().body("Pong");
    }
}
