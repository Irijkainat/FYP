package com.FYP.HealthApp.Repositries;

import com.FYP.HealthApp.model.Vitals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VitalRepository extends JpaRepository<Vitals, Long>{
}
