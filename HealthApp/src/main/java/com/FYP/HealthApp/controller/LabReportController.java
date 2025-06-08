package com.FYP.HealthApp.controller;

import com.FYP.HealthApp.DTO.ManualLabReportDTO;
import com.FYP.HealthApp.service.LabReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lab/reports")
public class LabReportController {
    @Autowired
    private LabReportService labReportService;

    @PostMapping("/manual")
    public ResponseEntity<String> saveManualReport(@RequestBody ManualLabReportDTO dto) {
        labReportService.saveManualReport(dto);
        return ResponseEntity.ok("Lab report saved successfully.");
    }
}
