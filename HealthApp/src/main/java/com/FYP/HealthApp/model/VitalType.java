package com.FYP.HealthApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VitalTypes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VitalType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long vitalTypeId;

    @ManyToOne
    @JoinColumn(name = "VitalId", nullable = false)
    private Vitals vital;

    @Column(name = "TypeName", nullable = false)
    private String typeName;
}


