package com.FYP.HealthApp.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "Patients")
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID") // this is what your DB column is named
    private Long patientId;

        @Column(name = "FullName", length = 100)
        private String fullName;

        @Column(name = "DOB")
        private LocalDateTime dob;

        @Column(name = "Gender", length = 10)
        private String gender;

}
