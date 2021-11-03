package com.example.admin.service.country;

import com.example.admin.DTO.CountryDTO;
import com.example.admin.mapper.CountryMapper;
import com.example.admin.model.Country;
import com.example.admin.model.Genre;
import com.example.admin.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService{
    private final CountryMapper countryMapper;
    private final CountryRepository countryRepository;

    @Override
    public Country addCountry(CountryDTO countryDTO) {
        return countryRepository.save(new Country(countryDTO.getCountryName()));
    }

    @Override
    public void deleteCountryById(long id) {
    countryRepository.deleteById(id);
    }

    @Override
    public void updateCountry(long id, CountryDTO countryDTO) {
        countryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Country with id:{" + id + "} does not exist"));
        Country updatedCountry = countryMapper.dtoToCountry(countryDTO);
        countryRepository.save(updatedCountry);
    }

    @Override
    public Country getCountryById(long id) {
        return countryRepository.getById(id);
    }

    @Override
    public Page<Country> getAllCountries(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }
}
