package com.FYP.HealthApp.controller;

import com.FYP.HealthApp.DTO.ManualLabReportDTO;
import com.FYP.HealthApp.DTO.PatientLabReportDTO;
import com.FYP.HealthApp.DTO.ReportFieldDTO;
import com.FYP.HealthApp.Repositries.*;
import com.FYP.HealthApp.model.Field;
import com.FYP.HealthApp.model.Lab;
import com.FYP.HealthApp.model.LabTest;
import com.FYP.HealthApp.model.Report;
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
    private ObservedValuesReportRepository observedValuesReportRepository;
    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private ReportRepository reportRepository;

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

    @PostMapping("/fields")
    public ResponseEntity<Field> createField(@RequestBody Field field) {
        return ResponseEntity.ok(fieldRepository.save(field));
    }

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
    @GetMapping("/by-patient/{id}")
    public ResponseEntity<List<Report>> getReportsByPatientId(@PathVariable Long id) {
        List<Report> reports = reportRepository.findByPatient_PatientId(id);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/lab/field-values/by-patient/{id}")
    public List<ReportFieldDTO> getPatientReportFields(@PathVariable Long id) {
        return observedValuesReportRepository.getFieldDataByPatientId(id);
    }




}
