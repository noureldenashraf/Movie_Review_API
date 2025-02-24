package nour.movie_review_api.controllers;

import jakarta.validation.Valid;
import nour.movie_review_api.entities.Movie;
import nour.movie_review_api.Dto.MovieResponseDTO;
import nour.movie_review_api.entities.Review;
import nour.movie_review_api.repository.MoviesRepository;
import nour.movie_review_api.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/movie")
public class MoviesController {
    private MoviesRepository moviesDao;
    private ReviewRepository reviewDao;

    @Autowired
    public MoviesController (MoviesRepository mv, ReviewRepository rv){
        moviesDao = mv;
        reviewDao = rv;
    }

@GetMapping("/movie")
public ResponseEntity<?> getAllMovies() {
    List<Movie> allMovies = moviesDao.findAll();
        if(allMovies.isEmpty()){
            return ResponseEntity.badRequest().body("No movies Found");
        }
         return ResponseEntity.accepted().body(allMovies);
}
//starting from here it's really fucked up so
    //remember to handle the exceptions with a better way
    // but for now just keep doing the other things
@GetMapping("/movie/{movieId}")
    public ResponseEntity<?> getMovie (@PathVariable Optional<Integer> movieId){
    if (movieId.isEmpty()){return ResponseEntity.badRequest().body("Put the Id in the Path");} // path with no id, i don't believe it's possible
    Optional<Movie> movie = moviesDao.findById(movieId.get());
    if (movie.isEmpty()){return ResponseEntity.badRequest().body("Movie with Id : "+movieId.get() +" Not found");} // no movie with this Id exception
    return ResponseEntity.accepted().body(new MovieResponseDTO(movie.get(),"Movie sucessfully Quered")); // good request :)
}

@GetMapping("/movie/popular")
public ResponseEntity<?> getHighestRatedMovies () {
        List<Movie> highestRatedMovies = moviesDao.highestRatedMovies();
        if (highestRatedMovies.isEmpty()){return ResponseEntity.badRequest().body("No movies Found");}
        return ResponseEntity.accepted().body(highestRatedMovies);
}


@PostMapping("/movie")
    public ResponseEntity<?> addMovie (@RequestBody Optional<Movie> newmovie){
        try {
            if(newmovie.isEmpty()){return ResponseEntity.badRequest().body("Why would you even send an empty request body :(");}
            moviesDao.save(newmovie.get());
            return ResponseEntity.accepted().body(new MovieResponseDTO(newmovie.get(),"Movie sucessfully created"));
        }
        // shitty catches and dublicates and it don't work with HttpMessageNotReadableException
        catch (Exception e) {
            if (e.getClass().getSimpleName().equals("TransactionSystemException"))
                return ResponseEntity.badRequest().body("make sure to send all the info in the request body");
            else if (e.getClass().getSimpleName().equals("HttpMessageNotReadableException")){
                return ResponseEntity.badRequest().body("Make sure to send the right datatypes in the request body");
            }
            else {
                return ResponseEntity.badRequest().body("Make sure to send the right datatypes in the request body");
            }
        }
}


@PutMapping("/movie")
public ResponseEntity<?> updateMovie (@RequestBody @Valid Movie updatemovie){
        Optional<Movie> existsMovie = moviesDao.findById(updatemovie.getId()); // does one with id exists
        if (existsMovie.isEmpty()){
            return ResponseEntity.badRequest().body("Movie with Id "+updatemovie.getId()+" not found");
        }
        else {
            updatemovie = moviesDao.save(updatemovie);
            return ResponseEntity.accepted().body(new MovieResponseDTO(updatemovie,"Movie sucessfully updated"));
        }
}


@DeleteMapping("/movie/{movieId}")
    public ResponseEntity<?> deleteMovie (@PathVariable Optional<Integer> movieId){
    if (movieId.isEmpty()){return ResponseEntity.badRequest().body("Put the Id in the Path");} // path with no id, i don't believe it's possible
    Optional<Movie> movie = moviesDao.findById(movieId.get());
    if (movie.isEmpty()){return ResponseEntity.badRequest().body("Movie with Id : "+movieId.get() +" Not found");}// no movie with this Id exception
    moviesDao.deleteById(movieId.get());
    return ResponseEntity.accepted().body(new MovieResponseDTO(movie.get(),"Movie sucessfully Deleted"));
}

}
