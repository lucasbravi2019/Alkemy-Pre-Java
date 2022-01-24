package com.alkemy.icons.icon.service;

import com.alkemy.icons.icon.dto.IconBasicDTO;
import com.alkemy.icons.icon.dto.IconDTO;
import com.alkemy.icons.icon.dto.IconDetailedDTO;

import java.util.NoSuchElementException;
import java.util.Set;

public interface IconService {

    Set<IconDetailedDTO> getAllDetailed();
    Set<IconBasicDTO> getAll();
    Set<IconBasicDTO> getByFilters(String name, String date, Set<Long> cities, String order);
    IconDetailedDTO createIcon(IconDTO iconDTO);
    IconDetailedDTO getIconById(Long id) throws NoSuchElementException;
    IconDetailedDTO getIconByName(String name) throws NoSuchElementException;
    IconDetailedDTO updateIcon(Long id, IconDTO iconDTO) throws NoSuchElementException;
    void deleteIcon(Long id) throws NoSuchElementException;

}
