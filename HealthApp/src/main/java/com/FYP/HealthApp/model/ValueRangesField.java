package com.FYP.HealthApp.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ValueRangesFields")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValueRangesField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "FieldId")
    private Field field;

    @Column(name = "MinValue")
    private Double minValue;

    @Column(name = "MaxValue")
    private Double maxValue;
}
