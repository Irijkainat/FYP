package com.FYP.HealthApp.service;

import com.FYP.HealthApp.DTO.FieldValueDTO;
import com.FYP.HealthApp.DTO.ManualLabReportDTO;
import com.FYP.HealthApp.Repositries.FieldRepository;
import com.FYP.HealthApp.Repositries.ObservedValuesReportRepository;
import com.FYP.HealthApp.Repositries.ReportRepository;
import com.FYP.HealthApp.model.Field;
import com.FYP.HealthApp.model.ObservedValuesReport;
import com.FYP.HealthApp.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LabReportService {

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private FieldRepository fieldRepository;


    @Autowired
    private ObservedValuesReportRepository observedValuesReportRepository;

    public void saveManualReport(ManualLabReportDTO dto) {
        Report report = new Report();
        report.setReportName(dto.getReportName());
        report.setReportDate(dto.getDate());
        report.setReportTime(dto.getTime());
        reportRepository.save(report);

        // Step 2: Save each field value in ObservedValuesReport
        for (FieldValueDTO fieldDto : dto.getFieldValues()) {
            // Fetch the field entity
            Field field = fieldRepository.findById(fieldDto.getFieldId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid FieldId: " + fieldDto.getFieldId()));

            ObservedValuesReport ovr = new ObservedValuesReport();
            ovr.setReport(report);
            ovr.setField(field);
            ovr.setReportValue(fieldDto.getValue());
            ovr.setReportDate(dto.getDate());
            ovr.setReportTime(dto.getTime());
            observedValuesReportRepository.save(ovr);
        }
    }
               }

