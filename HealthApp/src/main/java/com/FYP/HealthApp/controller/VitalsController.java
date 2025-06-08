package com.FYP.HealthApp.controller;

import com.FYP.HealthApp.DTO.*;
import com.FYP.HealthApp.model.ObservedValuesVital;
import com.FYP.HealthApp.model.ValueRangesVital;
import com.FYP.HealthApp.service.VitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vitals")
public class VitalsController {

    @Autowired
    private VitalService vitalService;

    @PostMapping("/add")
    public ResponseEntity<?> addVital(@RequestBody VitalDTO vitalDTO) {
        try {
            Long vitalId = vitalService.addVital(vitalDTO);
            return ResponseEntity.ok("Vital added successfully with ID: " + vitalId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/vitaltypes/add")
    public ResponseEntity<?> addVitalType(@RequestBody VitalTypeDTO vitalTypeDTO) {
        try {
            Long vitalTypeId = vitalService.addVitalType(vitalTypeDTO);
            return ResponseEntity.ok("Vital Type added successfully with ID: " + vitalTypeId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/addPatientVital")
    public Long addPatientVital(@RequestBody PatientVitalDTO dto) {
        return vitalService.addPatientVital(dto);
    }


    @PostMapping("/addObservedValue")
    public ResponseEntity<Long> addObservedValue(@RequestBody ObservedValueDTO dto) {
        Long id = vitalService.addObservedValue(dto);
        return ResponseEntity.ok(id);
    }
    @GetMapping("/patient/{patientId}/observed")
    public ResponseEntity<List<ObservedValuesVital>> getObservedValuesByPatient(@PathVariable Long patientId) {
        List<ObservedValuesVital> values = vitalService.getObservedValuesByPatient(patientId);
        return ResponseEntity.ok(values);
    }
    @PostMapping("/addValueRanges")
    public ResponseEntity<String> addRange(@RequestBody ValueRangesVitalDTO dto) {
        vitalService.saveRange(dto);
        return ResponseEntity.ok("Vital range added successfully.");
    }

    @GetMapping("/ranges")
    public ResponseEntity<List<ValueRangesVital>> getRange(
            @RequestParam Integer obsVId,
            @RequestParam String gender,
            @RequestParam Integer age
    ) {
        List<ValueRangesVital> ranges = vitalService.getApplicableRanges(obsVId, gender, age);
        return ResponseEntity.ok(ranges);
    }
}
