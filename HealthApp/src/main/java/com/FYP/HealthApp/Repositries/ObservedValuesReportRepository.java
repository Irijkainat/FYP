package com.FYP.HealthApp.Repositries;
import com.FYP.HealthApp.model.ObservedValuesReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservedValuesReportRepository extends JpaRepository<ObservedValuesReport, Long>{
}
