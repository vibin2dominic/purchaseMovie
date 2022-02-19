package com.vibin.purchasemovie.service;

import com.vibin.purchasemovie.model.Movie;
import com.vibin.purchasemovie.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class MovieServiceImplTest {

    @Autowired
    MovieService movieService;

    @MockBean
    private MovieRepository movieRepository;



    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addMovie() {
        Movie movie = new Movie("Test Movie", "20 min", "PG", "4pm", "5pm" , 2);
        Movie stubbedResponse = new Movie("Test Movie", "20 min", "PG", "4pm", "5pm" , 2);

        when(movieService.addMovie(movie)).thenReturn(stubbedResponse);

        Movie returnedMovie= movieService.addMovie(movie);

        assertEquals("Test Movie", returnedMovie.getTitle());
        assertTrue(movie.equals(returnedMovie));


    }
}