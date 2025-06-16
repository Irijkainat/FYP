package com.FYP.HealthApp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "PatientDiseases")
@IdClass(PatientDiseasesId.class)
@AllArgsConstructor
@NoArgsConstructor
public class PatientDiseases {    @Id
@Column(name = "PatientId")
private int patientId;

    @Id
    @Column(name = "DiseaseId")
    private int diseaseId;
}
