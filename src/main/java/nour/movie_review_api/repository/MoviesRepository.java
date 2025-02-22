package nour.movie_review_api.repository;

import nour.movie_review_api.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<Movie,Integer> {}
