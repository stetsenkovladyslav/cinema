package com.example.admin.mapper;


import com.example.root.dto.country.CountryDTO;
import com.example.root.dto.country.CountryRequest;
import com.example.root.model.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface CountryMapper extends CrudMapper<Country, CountryDTO, CountryRequest, CountryRequest> {

    CountryDTO toDTO(Country country);

    Country dtoToCountry(CountryDTO countryDTO);
}
