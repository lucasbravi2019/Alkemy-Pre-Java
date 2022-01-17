package com.alkemy.icons.icon.service;

import com.alkemy.icons.country.entity.Country;
import com.alkemy.icons.country.repo.CountryRepo;
import com.alkemy.icons.icon.dto.IconBasicDTO;
import com.alkemy.icons.icon.dto.IconDTO;
import com.alkemy.icons.icon.entity.Icon;
import com.alkemy.icons.icon.mapper.IconMapper;
import com.alkemy.icons.icon.repo.IconRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class IconServiceImpl implements IconService {

    @Autowired
    private IconRepo iconRepo;

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
    public List<IconDTO> getAllDetailed() throws Exception {
        List<Icon> icons = iconRepo.findAll();
        if(icons.isEmpty()) {
            throw new Exception("No icons found");
        }
        List<IconDTO> iconsDTO = iconMapper.icon2IconDTOList(icons);
        return iconsDTO;
    }

    @Override
    public Icon createIcon(IconDTO iconDTO) throws Exception {
        Icon icon = new Icon();
        icon = iconMapper.iconDTO2Icon(iconDTO);
        for(String name : iconDTO.getCountryNames()) {
            Country country = countryRepo.findByName(name).orElseThrow();
            icon.addCountry(country);
        }
        icon = iconRepo.save(icon);
        iconDTO.setId(icon.getId());
        return icon;
    }

    @Override
    public IconDTO getIconById(Long id) throws NoSuchElementException {
        Icon icon = iconRepo.findById(id).orElseThrow();
        IconDTO iconDTO = iconMapper.icon2IconDTO(icon);
        return iconDTO;
    }

    @Override
    public IconDTO getIconByName(String name) throws NoSuchElementException {
        Icon icon = iconRepo.findByName(name).orElseThrow();
        IconDTO iconDTO = iconMapper.icon2IconDTO(icon);
        return iconDTO;
    }

    @Override
    public IconDTO updateIcon(Long id, IconDTO iconDTO) throws NoSuchElementException {
        Icon icon = iconRepo.findById(id).orElseThrow();
        icon.setImage(iconDTO.getImage());
        icon.setCountries(iconDTO.getCountries());
        icon.setName(iconDTO.getName());
        icon.setCreatedAt(iconDTO.getCreatedAt());
        icon.setHeight(iconDTO.getHeight());
        icon.setHistory(iconDTO.getHistory());
        iconDTO.setId(icon.getId());
        return iconDTO;
    }

    @Override
    public void deleteIcon(Long id) throws NoSuchElementException {
        iconRepo.deleteById(id);
    }
}
