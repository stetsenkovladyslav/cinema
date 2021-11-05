package com.example.admin.mapper;


import com.example.admin.dto.country.CountryDTO;
import com.example.admin.dto.country.CountryRequest;
import com.example.admin.model.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface CountryMapper extends CrudMapper<Country, CountryDTO, CountryRequest, CountryRequest> {

    CountryDTO toDTO(Country country);

    Country dtoToCountry(CountryDTO countryDTO);
}
