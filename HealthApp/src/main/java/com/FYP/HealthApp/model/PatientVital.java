package com.FYP.HealthApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "PatientVitals")
@AllArgsConstructor
@NoArgsConstructor
public class PatientVital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long patientVitalId;

    @ManyToOne
    @JoinColumn(name = "PatientId", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "VitalId", nullable = false)
    private Vitals vitals;

    public PatientVital(Patient patient, Vitals vitals) {
        this.patient = patient;
        this.vitals = vitals;
    }

}
