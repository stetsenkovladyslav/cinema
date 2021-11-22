package com.example.admin.mapper;


import com.example.data.dto.country.CountryDTO;
import com.example.data.dto.country.CountryRequest;
import com.example.data.model.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface CountryMapper extends CrudMapper<Country, CountryDTO, CountryRequest, CountryRequest> {

    CountryDTO toDTO(Country country);

    Country dtoToCountry(CountryDTO countryDTO);
}
