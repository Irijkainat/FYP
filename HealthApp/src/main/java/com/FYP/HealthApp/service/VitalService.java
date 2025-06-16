package com.FYP.HealthApp.service;

import com.FYP.HealthApp.DTO.*;
import com.FYP.HealthApp.Repositries.*;
import com.FYP.HealthApp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class VitalService {

    @Autowired
    private ValueRangesVitalRepository repository;

    @Autowired
    private VitalRepository vitalsRepository;

    @Autowired
    private VitalTypeRepository vitalTypeRepository;

    @Autowired
    private ObservedValuesVitalRepository observedValuesVitalRepository;

    @Autowired
    private PatientVitalRepository patientVitalRepository;

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ValueRangesVitalRepository valueRangesVitalRepository;


    public List<ObservedValuesVital> getObservedValuesByPatient(Long patientId) {
        return observedValuesVitalRepository.findByPatientVital_Patient_PatientId(patientId);
    }

    public boolean isCritical(CheckVitalDTO dto) {
        List<ValueRangesVital> ranges = repository
                .findByVital_VitalIdAndGenderAndMinAgeLessThanEqualAndMaxAgeGreaterThanEqual(
                        dto.getVitalId(),
                        dto.getGender(),
                        dto.getAge(), // MinAge ≤ age
                        dto.getAge()  // MaxAge ≥ age
                );

        for (ValueRangesVital range : ranges) {
            boolean inValue = dto.getValue() >= range.getMinValue() && dto.getValue() <= range.getMaxValue();
            if (inValue) return false; // Not critical
        }

        return true; // No match found → Critical
    }
    public List<ValueRangesVital> getApplicableRanges(Long vitalId, String gender, Integer age) {
        return repository.findByVital_VitalIdAndGenderAndMinAgeLessThanEqualAndMaxAgeGreaterThanEqual(
                vitalId, gender, age, age
        );
    }

    public List<VitalStatusResponseDTO> submitVitals(PatientVitalEntryDTO dto) {
        List<VitalStatusResponseDTO> result = new ArrayList<>();
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Patient ID"));

        for (SingleVitalEntryDTO vitalDTO : dto.getVitals()) {
            // Add or fetch Vital
            Vitals vital = vitalsRepository.findByVitalName(vitalDTO.getVitalName())
                    .orElseGet(() -> vitalsRepository.save(Vitals.builder().vitalName(vitalDTO.getVitalName()).build()));

            // Add or fetch VitalType
            VitalType type = vitalTypeRepository.findByTypeNameAndVital_VitalId(vitalDTO.getVitalTypeName(), vital.getVitalId())
                    .orElseGet(() -> vitalTypeRepository.save(VitalType.builder().vital(vital).typeName(vitalDTO.getVitalTypeName()).build()));

            // Add or fetch PatientVital
            PatientVital pv = patientVitalRepository.findByPatient_PatientIdAndVitals_VitalId(dto.getPatientId(), vital.getVitalId())
                    .orElseGet(() -> patientVitalRepository.save(new PatientVital(patient, vital)));

            // Save Observed Value
            ObservedValuesVital ov = ObservedValuesVital.builder()
                    .patientVital(pv)
                    .vitalType(type)
                    .value(vitalDTO.getValue())
                    .date(vitalDTO.getDate())
                    .time(vitalDTO.getTime())
                    .build();
            observedValuesVitalRepository.save(ov);

            // Check if critical
            boolean isCritical = isCritical(new CheckVitalDTO(vital.getVitalId(), dto.getGender(), dto.getAge(), vitalDTO.getValue()));

            result.add(new VitalStatusResponseDTO(vital.getVitalName(), type.getTypeName(), vitalDTO.getValue(), isCritical));
        }

        return result;
    }

    public List<VitalDTO> getVitalsForPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        List<ObservedValuesVital> observedVitals = observedValuesVitalRepository.findByPatientVital_Patient(patient);

        List<VitalDTO> vitals = new ArrayList<>();

        // You may calculate age from DOB
        int age = calculateAgeFromDob(patient);

        for (ObservedValuesVital ov : observedVitals) {
            VitalType vitalType = ov.getVitalType();
            Double value = ov.getValue();

            List<ValueRangesVital> ranges = valueRangesVitalRepository
                    .findByVital_VitalIdAndGenderAndMinAgeLessThanEqualAndMaxAgeGreaterThanEqual(
                            vitalType.getVital().getVitalId(),
                            patient.getGender(),
                            age,
                            age
                    );

            boolean isCritical = true;
            for (ValueRangesVital range : ranges) {
                if (value >= range.getMinValue() && value <= range.getMaxValue()) {
                    isCritical = false;
                    break;
                }
            }

            vitals.add(new VitalDTO(
                    vitalType.getVital().getVitalName(),  // "Blood Pressure"
                    vitalType.getTypeName(),              // "Systolic"
                    value,
                    ov.getDate(),
                    ov.getTime(),
                    isCritical
            ));
        }

        return vitals;
    }



    private int calculateAgeFromDob(Patient patient) {
        if (patient.getDob() == null) {
            System.out.println("Patient with ID {} has no DOB set."+ patient.getPatientId());
            return 0;
        }
        return Period.between(patient.getDob().toLocalDate(), LocalDate.now()).getYears();
    }



}


