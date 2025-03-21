package nour.movie_review_api.repository;

import nour.movie_review_api.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movie,Integer> {

    @Query(value = "SELECT * FROM movie order by title asc",nativeQuery = true)
    List<Movie> findAll();

    @Query(value = "SELECT * FROM movie order by average_rating desc", nativeQuery = true)
    List<Movie> highestRatedMovies();

    @Query(value = "SELECT distinct genre FROM movie", nativeQuery = true)
    List<?> getALlGenres();

    @Query(value = "SELECT * FROM movie WHERE genre = :genreName", nativeQuery = true)
    List<Movie> getAllMoviesWithGenre(@Param("genreName") String genre);

}


