package com.semana4.peliculas.service;
import java.util.List;
import java.util.Optional;

import com.semana4.peliculas.model.Pelicula;

public interface PeliculaService {
    List<Pelicula> getAllPeliculas();
    Optional<Pelicula> getPeliculaById(Long id);

    // crear, modificar o actualizar y eliminar... para terminar CRUD
    Pelicula createPelicula(Pelicula pelicula);
    Pelicula updatePelicula(Long id, Pelicula pelicula);
    void deletePelicula(Long id);
    
}
