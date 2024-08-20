package com.bside.test.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bside.test.dto.AlumnoDto;
import com.bside.test.service.AlumnoService;

@WebMvcTest(AlumnoController.class)
class AlumnoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlumnoService alumnoService;

    @Test
    void getAllTest() throws Exception {
        AlumnoDto alumno1 = new AlumnoDto("Juan", "Pérez Jolote", LocalDate.now().minusYears(20));
        AlumnoDto alumno2 = new AlumnoDto("Ricardo", "Corazón de León", LocalDate.now().minusYears(21));

        when(alumnoService.getAll()).thenReturn(List.of(alumno1, alumno2));

        mockMvc.perform(get("/api/v1/alumnos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("Juan")))
                .andExpect(jsonPath("$[1].nombre", is("Ricardo")));

        verify(alumnoService, times(1)).getAll();
    }

    @Test
    void getByIdTest() throws Exception {
        AlumnoDto alumnoDto = new AlumnoDto("Juan", "Pérez Jolote", LocalDate.now().minusYears(20));

        when(alumnoService.getById(1)).thenReturn(alumnoDto);

        mockMvc.perform(get("/api/v1/alumnos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Juan")));

        verify(alumnoService, times(1)).getById(1);
    }

    @Test
    void saveTest() throws Exception {
        AlumnoDto alumnoDto = new AlumnoDto(1, "Juan", "Pérez Jolote", LocalDate.now().minusYears(20));

        when(alumnoService.save(any(AlumnoDto.class))).thenReturn(alumnoDto);

        mockMvc.perform(post("/api/v1/alumnos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         {
                             "nombre": "Juan",
                             "apellidos": "Pérez Jolote",
                             "fechaNacimiento": "12-05-2024"
                         }
                         """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre", is("Juan")));

        verify(alumnoService, times(1)).save(any(AlumnoDto.class));
    }
    
}
