package com.FYP.HealthApp.Repositries;

import com.FYP.HealthApp.model.Field;
import com.FYP.HealthApp.model.ValueRangesField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValueRangesFieldRepository extends JpaRepository<ValueRangesField, Long> {
    ValueRangesField findByField(Field field);
}
