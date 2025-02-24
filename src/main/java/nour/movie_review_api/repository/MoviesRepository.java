package nour.movie_review_api.repository;

import nour.movie_review_api.entities.Movie;
import nour.movie_review_api.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movie,Integer> {
    @Query(value = "SELECT * FROM movie order by average_rating desc",nativeQuery = true)
    List<Movie> highestRatedMovies ();

}


