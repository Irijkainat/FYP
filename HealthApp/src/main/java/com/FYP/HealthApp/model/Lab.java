package com.FYP.HealthApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Labs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LabId")
    private Long labId;


    @Column(name = "LabName", nullable = false)
    private String labName;
}
