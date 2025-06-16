package com.FYP.HealthApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientVitalEntryDTO {
    private Long patientId;
    private String gender;
    private Integer age;
    private List<SingleVitalEntryDTO> vitals;
}
