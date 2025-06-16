package com.FYP.HealthApp.Repositries;


import com.FYP.HealthApp.model.ObservedValuesVital;
import com.FYP.HealthApp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ObservedValuesVitalRepository extends JpaRepository<ObservedValuesVital, Long> {

    // Corrected method
    List<ObservedValuesVital> findByPatientVital_Patient(Patient patient);

    // Or this if you want by ID
    List<ObservedValuesVital> findByPatientVital_Patient_PatientId(Long patientId);
}
