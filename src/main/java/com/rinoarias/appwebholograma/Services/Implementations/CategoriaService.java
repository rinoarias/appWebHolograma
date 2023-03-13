package com.rinoarias.appwebholograma.Services.Implementations;

import com.rinoarias.appwebholograma.Repositories.CategoriaRepository;
import com.rinoarias.appwebholograma.Services.Interfaces.ICategoriaService;
import com.rinoarias.appwebholograma.Utilities.exceptions.ApiNotFound;
import com.rinoarias.appwebholograma.entities.Categoria;
import com.rinoarias.appwebholograma.entities.Imagen;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService implements ICategoriaService {
    @Autowired
    private final CategoriaRepository categoriaRepository;
    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public List<Categoria> findByName(String name) throws ApiNotFound {
        return categoriaRepository.findByName(name).orElse(null);
    }

    @Override
    public Categoria findById(int id) throws ApiNotFound {
        return categoriaRepository.findCategoriaById(id);
    }

    @Override
    public List<Imagen> findAllImagenesByCategoriaId(Integer categoriaId) {
        List<Imagen> imagenes = categoriaRepository.findAllImagenesByCategoriaId(categoriaId);
        if (imagenes == null){
            return null;
        }
        return imagenes;
    }
}
