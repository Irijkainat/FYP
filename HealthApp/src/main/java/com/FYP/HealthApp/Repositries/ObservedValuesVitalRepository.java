package com.FYP.HealthApp.Repositries;


import com.FYP.HealthApp.model.ObservedValuesVital;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ObservedValuesVitalRepository extends JpaRepository<ObservedValuesVital, Long> {

    // Find all observed values by patientVital's patient ID
    List<ObservedValuesVital> findByPatientVital_Patient_PatientId(Long patientId);
}
