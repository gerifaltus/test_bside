package com.bside.test.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.bside.test.entity.Alumno;

@DataJpaTest
@ActiveProfiles("test")
class AlumnoRepositoryTest {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @BeforeEach
    void config() {
        Alumno alumno = new Alumno();
        alumno.setNombre("Juan");
        alumno.setApellidos("Pérez Jolote");
        alumno.setFechaNacimiento(LocalDate.now().minusYears(20));
        alumnoRepository.save(alumno);
    }

    @Test
    void deberiaEncontrarAlumno() {
        Optional<Alumno> alumnoOptional = alumnoRepository.findById(1);
        assertTrue(alumnoOptional.isPresent());
        assertEquals("Juan", alumnoOptional.get().getNombre());
    }

    @Test
    void shouldReturnEmptyForNonExistentStudent() {
        Optional<Alumno> studentOptional = alumnoRepository.findById(10);
        assertTrue(studentOptional.isEmpty());
    }
    
}
