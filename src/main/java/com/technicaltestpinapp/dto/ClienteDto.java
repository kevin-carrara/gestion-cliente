package com.technicaltestpinapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;



import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ClienteDto {


    @NotBlank(message = "el nombre no puede ser null")
    private String nombre;
    @NotNull(message = "el apellido no puede ser null")
    private String apellido;
    @NotNull(message = "la fecha de nacimiento no puede ser null")
    private LocalDate fechaNacimiento;
    private String genero;
    @NotNull(message = "la altura no puede ser null")
    private Double altura;
    @NotNull(message = "el peso no puede ser null")
    private Double peso;

    private boolean fumador;
}
