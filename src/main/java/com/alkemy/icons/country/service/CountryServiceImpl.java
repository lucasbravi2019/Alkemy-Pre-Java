package com.alkemy.icons.country.service;

import com.alkemy.icons.continent.entity.Continent;
import com.alkemy.icons.continent.repo.ContinentRepo;
import com.alkemy.icons.country.dto.CountryBasicDTO;
import com.alkemy.icons.country.dto.CountryDTO;
import com.alkemy.icons.country.entity.Country;
import com.alkemy.icons.country.mapper.CountryMapper;
import com.alkemy.icons.country.repo.CountryRepo;
import com.alkemy.icons.icon.entity.Icon;
import com.alkemy.icons.icon.repo.IconRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    @Autowired
    private ContinentRepo continentRepo;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private IconRepo iconRepo;

    @Autowired
    private CountryMapper countryMapper;

    @Override
    public List<CountryDTO> getAllDetailed() throws Exception {
        List<Country> countries = countryRepo.findAll();
        List<CountryDTO> countriesDTO = countryMapper.country2CountryDTOList(countries);
        if(countriesDTO.isEmpty()) {
            throw new Exception("No countries found");
        }
        return countriesDTO;
    }

    @Override
    public List<CountryBasicDTO> getAll() throws Exception {
        List<Country> countries = countryRepo.findAll();
        List<CountryBasicDTO> countriesDTO = countryMapper.country2CountryBasicDTOList(countries);
        if(countriesDTO.isEmpty()) {
            throw new Exception("No countries found");
        }
        return countriesDTO;
    }

    @Override
    public CountryDTO createCountry(CountryDTO countryDTO) throws Exception {
        Country country = new Country();
        if(!countryDTO.getIconsNames().isEmpty()) {
            for(String name : countryDTO.getIconsNames()) {
                Icon icon = iconRepo.findByName(name).orElseThrow();
                countryDTO.addIcon(icon);
            }
        }
        country = countryMapper.countryDTO2Country(countryDTO);
        Continent continent = continentRepo.findById(countryDTO.getContinentId()).orElseThrow();
        country.setContinent(continent);
        countryDTO.setContinent(continent);
        country = countryRepo.save(country);
        countryDTO.setId(country.getId());
        return countryDTO;
    }

    @Override
    public CountryDTO getCountryById(Long id) throws NoSuchElementException {
        Country country = countryRepo.findById(id).orElseThrow();
        CountryDTO countryDTO = new CountryDTO();
        countryDTO = countryMapper.country2CountryDTO(country);
        return countryDTO;
    }

    @Override
    public CountryDTO getCountryByName(String name) throws NoSuchElementException {
        Country country = countryRepo.findByName(name).orElseThrow();
        CountryDTO countryDTO = new CountryDTO();
        countryDTO = countryMapper.country2CountryDTO(country);
        return countryDTO;
    }

    @Override
    public CountryDTO updateCountry(Long id, CountryDTO countryDTO) throws NoSuchElementException {
        Country country = countryRepo.findById(id).orElseThrow();
        country.setName(countryDTO.getName());
        country.setImage(countryDTO.getImage());
        country.setContinent(countryDTO.getContinent());
        country.setPopulation(countryDTO.getPopulation());
        country.setTotalSurface(countryDTO.getTotalSurface());
        country.setIcons(countryDTO.getIcons());
        countryDTO.setId(country.getId());
        return countryDTO;
    }

    @Override
    public void deleteCountry(Long id) throws NoSuchElementException {
        countryRepo.deleteById(id);
    }
}
