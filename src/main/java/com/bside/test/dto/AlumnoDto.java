package com.bside.test.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDto {
    
    @Positive(message="El id '${validatedValue}' no es un valor válido")
    private Integer id; 
    
    @NotBlank(message="El nombre: '${validatedValue}' no es un valor válido")
    private String nombre; 
    
    @NotBlank(message="El apellido: '${validatedValue}' no es un valor válido")
    private String apellidos;
    
    @Past(message="La fecha de Nacimiento: '${validatedValue}', debe ser una fecha válida")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fechaNacimiento;
    
    private boolean activo;

    public AlumnoDto(String nombre, String apellidos, LocalDate fechaNacimiento) {
        super();
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
    }

    public AlumnoDto(Integer id, String nombre, String apellidos, LocalDate fechaNacimiento) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    
}
