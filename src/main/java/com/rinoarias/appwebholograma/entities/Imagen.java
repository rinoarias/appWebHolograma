package com.rinoarias.appwebholograma.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity(name = "imagenes")
@Getter
@Setter
public class Imagen implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nombre_imagen")
    private String nombreImagen;
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    @Column(name = "activo")
    private boolean activo;

}
