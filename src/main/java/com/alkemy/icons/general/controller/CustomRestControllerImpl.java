package com.alkemy.icons.general.controller;

import com.alkemy.icons.general.mapper.CustomMapper;
import com.alkemy.icons.general.service.CustomServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.NoSuchElementException;

public class CustomRestControllerImpl<
        Service extends CustomServiceImpl<
                ? extends CustomMapper,
                ? extends JpaRepository,
                Entity,
                DTO,
                BasicDTO,
                DetailedDTO>,
        Entity,
        DTO,
        BasicDTO,
        DetailedDTO>
        implements CustomRestController<
        DTO,
        BasicDTO,
        DetailedDTO> {

    protected Service service;

    @Override
    public ResponseEntity<List<BasicDTO>> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<DetailedDTO> getDetailed(Long id, boolean loadRelationship) throws NoSuchElementException {
        try {
            return new ResponseEntity<>(service.getDetailed(id, loadRelationship), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<DetailedDTO> create(DTO dto, boolean loadRelationship) {
//        try {
            return new ResponseEntity<>(service.create(dto, loadRelationship), HttpStatus.CREATED);
//        } catch(Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
    }

    @Override
    public ResponseEntity<DetailedDTO> update(Long id, DTO dto, boolean loadRelationship) throws NoSuchElementException {
        try {
            return new ResponseEntity<>(service.update(id, dto, loadRelationship), HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> delete(Long id) throws NoSuchElementException {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
