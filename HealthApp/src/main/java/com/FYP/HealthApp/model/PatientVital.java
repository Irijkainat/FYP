package com.FYP.HealthApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "PatientVitals")
public class PatientVital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PatientVitalId")
    private Long patientVitalId;

    @ManyToOne
    @JoinColumn(name = "PatientId", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "VitalId", nullable = false)
    private Vitals vitals;
}
