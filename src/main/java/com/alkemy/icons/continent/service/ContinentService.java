package com.alkemy.icons.continent.service;

import com.alkemy.icons.continent.dto.ContinentDTO;

import java.util.List;
import java.util.NoSuchElementException;

public interface ContinentService {

    List<ContinentDTO> getAll();
    ContinentDTO createContinent(ContinentDTO continentDTO);
    ContinentDTO getContinentById(Long id) throws NoSuchElementException;
    ContinentDTO getContinentByName(String name) throws NoSuchElementException;
    ContinentDTO updateContinent(Long id, ContinentDTO continentDTO) throws NoSuchElementException;
    void deleteContinent(Long id);

}
