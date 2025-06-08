package com.FYP.HealthApp.Repositries;


import com.FYP.HealthApp.model.PatientVital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientVitalRepository extends JpaRepository<PatientVital, Long> {

}


