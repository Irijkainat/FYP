package com.FYP.HealthApp.Repositries;

import com.FYP.HealthApp.model.Field;
import com.FYP.HealthApp.model.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
    List<Field> findByLabTest(LabTest labTest);

}
