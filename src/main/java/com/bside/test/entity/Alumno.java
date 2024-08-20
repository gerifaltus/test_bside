package com.bside.test.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "alumno")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alumno implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 2961677104100746832L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private LocalDateTime fechaRegistro;
    private String usuarioRegistra;
    private LocalDateTime fechaActualiza;
    private String usuarioActualiza;
    private boolean activo;
    
    @PrePersist
    public void onPrePersist() {
        this.setUsuarioRegistra("sistemas@server.com");
        this.setUsuarioActualiza("sistemas@server.com");
        this.setFechaRegistro(LocalDateTime.now());
        this.setFechaActualiza(this.getFechaRegistro());
    }
      
    @PreUpdate
    public void onPreUpdate() {
        this.setUsuarioActualiza("sistemas_update@server.com");
        this.setFechaActualiza(LocalDateTime.now());
    }
}
