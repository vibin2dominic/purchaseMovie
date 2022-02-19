package com.vibin.purchasemovie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vibin.purchasemovie.model.Movie;
import com.vibin.purchasemovie.service.MovieService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
class MovieControllerTest {


    // private Movie movie = new Movie();


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @BeforeEach
    void setUp() {
//        movie.setTitle("The Simpsons");
//        movie.setDuration("1 hr 15 min");
//        movie.setRating("PG-13");
//        movie.setTimeSlot1("3 pm");
//        movie.setTimeSlot2("Midnight");
//        movie.setNumberOfTickets(10);
    }

    @AfterEach
    void tearDown() {
    }



    @Test
    void showAddMovieForm() throws Exception {

     mockMvc.perform(get("/api/movie"))
             .andExpect(status().isOk());
    }

    @Test
    void confirmation() throws Exception{

        Movie movie = new Movie("Test Movie", "20 min", "PG", "4pm", "5pm" , 2);


        when(movieService.addMovie(movie)).thenReturn(movie);

        this.mockMvc.perform(post("/api/movie"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html; charset=UTF-8"))
                .andDo(print());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}