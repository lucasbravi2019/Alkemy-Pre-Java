package com.alkemy.icons.icon.controller;

import com.alkemy.icons.general.controller.CustomRestControllerImpl;
import com.alkemy.icons.icon.dto.IconBasicDTO;
import com.alkemy.icons.icon.dto.IconDTO;
import com.alkemy.icons.icon.dto.IconDetailedDTO;
import com.alkemy.icons.icon.entity.Icon;
import com.alkemy.icons.icon.service.IconService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("/icons")
public class IconController extends CustomRestControllerImpl<
        IconService,
        Icon,
        IconDTO,
        IconBasicDTO,
        IconDetailedDTO> {

    public IconController(IconService service) {
        this.service = service;
    }

    @GetMapping("/all")
    @Override
    public ResponseEntity<List<IconBasicDTO>> getAll() {
        return super.getAll();
    }

    @GetMapping
    public ResponseEntity<List<IconBasicDTO>> getByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) Set<Long> cities,
            @RequestParam(required = false, defaultValue = "ASC") String order
        ) {
        try {
            return new ResponseEntity<>(service.getByFilters(name, date, cities, order), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<IconDetailedDTO> getDetailed(@PathVariable Long id, boolean loadRelationship) throws NoSuchElementException {
        return super.getDetailed(id, true);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<IconDetailedDTO> create(@Valid @RequestBody IconDTO iconDTO, boolean loadRelationship) {
        return super.create(iconDTO, true);
    }

    @PutMapping("/{id}/update")
    @Override
    public ResponseEntity<IconDetailedDTO> update(@PathVariable Long id, @Valid @RequestBody IconDTO iconDTO, boolean loadRelationship) throws NoSuchElementException {
        return super.update(id, iconDTO, true);
    }

    @DeleteMapping("/{id}/delete")
    @Override
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) throws NoSuchElementException {
        return super.delete(id);
    }
}
