package com.alkemy.icons.continent.service;

import com.alkemy.icons.continent.dto.ContinentDTO;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface ContinentService {

    List<ContinentDTO> getAll() throws Exception;
    ContinentDTO createContinent(ContinentDTO continentDTO) throws Exception;
    ContinentDTO getContinentById(Long id) throws NoSuchElementException;
    ContinentDTO getContinentByName(String name) throws NoSuchElementException;
    ContinentDTO updateContinent(Long id, ContinentDTO continentDTO) throws NoSuchElementException;
    void deleteContinent(Long id);

}
