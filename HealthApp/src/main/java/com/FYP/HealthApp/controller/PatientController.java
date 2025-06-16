package com.FYP.HealthApp.controller;

import com.FYP.HealthApp.DTO.AssignDiseaseDTO;
import com.FYP.HealthApp.DTO.PatientDTO;
import com.FYP.HealthApp.model.Patient;
import com.FYP.HealthApp.service.PatientService;
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

    @PostMapping("/assign-diseases")
    public ResponseEntity<String> assignDiseases(@RequestBody AssignDiseaseDTO dto) {
        patientService.assignDiseasesToPatient(dto);
        return ResponseEntity.ok("Diseases assigned successfully.");
    }


}
