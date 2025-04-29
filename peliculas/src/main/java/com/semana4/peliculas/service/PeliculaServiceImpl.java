package com.semana4.peliculas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.semana4.peliculas.model.Pelicula;
import com.semana4.peliculas.repository.PeliculaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService{
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public List<Pelicula> getAllPeliculas(){
        return peliculaRepository.findAll();
    }

    @Override
    public Optional<Pelicula> getPeliculaById(Long id){
        return peliculaRepository.findById(id);
    }
    

    // continuacion nueva logica CRUD

    @Override
    public Pelicula createPelicula(Pelicula pelicula){
        return peliculaRepository.save(pelicula);
    }

    @Override
    public Pelicula updatePelicula(Long id, Pelicula pelicula){
        if(peliculaRepository.existsById(id)){
            pelicula.setId(id);
            return peliculaRepository.save(pelicula);
        } else {
            return null;
        }
    }

    @Override
    public void deletePelicula(Long id){
        peliculaRepository.deleteById(id);
    }

    
}
