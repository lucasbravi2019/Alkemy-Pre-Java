package com.alkemy.icons.icon.service;

import com.alkemy.icons.icon.dto.IconBasicDTO;
import com.alkemy.icons.icon.dto.IconDTO;
import com.alkemy.icons.icon.entity.Icon;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface IconService {

    List<IconDTO> getAllDetailed() throws Exception;
    List<IconBasicDTO> getAll() throws Exception;
    Icon createIcon(IconDTO iconDTO) throws Exception;
    IconDTO getIconById(Long id) throws NoSuchElementException;
    IconDTO getIconByName(String name) throws NoSuchElementException;
    IconDTO updateIcon(Long id, IconDTO iconDTO) throws NoSuchElementException;
    void deleteIcon(Long id) throws NoSuchElementException;

}
