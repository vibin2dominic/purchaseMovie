package com.vibin.purchasemovie.service;

import com.vibin.purchasemovie.events.TicketSold;
import com.vibin.purchasemovie.model.Movie;

import java.util.List;


public interface MovieService {

     // saves movie
     Movie addMovie(Movie movie);

     // performs all updates for movies
     Movie update(Movie movie);

     void updateAvailableTickets(TicketSold ticketSold);

     List<Movie> findAll();


     // method used to update
     Movie findById(int id);

     // method to delete
     void deleteById(int id);


     Object checkUpdateType(Movie movie);

}
