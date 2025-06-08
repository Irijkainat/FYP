package com.FYP.HealthApp.Repositries;

import com.FYP.HealthApp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
