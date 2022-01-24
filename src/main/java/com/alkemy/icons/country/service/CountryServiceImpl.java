package com.alkemy.icons.country.service;

import com.alkemy.icons.continent.entity.Continent;
import com.alkemy.icons.continent.repo.ContinentRepo;
import com.alkemy.icons.country.dto.CountryBasicDTO;
import com.alkemy.icons.country.dto.CountryDTO;
import com.alkemy.icons.country.dto.CountryDetailedDTO;
import com.alkemy.icons.country.dto.CountryFilterDTO;
import com.alkemy.icons.country.entity.Country;
import com.alkemy.icons.country.mapper.CountryMapper;
import com.alkemy.icons.country.repo.CountryRepo;
import com.alkemy.icons.country.repo.specification.CountrySpecification;
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
    private CountrySpecification countrySpecification;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private IconRepo iconRepo;

    @Autowired
    private CountryMapper countryMapper;

    @Override
    public List<CountryDetailedDTO> getAllDetailed() {
        List<Country> countries = countryRepo.findAll();
        List<CountryDetailedDTO> countriesDTO = countryMapper.country2CountryDetailedDTOList(countries, true);
        return countriesDTO;
    }

    @Override
    public List<CountryBasicDTO> getAll() {
        List<Country> countries = countryRepo.findAll();
        List<CountryBasicDTO> countriesDTO = countryMapper.country2CountryBasicDTOList(countries);
        return countriesDTO;
    }

    @Override
    public List<CountryBasicDTO> getAllByFilters(String name, Long idContinent, String order) {
        CountryFilterDTO filterDTO = new CountryFilterDTO(name, idContinent, order);
        List<Country> countries = countryRepo.findAll(countrySpecification.getByFilters(filterDTO));
        List<CountryBasicDTO> countriesDTO = countryMapper.country2CountryBasicDTOList(countries);
        return countriesDTO;
    }

    @Override
    public CountryDetailedDTO createCountry(CountryDTO countryDTO) {
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
        country = countryRepo.save(country);
        CountryDetailedDTO dto = countryMapper.country2CountryDetailedDTO(country, true);
        return dto;
    }

    @Override
    public CountryDetailedDTO getCountryById(Long id) throws NoSuchElementException {
        Country country = countryRepo.findById(id).orElseThrow();
        CountryDetailedDTO countryDTO = new CountryDetailedDTO();
        countryDTO = countryMapper.country2CountryDetailedDTO(country, true);
        return countryDTO;
    }

    @Override
    public CountryDetailedDTO getCountryByName(String name) throws NoSuchElementException {
        Country country = countryRepo.findByName(name).orElseThrow();
        CountryDetailedDTO countryDTO = new CountryDetailedDTO();
        countryDTO = countryMapper.country2CountryDetailedDTO(country, true);
        return countryDTO;
    }

    @Override
    public CountryDetailedDTO updateCountry(Long id, CountryDTO countryDTO) throws NoSuchElementException {
        Country country = countryRepo.findById(id).orElseThrow();
        Continent continent = continentRepo.findById(countryDTO.getContinentId()).orElseThrow();
        country.setName(countryDTO.getName());
        country.setImage(countryDTO.getImage());
        country.setContinent(continent);
        country.setPopulation(countryDTO.getPopulation());
        country.setTotalSurface(countryDTO.getTotalSurface());
        if(!countryDTO.getIconsNames().isEmpty()) {
            for(String name : countryDTO.getIconsNames()) {
                Icon icon = iconRepo.findByName(name).orElseThrow();
                countryDTO.addIcon(icon);
            }
        }
        country.setIcons(countryDTO.getIcons());
        CountryDetailedDTO dto = countryMapper.country2CountryDetailedDTO(country, true);
        return dto;
    }

    @Override
    public void deleteCountry(Long id) throws NoSuchElementException {
        countryRepo.deleteById(id);
    }
}
