package com.FYP.HealthApp.service;

import com.FYP.HealthApp.DTO.*;
import com.FYP.HealthApp.Repositries.*;
import com.FYP.HealthApp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Long addVital(VitalDTO vitalDTO) {
        Vitals vital = Vitals.builder()
                .vitalName(vitalDTO.getVitalName())
                .build();
        vitalsRepository.save(vital);
        return vital.getVitalId();
    }
    public Long addVitalType(VitalTypeDTO vitalTypeDTO) {
        Vitals vital = vitalsRepository.findById(vitalTypeDTO.getVitalId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Vital ID"));

        VitalType vitalType = VitalType.builder()
                .vital(vital)
                .typeName(vitalTypeDTO.getTypeName())
                .build();

        vitalTypeRepository.save(vitalType);
        return vitalType.getVitalTypeId();
    }
    // Add mapping of patient to a vital (e.g. Ali -> Blood Pressure)
    public Long addPatientVital(PatientVitalDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Patient ID"));

        Vitals vitals = vitalsRepository.findById(dto.getVitalId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Vital ID"));

        PatientVital pv = new PatientVital();
        pv.setPatient(patient);
        pv.setVitals(vitals);
        PatientVital saved = patientVitalRepository.save(pv);
        return saved.getPatientVitalId();
    }


    public Long addObservedValue(ObservedValueDTO dto) {
        PatientVital patientVital = patientVitalRepository.findById(dto.getPatientVitalId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid PatientVital ID"));

        VitalType vitalType = vitalTypeRepository.findById(dto.getVitalTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid VitalType ID"));

        ObservedValuesVital observedValue = ObservedValuesVital.builder()
                .patientVital(patientVital)
                .value(dto.getValue())
                .date(dto.getDate())
                .time(dto.getTime())
                .vitalType(vitalType)
                .build();

        observedValuesVitalRepository.save(observedValue);

        return observedValue.getObsVId();
    }

    public List<ObservedValuesVital> getObservedValuesByPatient(Long patientId) {
        return observedValuesVitalRepository.findByPatientVital_Patient_PatientId(patientId);
    }
    public void saveRange(ValueRangesVitalDTO dto) {
        ValueRangesVital entity = new ValueRangesVital();
        entity.setObsVId(dto.getObsVId());
        entity.setGender(dto.getGender());
        entity.setMinValue(dto.getMinValue());
        entity.setMaxValue(dto.getMaxValue());
        entity.setMinAge(dto.getMinAge());
        entity.setMaxAge(dto.getMaxAge());

        repository.save(entity);
    }

    public List<ValueRangesVital> getApplicableRanges(Integer obsVId, String gender, Integer age) {
        return repository.findByObsVIdAndGenderAndMinAgeLessThanEqualAndMaxAgeGreaterThanEqual(
                obsVId, gender, age, age);
    }
}


