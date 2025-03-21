package nour.movie_review_api.repository;

import java.util.List;

import nour.movie_review_api.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    @Query(value = "SELECT * FROM review where movie_id = :movieId", nativeQuery = true)
    public List<Review> allReviewsWithMovieid(@Param("movieId") int movieId);

//    @Query(value = "SELECT * FROM review where id = :reviewId",nativeQuery = true)
//    public Review review

}
