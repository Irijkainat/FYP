package com.FYP.HealthApp.Repositries;
import com.FYP.HealthApp.DTO.ReportFieldDTO;
import com.FYP.HealthApp.model.ObservedValuesReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObservedValuesReportRepository extends JpaRepository<ObservedValuesReport, Long>{
    @Query("SELECT o FROM ObservedValuesReport o JOIN o.report r WHERE r.patient.patientId = :patientId")
    List<ObservedValuesReport> findByPatientId(@Param("patientId") Long patientId);
    @Query("""
SELECT DISTINCT new com.FYP.HealthApp.DTO.ReportFieldDTO(
    r.reportName,
    f.fieldName,
    o.reportValue,
    o.reportDate,
    o.reportTime,
    CASE 
        WHEN vr.minValue IS NOT NULL AND vr.maxValue IS NOT NULL AND (o.reportValue < vr.minValue OR o.reportValue > vr.maxValue)
        THEN true
        ELSE false
    END
)
FROM ObservedValuesReport o
JOIN o.report r
JOIN o.field f
LEFT JOIN ValueRangesField vr ON vr.field = f
WHERE r.patient.patientId = :patientId
""")
    List<ReportFieldDTO> getFieldDataByPatientId(@Param("patientId") Long patientId);

}
