package com.FYP.HealthApp.Repositries;

import com.FYP.HealthApp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query(value = "SELECT p.ID as id, p.FullName as fullName, CONVERT(varchar, p.DOB, 23) as dob, p.Gender as gender, " +
            "STRING_AGG(d.DiseaseName, ', ') AS diseases " +
            "FROM Patients p " +
            "LEFT JOIN PatientDiseases pd ON p.ID = pd.PatientId " +
            "LEFT JOIN Diseases d ON pd.DiseaseId = d.ID " +
            "GROUP BY p.ID, p.FullName, p.DOB, p.Gender", nativeQuery = true)
    List<Object[]> getAllPatientsWithDiseases();

}
