package com.FYP.HealthApp.controller;

import com.FYP.HealthApp.DTO.PatientWithDiseasesDTO;
import com.FYP.HealthApp.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/dashboard")
    public ResponseEntity<String> doctorDashboard() {
        return ResponseEntity.ok("Welcome to Doctor Dashboard");
    }


    @GetMapping("/patients")
    public ResponseEntity<List<PatientWithDiseasesDTO>> getPatientsWithDiseases() {
        List<PatientWithDiseasesDTO> patients = doctorService.getAllPatientsWithDiseases();
        return ResponseEntity.ok(patients);
    }
}
