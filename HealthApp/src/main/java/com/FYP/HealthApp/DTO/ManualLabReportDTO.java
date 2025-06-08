package com.FYP.HealthApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManualLabReportDTO {
    private Long patientId;
    private Long labTestId;
    private String reportName;
    private LocalDate date;
    private LocalTime time;
    private List<FieldValueDTO> fieldValues;
}
