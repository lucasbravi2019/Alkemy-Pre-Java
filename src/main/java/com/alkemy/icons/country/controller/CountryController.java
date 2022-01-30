package com.alkemy.icons.country.controller;

import com.alkemy.icons.country.dto.CountryBasicDTO;
import com.alkemy.icons.country.dto.CountryDTO;
import com.alkemy.icons.country.dto.CountryDetailedDTO;
import com.alkemy.icons.country.entity.Country;
import com.alkemy.icons.country.service.CountryService;
import com.alkemy.icons.general.controller.CustomRestControllerImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/cities")
public class CountryController extends CustomRestControllerImpl<
        CountryService,
        Country,
        CountryDTO,
        CountryBasicDTO,
        CountryDetailedDTO> {

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping("/all")
    @Override
    public ResponseEntity<List<CountryBasicDTO>> getAll() {
        return super.getAll();
    }

    @GetMapping
    public ResponseEntity<List<CountryBasicDTO>> getByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long idContinent,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        try {
            return new ResponseEntity<>(service.getByFilters(name, idContinent, order), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<CountryDetailedDTO> getDetailed(@PathVariable Long id, boolean loadRelationship) throws NoSuchElementException {
        return super.getDetailed(id, true);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<CountryDetailedDTO> create(@Valid @RequestBody CountryDTO countryDTO, boolean loadRelationship) {
        return super.create(countryDTO, true);
    }

    @PutMapping("/{id}/update")
    @Override
    public ResponseEntity<CountryDetailedDTO> update(@PathVariable Long id, @Valid @RequestBody CountryDTO countryDTO, boolean loadRelationship) throws NoSuchElementException {
        return super.update(id, countryDTO, true);
    }

    @DeleteMapping("/{id}/delete")
    @Override
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) throws NoSuchElementException {
        return super.delete(id);
    }
}



