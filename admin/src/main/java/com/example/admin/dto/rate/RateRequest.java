package com.example.admin.dto.rate;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
public class RateRequest {


    @NotNull(message = "rateValue is mandatory")
    @DecimalMin(value = "1.0", message = "Rate must not be less than 1.0")
    @DecimalMax(value = "10.0", message = "Rate must not be greater than 10.0")
    @Digits(integer = 2, fraction = 2, message = "Numeric value out of bounds (<2 digits>.<2 digits> expected)")
    private BigDecimal value;

    @PositiveOrZero(message = "Count must not be less than 0")
    private long rateCount;
}
