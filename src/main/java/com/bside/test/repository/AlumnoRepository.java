package com.bside.test.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bside.test.dto.AlumnoDto;
import com.bside.test.entity.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer>{

    @Query(""" 
            SELECT new com.bside.test.dto.AlumnoDto(id, nombre, apellidos, fechaNacimiento, activo) 
              FROM Alumno a WHERE a.activo = true
              """)
    List<AlumnoDto> getAll();
    
    @Query(""" 
            SELECT new com.bside.test.dto.AlumnoDto(nombre, apellidos, fechaNacimiento) 
              FROM Alumno a WHERE a.activo = true AND a.id = :idAlumno 
              """)
    Optional<AlumnoDto> getAlumnoById(@Param("idAlumno") Integer idAlumno);
    
}
