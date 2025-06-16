package com.FYP.HealthApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckVitalDTO {

    private Long  vitalId;
    private String gender;
    private Integer age;
    private Double value;
}
