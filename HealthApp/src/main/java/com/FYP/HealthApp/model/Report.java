package com.FYP.HealthApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Reports")
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReportId")
    private Long reportId;

    @Column(name = "ReportName", nullable = false)
    private String reportName;

    @Column(name = "ReportDate")
    private LocalDate reportDate;

    @Column(name = "ReportTime")
    private LocalTime reportTime;
}
