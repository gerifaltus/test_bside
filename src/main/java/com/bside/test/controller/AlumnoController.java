package com.bside.test.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bside.test.dto.AlumnoDto;
import com.bside.test.service.AlumnoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/v1")
public class AlumnoController {

    private AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        super();
        this.alumnoService = alumnoService;
    }

    @GetMapping("/alumnos")
    public List<AlumnoDto> getAll() {
        return alumnoService.getAll();
    }

    @GetMapping("/alumnos/{id}")
    public ResponseEntity<AlumnoDto> getById(@PathVariable @Valid @NotNull @Positive Integer id) {
        return ResponseEntity.ok(alumnoService.getById(id));
    }

    @PostMapping("/alumnos")
    public ResponseEntity<AlumnoDto> save(@Valid @RequestBody AlumnoDto alumnoDto) {
        return new ResponseEntity<>(alumnoService.save(alumnoDto), HttpStatus.CREATED);
    }

    @PutMapping("/alumnos/{id}")
    public ResponseEntity<AlumnoDto> update(
            @Valid @PathVariable @NotNull @Positive Integer id, 
            @Valid @RequestBody AlumnoDto alumnoDto) {
        return ResponseEntity.ok(alumnoService.update(id, alumnoDto));
    }

    @DeleteMapping("/alumnos/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable @NotNull @Positive Integer id) {
        alumnoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
