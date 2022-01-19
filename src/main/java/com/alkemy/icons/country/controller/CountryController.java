package com.alkemy.icons.country.controller;

import com.alkemy.icons.country.dto.CountryDTO;
import com.alkemy.icons.country.dto.CountryDetailedDTO;
import com.alkemy.icons.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/cities")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long idContinent,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        try {
            return new ResponseEntity<>(countryService.getAllByFilters(name, idContinent, order), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/detailed")
    public ResponseEntity<?> getAllDetailed() {
        try {
            return new ResponseEntity<>(countryService.getAllDetailed(), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCountry(@Valid @RequestBody CountryDTO countryDTO) {
        try {
            return new ResponseEntity<>(countryService.createCountry(countryDTO), HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDetailedDTO> getCountry(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(countryService.getCountryById(id), HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateCountry(@PathVariable Long id, @Valid @RequestBody CountryDTO countryDTO) {
        try {
            return new ResponseEntity<>(countryService.updateCountry(id, countryDTO), HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<HttpStatus> deleteCountry(@PathVariable Long id) {
        try {
            countryService.deleteCountry(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
