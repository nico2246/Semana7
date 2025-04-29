package com.semana4.peliculas.controller;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.semana4.peliculas.model.Pelicula;

import com.semana4.peliculas.service.PeliculaServiceImpl;

@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    
    @MockBean
    private PeliculaServiceImpl peliculaServicioMock;

    @Test
    public void obtenerTodosTest() throws Exception {
        //Arrange
        //Creacion de peliculas
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setAno(2000);
        pelicula1.setDirector("Nicolas Escobar");
        pelicula1.setGenero("Accion");
        pelicula1.setSinopsis("Sinopsis de pruebas");
        pelicula1.setTitulo("Mision imposible");

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setAno(2002);
        pelicula2.setDirector("Barbara Lillo");
        pelicula2.setGenero("Infantil");
        pelicula2.setSinopsis("Sinopsis de pruebas2");
        pelicula2.setTitulo("Blanca Nieves");

        
        List<Pelicula> peliculas = Arrays.asList(pelicula1, pelicula2);
        when(peliculaServicioMock.getAllPeliculas()).thenReturn(peliculas);

        //Act & Assert

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[0].titulo", Matchers.is("Mision imposible")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[1].titulo", Matchers.is("Blanca Nieves")));
                
    }

    //Otras  pruebas...
    
}
