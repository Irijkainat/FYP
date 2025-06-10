package com.FYP.HealthApp.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "ObservedValuesReport")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ObservedValuesReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ObsRId")
    private Long obsRId;

//    @Column(name = "FieldId", nullable = false)
//    private Long fieldId;
@ManyToOne
@JoinColumn(name = "FieldId", nullable = false)
private Field field;


    @ManyToOne
    @JoinColumn(name = "ReportId", nullable = false)
    private Report report;

    @Column(name = "ReportValue", nullable = false)
    private Double reportValue;

    @Column(name = "ReportDate")
    private LocalDate reportDate;

    @Column(name = "ReportTime")
    private LocalTime reportTime;
}
