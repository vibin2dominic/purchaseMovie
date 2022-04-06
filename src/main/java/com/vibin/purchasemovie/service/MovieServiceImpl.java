package com.vibin.purchasemovie.service;

import com.vibin.purchasemovie.events.*;
import com.vibin.purchasemovie.model.Movie;
import com.vibin.purchasemovie.repository.MovieRepository;
import com.vibin.purchasemovie.service.kafka.KafkaProducerService;
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


    @Autowired
    KafkaProducerService kafkaProducerService;


    @Override
    public Movie addMovie(Movie movie) {

        Movie savedMovie = movieRepository.save(movie);

        logger.info("Added movie: " + movie.getTitle() + " Tickets: " + movie.getNumberOfTickets());

        // sending to kafa
        MovieAdded movieAdded = new MovieAdded();

        movieAdded.setMovieId(savedMovie.getId());
        movieAdded.setMovieTitle(savedMovie.getTitle());
        movieAdded.setMovieDuration(savedMovie.getDuration());
        movieAdded.setMovieRating(savedMovie.getRating());
        movieAdded.setMovieTimeSlot1(savedMovie.getTimeSlot1());
        movieAdded.setMovieTimeSlot2(savedMovie.getTimeSlot2());
        movieAdded.setMovieNumberOfTickets(savedMovie.getNumberOfTickets());


        kafkaProducerService.publishMovieAdded(movieAdded);

        return  savedMovie;
    }

    @Override
    public Movie update(Movie movie) {

        Object object = checkUpdateType(movie);

        // If title is updated -> send the titleCorrected to kafka
        if(object instanceof TitleCorrected ){
            kafkaProducerService.publishTitleCorrected((TitleCorrected) object);
            logger.info("Sent object to Title Corrected");
        }

        if(object instanceof ShowtimesUpdated){
            logger.info("--------------------------------------");
            logger.info( "(ShowtimesUpdated) object) id --> " + ((ShowtimesUpdated) object).getMovieId());
            logger.info( "(ShowtimesUpdated) object) timeslot2 --> " + ((ShowtimesUpdated) object).getMovieTimeSlot2());
            logger.info( "(ShowtimesUpdated) object) timeslot1 --> " + ((ShowtimesUpdated) object).getMovieTimeSlot1());

            logger.info("--------------------------------------");

            kafkaProducerService.publishShowtimesUpdated((ShowtimesUpdated) object);

            logger.info("Sent object to ShowTimesUpdated");
        }

        Movie savedMovie = movieRepository.save(movie);

        logger.info("update() --> MovieServiceImpl called for " + savedMovie.getId());


        return null;
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

        // create an isntance of MovieRemoved Object (event)
        MovieRemoved movieRemoved = new MovieRemoved();
        movieRemoved.setMovieId(id);

        // send the event to kafka
        kafkaProducerService.publishMovieRemoved(movieRemoved);

        // delete the movie from database
        movieRepository.deleteById(id);

    }

    public void updateAvailableTickets(TicketSold ticketSold){
        logger.info("Inside updateAvailbleTickets() in MovieServiceImpl");
        int ticketsSold = ticketSold.getTicketsSold();
        int id = ticketSold.getId();

        Movie movie = movieRepository.findById(id).get();

        int availableTickets = movie.getNumberOfTickets();

        movie.setNumberOfTickets(availableTickets - ticketsSold);

        movieRepository.save(movie);
        logger.info("updateAvailbleTickets() --> Updated available tickets in the repository!!!");
    }

    @Override
    public  Object checkUpdateType(Movie movie) {

        Movie savedMovie = movieRepository.findById(movie.getId()).get();

        if(!savedMovie.getTitle().equals(movie.getTitle())){
            logger.info("Creating and sending TitleCorrected Object back to caller...");
            TitleCorrected titleCorrected = new TitleCorrected();
            titleCorrected.setMovieTitle(movie.getTitle());
            titleCorrected.setId(movie.getId());

            return titleCorrected;
        }

        if((!savedMovie.getTimeSlot1().equals(movie.getTimeSlot1())) || (!savedMovie.getTimeSlot2().equals(movie.getTimeSlot2()))){
            ShowtimesUpdated showtimesUpdated = new ShowtimesUpdated();
            showtimesUpdated.setMovieId(movie.getId());
            showtimesUpdated.setMovieTimeSlot1(movie.getTimeSlot1());
            showtimesUpdated.setMovieTimeSlot2(movie.getTimeSlot2());

            return showtimesUpdated;
        }
        return null;
    }
}
