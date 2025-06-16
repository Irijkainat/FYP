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
    @Column(name = "ID")
    private Long obsRId;


@ManyToOne
@JoinColumn(name = "FieldId", nullable = false)
private Field field;


    @ManyToOne
    @JoinColumn(name = "ReportId", nullable = false)
    private Report report;

    @Column(name = "Value", nullable = false)
    private Double reportValue;

    @Column(name = "Date")
    private LocalDate reportDate;

    @Column(name = "Time")
    private LocalTime reportTime;
}
