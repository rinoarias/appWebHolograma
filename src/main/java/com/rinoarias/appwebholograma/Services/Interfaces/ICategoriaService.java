package com.rinoarias.appwebholograma.Services.Interfaces;

import com.rinoarias.appwebholograma.Utilities.exceptions.ApiNotFound;
import com.rinoarias.appwebholograma.entities.Categoria;
import com.rinoarias.appwebholograma.entities.Imagen;

import java.util.List;

public interface ICategoriaService {
    List<Categoria> findAll();
    List<Categoria> findByName(String name) throws ApiNotFound;
    Categoria findById(int id) throws ApiNotFound;

    List<Imagen> findAllImagenesByCategoriaId(Integer categoriaId);

}
