package com.FYP.HealthApp.controller;

import com.FYP.HealthApp.DTO.*;
import com.FYP.HealthApp.Repositries.PatientRepository;
import com.FYP.HealthApp.model.ObservedValuesVital;
import com.FYP.HealthApp.model.Patient;
import com.FYP.HealthApp.model.ValueRangesVital;
import com.FYP.HealthApp.service.VitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vitals")
public class VitalsController {

    @Autowired
    private VitalService vitalService;

    @PostMapping("/submitVitals")
    public ResponseEntity<?> submitVitals(@RequestBody PatientVitalEntryDTO dto) {
        List<VitalStatusResponseDTO> responseList = vitalService.submitVitals(dto);
        return ResponseEntity.ok(responseList);
    }


    @GetMapping("/patient/{patientId}/observed")
    public ResponseEntity<List<ObservedValuesVital>> getObservedValuesByPatient(@PathVariable Long patientId) {
        List<ObservedValuesVital> values = vitalService.getObservedValuesByPatient(patientId);
        return ResponseEntity.ok(values);
    }


    @PostMapping("/ranges")
    public ResponseEntity<List<ValueRangesVital>> getRangePost(@RequestBody CheckVitalDTO dto) {
        List<ValueRangesVital> ranges = vitalService.getApplicableRanges(dto.getVitalId(), dto.getGender(), dto.getAge());
        return ResponseEntity.ok(ranges);
    }


    @PostMapping("/check-critical")
    public ResponseEntity<Boolean> checkIfCritical(@RequestBody CheckVitalDTO dto) {
        boolean isCritical = vitalService.isCritical(dto);
        return ResponseEntity.ok(isCritical);
    }

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/by-patient/{id}")
    public ResponseEntity<?> getVitalsByPatientId(@PathVariable Long id) {
        // Fetch patient basic info
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        List<VitalDTO> vitals = vitalService.getVitalsForPatient(id);

        // Structure the response like your example
        Map<String, Object> response = new HashMap<>();
        response.put("patientId", patient.getPatientId());
        response.put("gender", patient.getGender());
        response.put("date of birth", patient.getDob());
        response.put("vitals", vitals);

        return ResponseEntity.ok(response);
    }}
