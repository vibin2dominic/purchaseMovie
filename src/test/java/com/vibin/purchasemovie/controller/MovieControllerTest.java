package com.vibin.purchasemovie.controller;

import com.vibin.purchasemovie.model.Movie;
import com.vibin.purchasemovie.service.MovieService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.is;

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
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/api/movie/show"))
                .andDo(print());

    }


    @Test
    void findAll() throws Exception{
        Movie movie = new Movie("Test Movie", "20 min", "PG", "4pm", "5pm" , 2);
        Movie movie2 = new Movie("Test Movie2", "20 min", "PG", "4pm", "5pm" , 2);

        List<Movie> list = new ArrayList<>();
        list.add(movie);
        list.add(movie2);

        when(movieService.findAll()).thenReturn(list);

        this.mockMvc.perform(get("/api/movie/show"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("allMovies"))
                .andExpect(model().attribute("allMovies", is(list)));

    }


}