package com.vibin.purchasemovie.repository;

import com.vibin.purchasemovie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
