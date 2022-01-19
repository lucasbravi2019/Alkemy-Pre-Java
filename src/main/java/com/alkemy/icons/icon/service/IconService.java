package com.alkemy.icons.icon.service;

import com.alkemy.icons.icon.dto.IconBasicDTO;
import com.alkemy.icons.icon.dto.IconDTO;
import com.alkemy.icons.icon.dto.IconDetailedDTO;
import com.alkemy.icons.icon.entity.Icon;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

public interface IconService {

    List<IconDetailedDTO> getAllDetailed() throws Exception;
    List<IconBasicDTO> getAll() throws Exception;
    List<IconDetailedDTO> getByFilters(String name, String date, Set<Long> cities, String order) throws Exception;
    IconDetailedDTO createIcon(IconDTO iconDTO) throws Exception;
    IconDetailedDTO getIconById(Long id) throws NoSuchElementException;
    IconDetailedDTO getIconByName(String name) throws NoSuchElementException;
    IconDetailedDTO updateIcon(Long id, IconDTO iconDTO) throws NoSuchElementException;
    void deleteIcon(Long id) throws NoSuchElementException;

}
