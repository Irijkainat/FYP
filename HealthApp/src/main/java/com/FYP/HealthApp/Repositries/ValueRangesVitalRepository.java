package com.FYP.HealthApp.Repositries;

import com.FYP.HealthApp.model.ValueRangesVital;
import com.FYP.HealthApp.model.VitalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ValueRangesVitalRepository extends JpaRepository<ValueRangesVital, Long> {
    List<ValueRangesVital> findByVital_VitalIdAndGenderAndMinAgeLessThanEqualAndMaxAgeGreaterThanEqual(
            Long vitalId, String gender, Integer minAge, Integer maxAge
    );


}

