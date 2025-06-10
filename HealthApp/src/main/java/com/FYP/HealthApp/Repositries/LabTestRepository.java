package com.FYP.HealthApp.Repositries;

import com.FYP.HealthApp.model.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabTestRepository  extends JpaRepository<LabTest, Long> {
}
