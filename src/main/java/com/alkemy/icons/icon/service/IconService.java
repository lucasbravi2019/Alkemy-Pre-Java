package com.alkemy.icons.icon.service;

import com.alkemy.icons.country.dto.CountryBasicDTO;
import com.alkemy.icons.country.dto.CountryFilterDTO;
import com.alkemy.icons.general.service.CustomServiceImpl;
import com.alkemy.icons.icon.dto.IconBasicDTO;
import com.alkemy.icons.icon.dto.IconDTO;
import com.alkemy.icons.icon.dto.IconDetailedDTO;
import com.alkemy.icons.icon.dto.IconFiltersDTO;
import com.alkemy.icons.icon.entity.Icon;
import com.alkemy.icons.icon.mapper.IconMapper;
import com.alkemy.icons.icon.repo.IconRepo;
import com.alkemy.icons.icon.repo.specification.IconSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class IconService extends CustomServiceImpl<
        IconMapper,
        IconRepo,
        Icon,
        IconDTO,
        IconBasicDTO,
        IconDetailedDTO> {

    @Autowired
    private IconSpecification iconSpecification;

    public IconService(IconMapper mapper, IconRepo repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    public List<IconBasicDTO> getByFilters(String name, String date, Set<Long> cities, String order) {
        IconFiltersDTO filterDTO = new IconFiltersDTO(name, date, cities, order);
        List<IconBasicDTO> dto = mapper.toBasicDTOList(repo.findAll(iconSpecification.getByFilters(filterDTO)));
        return dto;
    }

}
