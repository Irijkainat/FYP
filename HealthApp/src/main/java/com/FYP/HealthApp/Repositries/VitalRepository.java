package com.FYP.HealthApp.Repositries;

import com.FYP.HealthApp.model.Vitals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VitalRepository extends JpaRepository<Vitals, Long>{
    Optional<Vitals> findByVitalName(String vitalName);

}
