package com.semana4.peliculas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.semana4.peliculas.model.Pelicula;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaRepositoryTest {
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Test
    public void guardarPeliculaTest(){
        //Arrange
        Pelicula pelicula = new Pelicula();
        pelicula.setAno(2000);
        pelicula.setDirector("Nicolas Escobar");
        pelicula.setGenero("Accion");
        pelicula.setSinopsis("Sinopsis de pruebas");
        pelicula.setTitulo("Mision imposible");
       

        //Act
        Pelicula resultado = peliculaRepository.save(pelicula);

        //Assert
        assertNotNull(resultado.getId());
        assertEquals("Mision imposible", resultado.getTitulo());
    }
    
    //  otras pruebas...
}
