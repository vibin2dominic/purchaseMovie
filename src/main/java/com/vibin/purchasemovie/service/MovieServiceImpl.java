package com.vibin.purchasemovie.service;

import com.vibin.purchasemovie.model.Movie;
import com.vibin.purchasemovie.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    private MovieRepository movieRepository;


    @Override
    public Movie addMovie(Movie movie) {

        Movie savedMovie = movieRepository.save(movie);

        logger.info("Added movie: " + movie.getTitle() + " Tickets: " + movie.getNumberOfTickets());

        return  savedMovie;
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> movies = movieRepository.findAll();

        return movies;
    }

    @Override
    public Movie findById(int id) {
        Optional<Movie> optional = movieRepository.findById(id);


        Movie movie = null;
        if(optional.isPresent()){
            movie = optional.get();
        } else{
            throw new RuntimeException("Movie not found for id: " + id);
        }
        return movie;
    }

    @Override
    public void deleteById(int id) {

        movieRepository.deleteById(id);
    }
}
