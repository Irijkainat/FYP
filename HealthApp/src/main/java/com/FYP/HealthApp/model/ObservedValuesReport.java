package com.FYP.HealthApp.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "ObservedValuesReport")
@Data

public class ObservedValuesReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long obsRId;

    @Column(name = "FieldId")
    private Long fieldId;

    @ManyToOne
    @JoinColumn(name = "ReportId")
    private Report report;

    @Column(name = "ReportValue")
    private Double reportValue;

    @Column(name = "ReportDate")
    private LocalDate reportDate;

    @Column(name = "ReportTime")
    private LocalTime reportTime;
}
