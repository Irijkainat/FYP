package com.FYP.HealthApp.controller;

import com.FYP.HealthApp.DTO.ManualLabReportDTO;
import com.FYP.HealthApp.Repositries.FieldRepository;
import com.FYP.HealthApp.Repositries.LabRepository;
import com.FYP.HealthApp.Repositries.LabTestRepository;
import com.FYP.HealthApp.model.Field;
import com.FYP.HealthApp.model.Lab;
import com.FYP.HealthApp.model.LabTest;
import com.FYP.HealthApp.service.LabReportService;
import com.FYP.HealthApp.service.OCRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/lab/reports")
public class LabReportController {

    @Autowired
    private LabReportService labReportService;

    @Autowired
    private LabTestRepository labTestRepository;
    @Autowired
    private LabRepository labRepository;

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private OCRService ocrService;

    @PostMapping("/labs")
    public ResponseEntity<Lab> createLab(@RequestBody Lab lab) {
        return ResponseEntity.ok(labRepository.save(lab));
    }


    @PostMapping("/lab-tests")
    public ResponseEntity<LabTest> createLabTest(@RequestBody LabTest labTest) {
        return ResponseEntity.ok(labTestRepository.save(labTest));
    }

    // 3. FIELD API - Create a Field for a Lab Test
    @PostMapping("/fields")
    public ResponseEntity<Field> createField(@RequestBody Field field) {
        // Optionally validate Field's LabId exists
        return ResponseEntity.ok(fieldRepository.save(field));
    }
//
//    @GetMapping("/fields/{labTestId}")
//    public ResponseEntity<List<Field>> getFieldsByLabTest(@PathVariable Long labTestId) {
//        LabTest labTest = labTestRepository.findById(labTestId)
//                .orElseThrow(() -> new IllegalArgumentException("LabTest ID not found: " + labTestId));
//        return ResponseEntity.ok(fieldRepository.findByLabTest(labTest));
//    }
//    @GetMapping("/labs")
//    public ResponseEntity<List<Lab>> getAllLabs() {
//        return ResponseEntity.ok(labRepository.findAll());
//    }


    @PostMapping("/manual")
    public ResponseEntity<String> saveManualReport(@RequestBody ManualLabReportDTO dto) {
        labReportService.saveManualReport(dto);
        return ResponseEntity.ok("Lab report saved successfully.");
    }
    @PostMapping("/ocr-upload/{labTestId}")
    public ResponseEntity<String> scanTestReport(@RequestParam("file") MultipartFile file,@PathVariable Long labTestId) {
        try {
            LabTest labTest = labTestRepository.findById(labTestId)
                    .orElseThrow(() -> new IllegalArgumentException("LabTest ID not found: " + labTestId));
            List<Field> fields = fieldRepository.findByLabTest(labTest);

            File tempFile = File.createTempFile("report-", file.getOriginalFilename());
            file.transferTo(tempFile);

            String rawText = ocrService.extractText(tempFile,fields);
            tempFile.delete();

            return ResponseEntity.ok(rawText);

        } catch (IOException e) {
            return ResponseEntity.status(500).body("File processing error: " + e.getMessage());
        }
    }

}
