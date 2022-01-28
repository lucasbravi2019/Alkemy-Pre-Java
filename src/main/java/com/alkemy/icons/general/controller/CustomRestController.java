package com.alkemy.icons.general.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.NoSuchElementException;

public interface CustomRestController<
        DTO,
        BasicDTO,
        DetailedDTO> {

    ResponseEntity<List<BasicDTO>> getAll();
    ResponseEntity<DetailedDTO> getDetailed(Long id, boolean loadRelationship) throws NoSuchElementException;
    ResponseEntity<DetailedDTO> create(DTO dto, boolean loadRelationship);
    ResponseEntity<DetailedDTO> update(Long id, DTO dto, boolean loadRelationship) throws  NoSuchElementException;
    ResponseEntity<HttpStatus> delete(Long id) throws NoSuchElementException;

}
