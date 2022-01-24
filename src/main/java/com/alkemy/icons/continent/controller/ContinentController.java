package com.alkemy.icons.continent.controller;

import com.alkemy.icons.continent.dto.ContinentDTO;
import com.alkemy.icons.continent.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/continents")
public class ContinentController {

    @Autowired
    private ContinentService continentService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(continentService.getAll(), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ContinentDTO> createContinent(@Valid @RequestBody ContinentDTO continentDTO) {
        try {
            return new ResponseEntity<>(continentService.createContinent(continentDTO), HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContinentDTO> getContinent(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(continentService.getContinentById(id), HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ContinentDTO> updateContinent(@PathVariable Long id, @Valid @RequestBody ContinentDTO continentDTO) {
        try {
            return new ResponseEntity<>(continentService.updateContinent(id, continentDTO), HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity deleteContinent(@PathVariable Long id) {
        try {
            continentService.deleteContinent(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
