package com.alkemy.icons.continent.service;

import com.alkemy.icons.continent.dto.ContinentDTO;
import com.alkemy.icons.continent.entity.Continent;
import com.alkemy.icons.continent.mapper.ContinentMapper;
import com.alkemy.icons.continent.repo.ContinentRepo;
import com.alkemy.icons.country.entity.Country;
import com.alkemy.icons.country.repo.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContinentServiceImpl implements ContinentService {

    @Autowired
    private ContinentRepo continentRepo;

    @Autowired
    private ContinentMapper continentMapper;

    @Autowired
    private CountryRepo countryRepo;

    @Override
    public List<ContinentDTO> getAll() {
        List<Continent> continents = continentRepo.findAll();
        List<ContinentDTO> continentsDTO = continentMapper.continent2ContinentDTOList(continents);
        return continentsDTO;
    }

    @Override
    public ContinentDTO createContinent(ContinentDTO continentDTO) {
        Continent continent = continentMapper.continentDTO2Continent(continentDTO);
        continent = continentRepo.save(continent);
        continentDTO.setId(continent.getId());
        return continentDTO;
    }

    @Override
    public ContinentDTO getContinentById(Long id) throws NoSuchElementException {
        Continent continent = continentRepo.findById(id).orElseThrow();
        ContinentDTO continentDTO = continentMapper.continent2ContinentDTO(continent);
        return continentDTO;
    }

    @Override
    public ContinentDTO getContinentByName(String name) throws NoSuchElementException {
        Continent continent = continentRepo.findByName(name).orElseThrow();
        ContinentDTO continentDTO = continentMapper.continent2ContinentDTO(continent);
        return continentDTO;
    }

    @Override
    public ContinentDTO updateContinent(Long id, ContinentDTO continentDTO) throws NoSuchElementException {
        Continent continent = continentRepo.findById(id).orElseThrow();
        continentDTO.setId(continent.getId());
        continent.setName(continentDTO.getName());
        continent.setImage(continentDTO.getImage());
        return continentDTO;
    }

    @Override
    public void deleteContinent(Long id) throws NoSuchElementException {
        List<Country> countries = countryRepo.findAll();
        countries = countries.stream().filter(country -> country.getContinent() != null && country.getContinent().getId() == id).collect(Collectors.toList());
        for(Country country : countries) {
            country.setContinent(null);
        }
        continentRepo.deleteById(id);
    }
}
