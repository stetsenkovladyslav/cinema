package com.example.root.dto.country;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {
    @NotBlank
    @NotNull
    Long id;
    @NotBlank(message = "Country name is mandatory")
    private String countryName;

}
