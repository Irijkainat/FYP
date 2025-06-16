package com.FYP.HealthApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Fields")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long fieldId;

    @Column(name = "LabId")
    private Long labId;

    @Column(name = "Name", nullable = false)
    private String fieldName;

    @ManyToOne
    @JoinColumn(name = "LabTestId")
    private LabTest labTest;


}
