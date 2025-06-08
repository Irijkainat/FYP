package com.FYP.HealthApp.controller;

import com.FYP.HealthApp.DTO.PatientDTO;
import com.FYP.HealthApp.model.Patient;
import com.FYP.HealthApp.service.PatientService;
import com.FYP.HealthApp.service.VitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/patient")
public class PatientController {


    @Autowired
    private PatientService patientService;

    @PostMapping("/add")
    public Patient addPatient(@RequestBody PatientDTO patientDTO) {
        return patientService.addPatient(patientDTO);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<String> patientDashboard() {
        return ResponseEntity.ok("Welcome to Patient Dashboard");
    }

}
