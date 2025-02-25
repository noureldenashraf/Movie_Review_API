package nour.movie_review_api.controllers;

import jakarta.validation.Valid;
import nour.movie_review_api.entities.Movie;
import nour.movie_review_api.services.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController("/movie")
public class MoviesController {
    private MoviesService moviesService;

    @Autowired
    public MoviesController(MoviesService ms) {
        moviesService = ms;
    }

    @GetMapping("/movie")
    public ResponseEntity<?> getAllMovies() {
        return moviesService.getAllMovies();
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<?> getMovie(@PathVariable Optional<Integer> movieId) {
        return moviesService.getMovie(movieId);
    }

    @GetMapping("/movie/popular")
    public ResponseEntity<?> getHighestRatedMovies() {
        return moviesService.getHighestRatedMovies();
    }

    @GetMapping("/movie/genre")
    public ResponseEntity<?> getAllGenres() {
        return moviesService.getAllGenres();
    }

    @GetMapping("/movie/genre/{genreName}")
    public ResponseEntity<?> getAllMoviesWithGenre(@PathVariable("genreName") Optional<String> genreName){
        return moviesService.getAllMoviesWithGenre(genreName);
    }

    @PostMapping("/movie")
    public ResponseEntity<?> addMovie(@RequestBody Optional<Movie> newMovie) {
        return moviesService.addMovie(newMovie);
    }


    @PutMapping("/movie")
    public ResponseEntity<?> updateMovie(@RequestBody @Valid Movie updatemovie) {
        return moviesService.updateMovie(updatemovie);
    }


    @DeleteMapping("/movie/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable Optional<Integer> movieId) {
        return moviesService.deleteMovie(movieId);
    }
}
