package com.FYP.HealthApp.service;

import com.FYP.HealthApp.DTO.FieldValueDTO;
import com.FYP.HealthApp.DTO.ManualLabReportDTO;
import com.FYP.HealthApp.DTO.PatientLabReportDTO;
import com.FYP.HealthApp.Repositries.FieldRepository;
import com.FYP.HealthApp.Repositries.ObservedValuesReportRepository;
import com.FYP.HealthApp.Repositries.ReportRepository;
import com.FYP.HealthApp.Repositries.ValueRangesFieldRepository;
import com.FYP.HealthApp.model.Field;
import com.FYP.HealthApp.model.ObservedValuesReport;
import com.FYP.HealthApp.model.Report;
import com.FYP.HealthApp.model.ValueRangesField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LabReportService {

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private ObservedValuesReportRepository observedValuesReportRepository;
    @Autowired
    private ValueRangesFieldRepository valueRangesFieldRepository;

    public void saveManualReport(ManualLabReportDTO dto) {
        Report report = new Report();
        report.setReportName(dto.getReportName());
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

    public List<PatientLabReportDTO> getReportsByPatientId(Long patientId) {
        List<ObservedValuesReport> reports = observedValuesReportRepository.findByPatientId(patientId);
        List<PatientLabReportDTO> dtoList = new ArrayList<>();

        for (ObservedValuesReport ovr : reports) {
            Field field = ovr.getField();
            Double value = ovr.getReportValue();

            // Fetch value range for this field
            ValueRangesField range = valueRangesFieldRepository.findByField(field);

            boolean isCritical = false;
            if (range != null) {
                if (range.getMinValue() != null && value < range.getMinValue()) {
                    isCritical = true;
                } else if (range.getMaxValue() != null && value > range.getMaxValue()) {
                    isCritical = true;
                }
            }

            dtoList.add(new PatientLabReportDTO(
                    ovr.getReport().getReportName(),
                    field.getFieldName(),
                    value,
                    ovr.getReportDate(),
                    ovr.getReportTime(),
                    isCritical
            ));
        }

        return dtoList;
    }

}

