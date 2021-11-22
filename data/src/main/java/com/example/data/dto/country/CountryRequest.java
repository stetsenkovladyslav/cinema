package com.example.data.dto.country;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CountryRequest {
    @NotBlank(message = "Country name is mandatory")
    private String countryName;
}
