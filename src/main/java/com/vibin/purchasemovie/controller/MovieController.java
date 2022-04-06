package com.vibin.purchasemovie.controller;

import com.vibin.purchasemovie.model.Movie;
import com.vibin.purchasemovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        return "redirect:/api/movie/show";
    }


    @PostMapping("/movie/edit")
    public String showEditMovieForm(@ModelAttribute Movie movie, Model model){

        model.addAttribute("movie", movie);

        movieService.update(movie);

        return "redirect:/api/movie/show";
    }



    @GetMapping("/movie/show")
    public String findAll(Model model){

        List<Movie> movies = movieService.findAll();

        model.addAttribute("allMovies", movies);

        return "movies";
    }

    @GetMapping("/movie/update/{id}")
    public String update(@PathVariable (value = "id") int id, Model model){

        Movie movie = movieService.findById(id); // get the movie from movie service

        model.addAttribute("movie", movie);

        return "updatemovie";
    }

    @GetMapping("/movie/delete/{id}")
    public String deleteMovie(@PathVariable (value="id") int id){

        movieService.deleteById(id);

        return "redirect:/api/movie/show";
    }
}
