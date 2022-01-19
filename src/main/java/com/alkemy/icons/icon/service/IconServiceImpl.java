package com.alkemy.icons.icon.service;

import com.alkemy.icons.country.entity.Country;
import com.alkemy.icons.country.repo.CountryRepo;
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
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@Transactional
public class IconServiceImpl implements IconService {

    @Autowired
    private IconRepo iconRepo;

    @Autowired
    private IconSpecification iconSpecification;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private IconMapper iconMapper;

    @Override
    public List<IconBasicDTO> getAll() throws Exception {
        List<Icon> icons = iconRepo.findAll();
        if(icons.isEmpty()) {
            throw new Exception("No icons found");
        }
        List<IconBasicDTO> iconsDTO = iconMapper.icon2IconBasicDTOList(icons);
        return iconsDTO;
    }

    @Override
    public List<IconDetailedDTO> getByFilters(String name, String date, Set<Long> cities, String order) throws Exception {
        IconFiltersDTO filtersDTO = new IconFiltersDTO(name, date, cities, order);
        List<Icon> icons = iconRepo.findAll(iconSpecification.getByFilters(filtersDTO));
        List<IconDetailedDTO> iconsDTO = iconMapper.icon2IconDetailedDTOList(icons);
        return iconsDTO;
    }

    @Override
    public List<IconDetailedDTO> getAllDetailed() throws Exception {
        List<Icon> icons = iconRepo.findAll();
        if(icons.isEmpty()) {
            throw new Exception("No icons found");
        }
        List<IconDetailedDTO> iconsDTO = iconMapper.icon2IconDetailedDTOList(icons);
        return iconsDTO;
    }

    @Override
    public IconDetailedDTO createIcon(IconDTO iconDTO) throws Exception {
        Icon icon = new Icon();
        icon = iconMapper.iconDTO2Icon(iconDTO);
        icon = iconRepo.save(icon);
        IconDetailedDTO dto = iconMapper.icon2IconDetailedDTO(icon);
        return dto;
    }

    @Override
    public IconDetailedDTO getIconById(Long id) throws NoSuchElementException {
        Icon icon = iconRepo.findById(id).orElseThrow();
        IconDetailedDTO iconDTO = iconMapper.icon2IconDetailedDTO(icon);
        return iconDTO;
    }

    @Override
    public IconDetailedDTO getIconByName(String name) throws NoSuchElementException {
        Icon icon = iconRepo.findByName(name).orElseThrow();
        IconDetailedDTO iconDTO = iconMapper.icon2IconDetailedDTO(icon);
        return iconDTO;
    }

    @Override
    public IconDetailedDTO updateIcon(Long id, IconDTO iconDTO) throws NoSuchElementException {
        Icon icon = iconRepo.findById(id).orElseThrow();
        icon.setImage(iconDTO.getImage());
        icon.setName(iconDTO.getName());
        icon.setCreatedAt(iconDTO.getCreatedAt());
        icon.setHeight(iconDTO.getHeight());
        icon.setHistory(iconDTO.getHistory());
        IconDetailedDTO dto = iconMapper.icon2IconDetailedDTO(icon);
        return dto;
    }

    @Override
    public void deleteIcon(Long id) throws NoSuchElementException {
        iconRepo.deleteById(id);
    }
}
