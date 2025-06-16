package com.FYP.HealthApp.service;


import com.FYP.HealthApp.DTO.PatientWithDiseasesDTO;
import com.FYP.HealthApp.Repositries.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private PatientRepository patientRepository;

    public List<PatientWithDiseasesDTO> getAllPatientsWithDiseases() {
        List<Object[]> rawList = patientRepository.getAllPatientsWithDiseases();
        List<PatientWithDiseasesDTO> result = new ArrayList<>();

        for (Object[] row : rawList) {
            PatientWithDiseasesDTO dto = new PatientWithDiseasesDTO();
            dto.setId((Integer) row[0]);
            dto.setFullName((String) row[1]);
            dto.setDob((String) row[2]);
            dto.setGender((String) row[3]);

            String diseasesString = (String) row[4];
            if (diseasesString != null) {
                dto.setDiseases(Arrays.asList(diseasesString.split(", ")));
            } else {
                dto.setDiseases(new ArrayList<>()); // empty list if no diseases
            }

            result.add(dto);
        }

        return result;
    }

}
