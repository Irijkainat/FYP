package com.FYP.HealthApp.service;


import com.FYP.HealthApp.DTO.PatientDTO;
import com.FYP.HealthApp.model.Patient;
import com.FYP.HealthApp.Repositries.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient addPatient(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setPatientId(patientDTO.getPatientId()); // manually set ID
        patient.setFullName(patientDTO.getFullName());
        patient.setDob(patientDTO.getDob());
        patient.setGender(patientDTO.getGender());

        return patientRepository.save(patient);
    }
}
