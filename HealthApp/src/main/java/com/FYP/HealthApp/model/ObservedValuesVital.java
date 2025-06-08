package com.FYP.HealthApp.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "ObservedValuesVital")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObservedValuesVital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ObsVId")
    private Long obsVId;

    @ManyToOne
    @JoinColumn(name = "PatientVitalId")
    private PatientVital patientVital;

    @Column(name = "Value", nullable = false)
    private Double value;

    @Column(name = "Date", nullable = false)
    private LocalDate date;

    @Column(name = "Time", nullable = false)
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "VitalTypeId")
    private VitalType vitalType;
}


