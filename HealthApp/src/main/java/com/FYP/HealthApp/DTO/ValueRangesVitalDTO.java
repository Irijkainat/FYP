package com.FYP.HealthApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValueRangesVitalDTO {
    private Integer obsVId;
    private String gender;
    private Float minValue;
    private Float maxValue;
    private Integer minAge;
    private Integer maxAge;
}
