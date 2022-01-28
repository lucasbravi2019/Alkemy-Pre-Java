package com.alkemy.icons.general.service;

import com.alkemy.icons.general.mapper.CustomMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.NoSuchElementException;

public interface CustomService<
        T extends CustomMapper,
        S extends JpaRepository,
        DTO,
        BasicDTO,
        DetailedDTO> {

    List<BasicDTO> getAll();
    DetailedDTO getDetailed(Long id, boolean loadRelationship) throws NoSuchElementException;
    DetailedDTO create(DTO dto, boolean loadRelationship);
    DetailedDTO update(Long id, DTO dto, boolean loadRelationship) throws NoSuchElementException;
    void delete(Long id) throws NoSuchElementException;

}
