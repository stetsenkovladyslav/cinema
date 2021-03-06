package com.example.root.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "rates")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Rate extends BaseEntity {

    @Column(name = "value", nullable = true, precision = 2, scale = 2)
    private double value;

    @Column(name = "rate_count", nullable = true)
    private long rateCount;

    public Rate(Long id, int rating, int i) {
        super();
    }
}
