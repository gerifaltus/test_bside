package com.bside.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bside.test.dto.AlumnoDto;
import com.bside.test.entity.Alumno;
import com.bside.test.error.AlumnoNotFoundException;
import com.bside.test.repository.AlumnoRepository;
import com.bside.test.service.AlumnoService;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private AlumnoRepository alumnoRepository;
    
    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        super();
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public List<AlumnoDto> getAll() {
        return alumnoRepository.getAll();
    }

    @Override
    public AlumnoDto getById(Integer id) {
        return alumnoRepository.getAlumnoById(id)
                .orElseThrow(() -> new AlumnoNotFoundException("Alumno con id: "+id+" no encontrado"));
    }

    @Override
    public AlumnoDto save(AlumnoDto alumnoDto) {
        
        Alumno alumno = Alumno.builder()
                .nombre(alumnoDto.getNombre())
                .apellidos(alumnoDto.getApellidos())
                .fechaNacimiento(alumnoDto.getFechaNacimiento())
                .activo(true)
                .build();
        
        alumno = alumnoRepository.save(alumno);
        return new AlumnoDto(alumno.getId(), alumno.getNombre(), alumno.getApellidos(), alumno.getFechaNacimiento());
    }

    @Override
    public AlumnoDto update(Integer id, AlumnoDto alumnoDto) {
        Alumno alumno = alumnoRepository.getReferenceById(id);
        
        if (alumno.getId() == null) {
            throw new AlumnoNotFoundException("Alumno con id: "+id+" no encontrado, imposible actualizar");
        }
        
        alumno.setNombre(alumnoDto.getNombre());
        alumno.setApellidos(alumnoDto.getApellidos());
        alumno.setFechaNacimiento(alumnoDto.getFechaNacimiento());
        alumno.setActivo(alumnoDto.isActivo());
        
        alumno = alumnoRepository.save(alumno);
        return new AlumnoDto(alumno.getId(), alumno.getNombre(), alumno.getApellidos(), alumno.getFechaNacimiento(), alumno.isActivo());
        
    }

    @Override
    public void delete(Integer id) {
        Alumno alumno = alumnoRepository.getReferenceById(id);
        
        if (alumno.getId() == null) {
            throw new AlumnoNotFoundException("Alumno con id: "+id+" no encontrado, imposible eliminar");
        }
        
        alumno.setActivo(false);
        
        alumnoRepository.save(alumno);
    }

}
