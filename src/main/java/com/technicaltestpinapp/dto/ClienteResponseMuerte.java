package com.technicaltestpinapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ClienteResponseMuerte {

    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String genero;
    private double altura;
    private double peso;
    private boolean fumador;
    private Integer edad;
    private LocalDate fechaProbableMuerte;
}
