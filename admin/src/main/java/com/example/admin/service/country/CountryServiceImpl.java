package com.example.admin.service.country;

import com.example.data.dto.country.CountryDTO;
import com.example.data.dto.country.CountryRequest;
import com.example.admin.mapper.CountryMapper;
import com.example.data.model.Country;
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
    public CountryDTO addCountry(CountryRequest countryRequest) {
        Country country = countryMapper.create(countryRequest);
        return countryMapper.mapToDTO(countryRepository.save(country));
    }

    @Override
    public void deleteCountryById(long id) {
    countryRepository.deleteById(id);
    }

    @Override
    public CountryDTO updateCountry(long id, CountryRequest countryRequest) {
        Country country = countryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Country with id:{" + id + "} does not exist"));
        return countryMapper.mapToDTO(countryRepository.save(countryMapper.update(country, countryRequest)));
    }

    @Override
    public CountryDTO findCountryById(long id) {
        return countryMapper.mapToDTO(countryRepository.getById(id));
    }

    @Override
    public Page<Country> getAllCountries(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }
}
