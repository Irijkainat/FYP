package com.FYP.HealthApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long reportId;

    @Column(name = "ReportName", nullable = false)
    private String reportName;


    @ManyToOne
    @JoinColumn(name = "RecommendId", nullable = true)
    private Recommend recommend;

    @ManyToOne
    @JoinColumn(name = "PatientId", referencedColumnName = "ID")
    private Patient patient;
}
