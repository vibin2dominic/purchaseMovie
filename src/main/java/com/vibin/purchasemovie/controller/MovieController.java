package com.vibin.purchasemovie.controller;

import com.vibin.purchasemovie.model.Movie;
import com.vibin.purchasemovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class MovieController {

    @Autowired
     private MovieService movieService;

    @GetMapping("/movie")
    public String showAddMovieForm(Model model){
        model.addAttribute("movie", new Movie());
        return "addmovie";
    }



    @PostMapping("/movie")
    public String  confirmation(@ModelAttribute Movie movie, Model theModel){

        theModel.addAttribute("movie", movie);
        movieService.addMovie(movie);


        return "confirmation";
    }

    @GetMapping("/movie/show")
    public String findAll(Model model){

        List<Movie> movies = movieService.findAll();

        model.addAttribute("allMovies", movies);

        return "addmovie";
    }
}
