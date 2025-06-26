package com.technicaltestpinapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "Clientes")
public class Cliente {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    private String nombre;
    private String apellido;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    private Integer edad;
    private String genero;
    private boolean fumador;
    private double peso;
    private double altura;

}
