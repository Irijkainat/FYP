package com.FYP.HealthApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Vitals")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vitals {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        private Long vitalId;

        @Column(name = "Name", nullable = false)
        private String vitalName;
    }


