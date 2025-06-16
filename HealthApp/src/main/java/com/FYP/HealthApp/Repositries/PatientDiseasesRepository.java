package com.FYP.HealthApp.Repositries;

import com.FYP.HealthApp.model.PatientDiseases;
import com.FYP.HealthApp.model.PatientDiseasesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDiseasesRepository extends JpaRepository<PatientDiseases, PatientDiseasesId> {
}
