package com.vibin.purchasemovie.service;

import com.vibin.purchasemovie.model.Movie;

import java.util.List;


public interface MovieService {

     //saves movie
     Movie addMovie(Movie movie);

     List<Movie> findAll();



//     Movie getEmployeeById(long id);
//     void deleteMovie(long id);


}
