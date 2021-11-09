package com.example.admin.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "rates")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Rate extends BaseEntity {

    @Column(name = "value", nullable = false, precision = 2, scale = 2)
    private BigDecimal value;

    @Column(name = "rate_count", nullable = false)
    private long rateCount;
}
