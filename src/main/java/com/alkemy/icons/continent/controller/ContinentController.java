package com.alkemy.icons.continent.controller;

import com.alkemy.icons.continent.dto.ContinentBasicDTO;
import com.alkemy.icons.continent.dto.ContinentDTO;
import com.alkemy.icons.continent.dto.ContinentDetailedDTO;
import com.alkemy.icons.continent.entity.Continent;
import com.alkemy.icons.continent.service.ContinentService;
import com.alkemy.icons.general.controller.CustomRestControllerImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/continents")
public class ContinentController extends CustomRestControllerImpl<
        ContinentService,
        Continent,
        ContinentDTO,
        ContinentBasicDTO,
        ContinentDetailedDTO> {

    public ContinentController(ContinentService service) {
        this.service = service;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ContinentBasicDTO>> getAll() {
        return super.getAll();
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ContinentDetailedDTO> getDetailed(@PathVariable Long id, boolean loadRelationship) throws NoSuchElementException {
        return super.getDetailed(id, false);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<ContinentDetailedDTO> create(@Valid @RequestBody ContinentDTO continentDTO, boolean loadRelationship) {
        return super.create(continentDTO, false);
    }

    @PutMapping("/{id}/update")
    @Override
    public ResponseEntity<ContinentDetailedDTO> update(@PathVariable Long id, @Valid @RequestBody ContinentDTO continentDTO, boolean loadRelationship) throws NoSuchElementException {
        return super.update(id, continentDTO, false);
    }

    @DeleteMapping("/{id}/delete")
    @Override
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) throws NoSuchElementException {
        return super.delete(id);
    }
}
