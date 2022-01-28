package com.alkemy.icons.country.service;

import com.alkemy.icons.country.dto.CountryBasicDTO;
import com.alkemy.icons.country.dto.CountryDTO;
import com.alkemy.icons.country.dto.CountryDetailedDTO;
import com.alkemy.icons.country.dto.CountryFilterDTO;
import com.alkemy.icons.country.entity.Country;
import com.alkemy.icons.country.mapper.CountryMapper;
import com.alkemy.icons.country.repo.CountryRepo;
import com.alkemy.icons.country.repo.specification.CountrySpecification;
import com.alkemy.icons.general.service.CustomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService extends CustomServiceImpl<
        CountryMapper,
        CountryRepo,
        Country,
        CountryDTO,
        CountryBasicDTO,
        CountryDetailedDTO> {

    @Autowired
    private CountrySpecification countrySpecification;

    public CountryService(CountryMapper mapper, CountryRepo repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    public List<CountryBasicDTO> getByFilters(String name, Long idContinent, String order) {
        CountryFilterDTO filterDTO = new CountryFilterDTO(name, idContinent, order);
        List<CountryBasicDTO> dto = mapper.toBasicDTOList(repo.findAll(countrySpecification.getByFilters(filterDTO)));
        return dto;
    }

}
