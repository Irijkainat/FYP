package com.FYP.HealthApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ValueRangesVital")
public class ValueRangesVital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer valueRangesId;

    @ManyToOne
    @JoinColumn(name = "VitalId", nullable = false)
    private Vitals vital;

    @Column(name = "Gender", nullable = false)
    private String gender;

    @Column(name = "MinValue")
    private Float minValue;

    @Column(name = "MaxValue")
    private Float maxValue;

    @Column(name = "MinAge")
    private Integer minAge;

    @Column(name = "MaxAge")
    private Integer maxAge;

}
