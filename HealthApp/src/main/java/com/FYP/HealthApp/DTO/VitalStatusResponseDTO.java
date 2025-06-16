package com.FYP.HealthApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VitalStatusResponseDTO {
    private String vitalName;
    private String vitalType;
    private Double value;
    private boolean critical;
}
