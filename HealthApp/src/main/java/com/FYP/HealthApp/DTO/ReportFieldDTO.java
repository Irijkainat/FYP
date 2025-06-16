package com.FYP.HealthApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportFieldDTO {
    private String reportName;
    private String fieldName;
    private Double value;
    private LocalDate date;
    private LocalTime time;
    private Boolean isCritical;
}
