package com.example.admin.repository;

import com.example.data.model.Country;
import com.example.data.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {

    List<Country> getCountryByIdIn(List<Long> ids);

}
