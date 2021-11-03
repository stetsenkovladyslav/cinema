package com.example.admin.mapper;


import com.example.admin.DTO.CountryDTO;
import com.example.admin.DTO.GenreDTO;
import com.example.admin.model.Country;
import com.example.admin.model.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface CountryMapper {


    CountryDTO toDTO(Country country);

    Country dtoToCountry(CountryDTO countryDTO);
}
