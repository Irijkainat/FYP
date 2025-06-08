package com.FYP.HealthApp.Repositries;

import com.FYP.HealthApp.model.VitalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VitalTypeRepository extends JpaRepository<VitalType, Long> {
    @Query("SELECT vt FROM VitalType vt WHERE vt.vital.vitalId = :vitalId")
    List<VitalType> findByVitalId(@Param("vitalId") Long vitalId);
}