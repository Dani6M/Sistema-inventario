package com.castores.inventario.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario") 
    private Integer idUsuario;

    @Column(nullable = false, length = 100,name = "nombre")
    private String nombre;

    @Column(nullable = false, length = 50, unique = true,name = "correo")
    private String correo;

    @Column(nullable = false,name = "contrasena")
    private String contrasena;

    @Column(nullable = false,name = "idRol")
    private Integer idRol;

    @Column(nullable = false,name = "estatus")
    private Integer estatus;
}
