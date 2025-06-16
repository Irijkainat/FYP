package com.FYP.HealthApp.DTO;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientWithDiseasesDTO {
    private int id;
    private String fullName;
    private String dob;
    private String gender;
    private List<String> diseases;

}
