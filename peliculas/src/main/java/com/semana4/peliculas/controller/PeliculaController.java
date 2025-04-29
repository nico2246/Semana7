package com.semana4.peliculas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.semana4.peliculas.model.Pelicula;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



import com.semana4.peliculas.service.PeliculaService;

@RestController
@RequestMapping("/peliculas")
@CrossOrigin(origins = "*")
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public CollectionModel<EntityModel<Pelicula>> getAllPeliculas(){
        List<Pelicula> peliculas = peliculaService.getAllPeliculas();

        List<EntityModel<Pelicula>> peliculaResources = peliculas.stream()
        .map(pelicula -> EntityModel.of(pelicula,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(pelicula.getId())).withSelfRel()
                ))
        .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas());
        CollectionModel<EntityModel<Pelicula>> resources = CollectionModel.of(peliculaResources, linkTo.withRel("peliculas"));

        return resources;

    }

    @GetMapping("/{id}")
    public EntityModel<Pelicula> getPeliculaById(@PathVariable Long id){
        Optional<Pelicula> pelicula = peliculaService.getPeliculaById(id);
        //Verifica si existe la pelicula
        if (pelicula.isPresent()) {
            return EntityModel.of(pelicula.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all-peliculas"));
        } else {
            throw new PeliculaNotFoundException("Pelicula not found" + id);
        }   

    }

    //nuevos controller para el CRUD


    @PostMapping
    public EntityModel<Pelicula> createPelicula(@RequestBody Pelicula pelicula){
        Pelicula createdPelicula = peliculaService.createPelicula(pelicula);
        return EntityModel.of(createdPelicula,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(createdPelicula.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all-peliculas"));
    }

    @PutMapping("/{id}")
    public EntityModel<Pelicula> updatePelicula(@PathVariable Long id, @RequestBody Pelicula pelicula){
        Pelicula updatedPelicula = peliculaService.updatePelicula(id, pelicula);
        return EntityModel.of(updatedPelicula,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all-peliculas"));
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable Long id){
        peliculaService.deletePelicula(id);
    }
    
}
