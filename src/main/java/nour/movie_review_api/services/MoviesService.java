package nour.movie_review_api.services;

import nour.movie_review_api.Dto.MovieListResponseDTO;
import nour.movie_review_api.Dto.MovieResponseDTO;
import nour.movie_review_api.entities.Movie;
import nour.movie_review_api.repository.DirectorRepository;
import nour.movie_review_api.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoviesService {
    final private MoviesRepository moviesDao;
    final private DirectorRepository directorDao;

    @Autowired
    public MoviesService(MoviesRepository movie, DirectorRepository director) {
        this.moviesDao = movie;
        this.directorDao = director;
    }

    // movies controller
    public ResponseEntity<?> getAllMovies() {
        List<Movie> allMovies = moviesDao.findAll();
        if (allMovies.isEmpty()) {
//            return ResponseEntity.badRequest().body("No movies Found");
                throw new RuntimeException("204 No Movies Found");
        }
        return ResponseEntity.accepted().body(new MovieListResponseDTO(allMovies, "All movies Queryed with alphabetic order , " + "Movies count = " + allMovies.size()));
    }


//    public ResponseEntity<?> getMovie(Optional<Integer> movieId) {
//        if (movieId.isEmpty()) {
//            return ResponseEntity.badRequest().body("Put the Id in the Path");
//        } // path with no id, i don't believe it's possible
//        Optional<Movie> movie = moviesDao.findById(movieId.get());
//        if (movie.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(LocalDateTime.now(),));
//        } // no movie with this Id exception
//        return ResponseEntity.accepted().body(new MovieResponseDTO(movie.get(), "Movie sucessfully Quered")); // good request :)
//    }
public ResponseEntity<?> getMovie(Optional<Integer> movieId) {
        Optional<Movie> movie = moviesDao.findById(movieId.get());
        if (movie.isEmpty()) {
            throw new RuntimeException("404 Movie With id : "+movieId.get()+" Not found");
        }
        return ResponseEntity.accepted().body(new MovieResponseDTO(movie.get(), "Movie sucessfully Quered")); // good request :)
}

    public ResponseEntity<?> getHighestRatedMovies() {
        List<Movie> highestRatedMovies = moviesDao.highestRatedMovies();
        if (highestRatedMovies.isEmpty()) {
            return ResponseEntity.badRequest().body("No movies Found");
        }
        return ResponseEntity.accepted().body(highestRatedMovies);
    }

    //starting from here it's really fucked up so
    //remember to handle the exceptions with a better way
    // but for now just keep doing the other things
    public ResponseEntity<?> addMovie(Optional<Movie> newMovie) {
        try {
            if (newMovie.isEmpty()) {
                return ResponseEntity.badRequest().body("Why would you even send an empty request body :(");
            }
            moviesDao.save(newMovie.get());
            return ResponseEntity.accepted().body(new MovieResponseDTO(newMovie.get(), "Movie sucessfully created"));
        }
        // shitty catches and dublicates and it don't work with HttpMessageNotReadableException demo {id: "efsf"}
        catch (Exception e) {
            if (e.getClass().getSimpleName().equals("TransactionSystemException"))
                return ResponseEntity.badRequest().body("make sure to send all the info in the request body");
            else if (e.getClass().getSimpleName().equals("HttpMessageNotReadableException")) {
                return ResponseEntity.badRequest().body("Make sure to send the right datatypes in the request body");
            } else {
                return ResponseEntity.badRequest().body("Make sure to send the right datatypes in the request body");
            }
        }
    }


    public ResponseEntity<?> updateMovie(Movie updatemovie) {
        Optional<Movie> existsMovie = moviesDao.findById(updatemovie.getId()); // does one with id exists
        if (existsMovie.isEmpty()) {
            return ResponseEntity.badRequest().body("Movie with Id " + updatemovie.getId() + " not found");
        } else {
            updatemovie = moviesDao.save(updatemovie);
            return ResponseEntity.accepted().body(new MovieResponseDTO(updatemovie, "Movie sucessfully updated"));
        }
    }


    public ResponseEntity<?> deleteMovie(Optional<Integer> movieId) {
        if (movieId.isEmpty()) {
            return ResponseEntity.badRequest().body("Put the Id in the Path");
        } // path with no id, i don't believe it's possible
        Optional<Movie> movie = moviesDao.findById(movieId.get());
        if (movie.isEmpty()) {
            return ResponseEntity.badRequest().body("Movie with Id : " + movieId.get() + " Not found");
        }// no movie with this Id exception
        moviesDao.deleteById(movieId.get());
        return ResponseEntity.accepted().body(new MovieResponseDTO(movie.get(), "Movie sucessfully Deleted"));
    }


    public ResponseEntity<?> getAllGenres() {
        List<?> allGenres = moviesDao.getALlGenres();
        if (allGenres.isEmpty()) {
            return ResponseEntity.badRequest().body("No genres found");
        }
        return ResponseEntity.accepted().body(allGenres);
    }


    public ResponseEntity<?> getAllMoviesWithGenre(Optional<String> genreName) {
        if (genreName.isEmpty()) {
            return ResponseEntity.badRequest().body("Put genre name in Path");
        }
        List<?> allMoviesWithGenre = moviesDao.getAllMoviesWithGenre(genreName.get());
        if (allMoviesWithGenre.isEmpty()) {
            return ResponseEntity.badRequest().body("No movies with this genre name, make sure to write them correct , here is a list of all genre " + moviesDao.getALlGenres());
        }
        return ResponseEntity.accepted().body(allMoviesWithGenre);
    }
    /////////////////////////////////////////////////////////

}
