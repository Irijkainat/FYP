package com.FYP.HealthApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LabTests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabTest {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "LabTestId")
        private Long labTestId;

        @Column(name = "LabId")
        private Long labId;

        @Column(name = "TestName", nullable = false)
        private String testName;


}
