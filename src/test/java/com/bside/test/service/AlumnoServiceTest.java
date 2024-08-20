package com.bside.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bside.test.dto.AlumnoDto;
import com.bside.test.entity.Alumno;
import com.bside.test.repository.AlumnoRepository;
import com.bside.test.service.impl.AlumnoServiceImpl;

@ExtendWith(MockitoExtension.class)
class AlumnoServiceTest {

    @Mock
    private AlumnoRepository alumnoRepository;

    @InjectMocks
    private AlumnoService alumnoService = new AlumnoServiceImpl(alumnoRepository);

    @BeforeEach
    void config() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deberiaDevolverAlumnoId() {
        AlumnoDto alumno = new AlumnoDto();
        alumno.setId(1);
        alumno.setNombre("Juan");
        alumno.setApellidos("Pérez Jolote");
        when(alumnoRepository.getAlumnoById(anyInt())).thenReturn(Optional.of(alumno));

        AlumnoDto alumnoDto = alumnoService.getById(1);

        assertNotNull(alumnoDto);
        assertEquals("Juan", alumnoDto.getNombre());
    }

    @Test
    void saveAlumno() {
        AlumnoDto alumnoDto = new AlumnoDto("Juan", "Pérez Jolote", LocalDate.now().minusYears(20));
        Alumno alumno = new Alumno();
        alumno.setNombre("Juan");
        alumno.setApellidos("Pérez Jolote");
        alumno.setFechaNacimiento(LocalDate.now().minusYears(20));
        
        when(alumnoRepository.save(any(Alumno.class))).thenReturn(alumno);

        AlumnoDto alumnoSave = alumnoService.save(alumnoDto);

        assertNotNull(alumnoDto);
        assertEquals("Juan", alumnoSave.getNombre());
        verify(alumnoRepository, times(1)).save(any(Alumno.class));
    }
    
}
