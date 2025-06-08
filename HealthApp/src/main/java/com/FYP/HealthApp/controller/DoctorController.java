package com.FYP.HealthApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @GetMapping("/dashboard")
    public ResponseEntity<String> doctorDashboard() {
        return ResponseEntity.ok("Welcome to Doctor Dashboard");
    }
}
