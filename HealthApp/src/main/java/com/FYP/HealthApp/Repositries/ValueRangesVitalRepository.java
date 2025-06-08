package com.FYP.HealthApp.Repositries;

import com.FYP.HealthApp.model.ValueRangesVital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ValueRangesVitalRepository extends JpaRepository<ValueRangesVital, Integer> {
    // Example method to find ranges based on obsId, gender and age
    List<ValueRangesVital> findByObsVIdAndGenderAndMinAgeLessThanEqualAndMaxAgeGreaterThanEqual(
            Integer obsVId, String gender, Integer age1, Integer age2);
}

