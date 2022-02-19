package com.vibin.purchasemovie.service;

import com.vibin.purchasemovie.model.Movie;

import java.util.List;


public interface MovieService {

     Movie addMovie(Movie movie);

     List<Movie> findAll();


}
