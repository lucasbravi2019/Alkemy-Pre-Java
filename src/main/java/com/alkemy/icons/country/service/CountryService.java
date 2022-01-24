package com.alkemy.icons.country.service;

import com.alkemy.icons.country.dto.CountryBasicDTO;
import com.alkemy.icons.country.dto.CountryDTO;
import com.alkemy.icons.country.dto.CountryDetailedDTO;

import java.util.List;
import java.util.NoSuchElementException;

public interface CountryService {

    List<CountryDetailedDTO> getAllDetailed();
    List<CountryBasicDTO> getAll();
    List<CountryBasicDTO> getAllByFilters(String name, Long idContinent, String order);
    CountryDetailedDTO createCountry(CountryDTO countryDTO);
    CountryDetailedDTO getCountryById(Long id) throws NoSuchElementException;
    CountryDetailedDTO getCountryByName(String name) throws NoSuchElementException;
    CountryDetailedDTO updateCountry(Long id, CountryDTO countryDTO) throws NoSuchElementException;
    void deleteCountry(Long id) throws NoSuchElementException;

}
