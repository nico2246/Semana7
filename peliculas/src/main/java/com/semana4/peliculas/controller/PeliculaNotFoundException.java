package com.semana4.peliculas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PeliculaNotFoundException extends RuntimeException {

    public PeliculaNotFoundException(String message) {
        super(message);
    }
    
}
