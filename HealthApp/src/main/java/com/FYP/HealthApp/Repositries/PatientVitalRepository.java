package com.FYP.HealthApp.Repositries;


import com.FYP.HealthApp.model.PatientVital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientVitalRepository extends JpaRepository<PatientVital, Long> {
    Optional<PatientVital> findByPatient_PatientIdAndVitals_VitalId(Long patientId, Long vitalId);

}


