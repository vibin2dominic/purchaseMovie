package com.vibin.purchasemovie.service;

import com.vibin.purchasemovie.model.Movie;

import java.util.List;


public interface MovieService {

     // saves movie
     Movie addMovie(Movie movie);

     List<Movie> findAll();


     // method used to update
     Movie findById(int id);


     // method to delete


}
