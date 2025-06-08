package com.FYP.HealthApp.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientVitalDTO {
    private Long patientId;
    private Long vitalId;
}
