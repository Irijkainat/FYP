package com.FYP.HealthApp.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleVitalEntryDTO {
    private String vitalName;
    private String vitalTypeName;
    private Double value;
    private LocalDate date;
    private LocalTime time;
}
