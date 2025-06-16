package com.FYP.HealthApp.service;


import com.FYP.HealthApp.DTO.AssignDiseaseDTO;
import com.FYP.HealthApp.DTO.PatientDTO;
import com.FYP.HealthApp.DTO.PatientWithDiseasesDTO;
import com.FYP.HealthApp.Repositries.PatientDiseasesRepository;
import com.FYP.HealthApp.model.Patient;
import com.FYP.HealthApp.Repositries.PatientRepository;
import com.FYP.HealthApp.model.PatientDiseases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientDiseasesRepository patientDiseasesRepository;

    public void assignDiseasesToPatient(AssignDiseaseDTO assignDiseaseDTO) {
        List<PatientDiseases> patientDiseasesList = assignDiseaseDTO.getDiseaseIds().stream()
                .map(diseaseId -> {
                    PatientDiseases pd = new PatientDiseases();
                    pd.setPatientId(assignDiseaseDTO.getPatientId());
                    pd.setDiseaseId(diseaseId);
                    return pd;
                }).toList();

        patientDiseasesRepository.saveAll(patientDiseasesList);
    }
    public Patient addPatient(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setPatientId(patientDTO.getPatientId());
        patient.setFullName(patientDTO.getFullName());
        patient.setDob(patientDTO.getDob());
        patient.setGender(patientDTO.getGender());

        return patientRepository.save(patient);
    }

}
