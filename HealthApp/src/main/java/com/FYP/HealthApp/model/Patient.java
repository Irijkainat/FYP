package com.FYP.HealthApp.model;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "Patients")
public class Patient {

    @Id
    @Column(name = "PatientId")
    private Integer patientId;


        @Column(name = "FullName", length = 100)
        private String fullName;

        @Column(name = "DOB")
        private LocalDateTime dob;

        @Column(name = "Gender", length = 10)
        private String gender;

}
