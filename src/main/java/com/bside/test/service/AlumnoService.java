package com.bside.test.service;

import java.util.List;

import com.bside.test.dto.AlumnoDto;

public interface AlumnoService {

    List<AlumnoDto> getAll();
    AlumnoDto getById(Integer id);
    AlumnoDto save(AlumnoDto alumno);
    AlumnoDto update(Integer id, AlumnoDto alumno);
    void delete(Integer id);
    
}
