package com.alkemy.icons.continent.service;

import com.alkemy.icons.continent.dto.ContinentBasicDTO;
import com.alkemy.icons.continent.dto.ContinentDTO;
import com.alkemy.icons.continent.dto.ContinentDetailedDTO;
import com.alkemy.icons.continent.entity.Continent;
import com.alkemy.icons.continent.mapper.ContinentMapper;
import com.alkemy.icons.continent.repo.ContinentRepo;
import com.alkemy.icons.country.entity.Country;
import com.alkemy.icons.country.repo.CountryRepo;
import com.alkemy.icons.general.service.CustomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContinentService extends CustomServiceImpl<
        ContinentMapper,
        ContinentRepo,
        Continent,
        ContinentDTO,
        ContinentBasicDTO,
        ContinentDetailedDTO> {

    public ContinentService(ContinentMapper mapper, ContinentRepo repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    @Autowired
    private CountryRepo countryRepo;

    @Override
    public void delete(Long id) throws NoSuchElementException {
        List<Country> countries = countryRepo.findAll();
        countries = countries.stream().filter(country -> country.getContinent() != null && country.getContinent().getId() == id).collect(Collectors.toList());
        for(Country country : countries) {
            country.setContinent(null);
        }
        repo.deleteById(id);
    }
}
