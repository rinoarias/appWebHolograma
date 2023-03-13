package com.rinoarias.appwebholograma.Repositories;

import com.rinoarias.appwebholograma.entities.Categoria;
import com.rinoarias.appwebholograma.entities.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Query(value = "SELECT c FROM categoria c WHERE c.nombreCategoria = :name", nativeQuery = true)
    Optional<List<Categoria>> findByName(@Param("name") String name);
    @Query(value = "SELECT i FROM imagenes i WHERE i.categoria.id = :id", nativeQuery = true)
    Categoria findCategoriaById(@Param("id") int id);

    @Query(value = "SELECT i FROM imagenes i WHERE i.categoria.id = :id")
    List<Imagen> findAllImagenesByCategoriaId(@Param("id") int id);
}
