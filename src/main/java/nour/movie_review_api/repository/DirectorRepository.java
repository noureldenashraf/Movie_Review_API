package nour.movie_review_api.repository;

import nour.movie_review_api.entities.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director,Integer> {}
