package com.FYP.HealthApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Recommend")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long recommendId;

    @Column(name = "DoctorId")
    private Long doctorId;

    @Column(name = "PatientId")
    private Long patientId;

    @Column(name = "TestId")
    private Long testId;
}
