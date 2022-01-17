package com.alkemy.icons.icon.controller;

import com.alkemy.icons.icon.dto.IconDTO;
import com.alkemy.icons.icon.entity.Icon;
import com.alkemy.icons.icon.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/icons")
public class IconController {

    @Autowired
    private IconService iconService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(iconService.getAll(), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/detailed")
    public ResponseEntity<?> getAllDetailed() {
        try {
            return new ResponseEntity<>(iconService.getAllDetailed(), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Icon> createIcon(@Valid @RequestBody IconDTO iconDTO) {
        try {
            return new ResponseEntity<>(iconService.createIcon(iconDTO), HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<IconDTO> getIcon(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(iconService.getIconById(id), HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<IconDTO> updateIcon(@PathVariable Long id, @Valid @RequestBody IconDTO iconDTO) {
        try {
            return new ResponseEntity<>(iconService.updateIcon(id, iconDTO), HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<HttpStatus> deleteIcon(@PathVariable Long id) {
        try {
            iconService.deleteIcon(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
