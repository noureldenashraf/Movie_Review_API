package nour.movie_review_api.services;

import nour.movie_review_api.entities.Review;
import nour.movie_review_api.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    final private ReviewRepository reviewDao;

    @Autowired
    public ReviewService(ReviewRepository review) {
        this.reviewDao = review;
    }

    public ResponseEntity<?> getAllReviews(Optional<Integer> movieId) {
        if (movieId.isPresent()) { // case of passing ?movieId
            List<Review> allReviewsWithMovieid = reviewDao.allReviewsWithMovieid(movieId.get());
            if (allReviewsWithMovieid.isEmpty()) {
                return ResponseEntity.badRequest().body("Movie with Id : " + movieId.get() + " got no reviews");
            }
            return ResponseEntity.accepted().body(allReviewsWithMovieid); // good request :)
        }
        List<Review> allReviews = reviewDao.findAll();
        if (allReviews.isEmpty()) {
            return ResponseEntity.badRequest().body("No Reviews Found");
        }
        return ResponseEntity.accepted().body(reviewDao.findAll());
    }

    public ResponseEntity<?> getReviewById(Optional<Integer> reviewId) {
        if (reviewId.isEmpty()) {
            return ResponseEntity.badRequest().body("Please put the id in the path");
        }
        Optional<Review> review = reviewDao.findById(reviewId.get());
        if (review.isEmpty()) {
            return ResponseEntity.badRequest().body("Review with Id : " + reviewId.get() + " Not found");
        }
        return ResponseEntity.accepted().body(review.get());
    }

    public ResponseEntity<?> addReview (Optional<Review> review){
        if(review.isEmpty()){ throw new RuntimeException("400 Bad Review body");}
    }


}

