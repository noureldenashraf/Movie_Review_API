package nour.movie_review_api.controllers;

import nour.movie_review_api.entities.Movie;
import nour.movie_review_api.entities.Review;
import nour.movie_review_api.repository.ReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/review")
public class ReviewsController {
    private ReviewRepository reviewDao;

    public ReviewsController (ReviewRepository rv) {
        reviewDao = rv;
    }

@GetMapping("/review") // all reviews
    public ResponseEntity<?> getAllReviews (@RequestParam(required = false) Optional<Integer> movieId) {
        if(movieId.isPresent()){ // case of passing ?movieId
            List<Review> allReviewsWithmMovieid = reviewDao.allReviewsWithmMovieid(movieId.get());
            if(allReviewsWithmMovieid.isEmpty()){return ResponseEntity.badRequest().body("Movie with Id : "+movieId.get() +" got no reviews");}
            return ResponseEntity.accepted().body(allReviewsWithmMovieid); // good request :)
        }
    List<Review> allReviews = reviewDao.findAll();
    if (allReviews.isEmpty()){
        return ResponseEntity.badRequest().body("No Reviews Found");
    }
        return ResponseEntity.accepted().body(reviewDao.findAll());
    }

    @GetMapping("/review/{reviewId}") // get review by Id
    public ResponseEntity<?> getReviewById (@PathVariable Optional<Integer> reviewId) {
        if (reviewId.isEmpty()) {return ResponseEntity.badRequest().body("Please put the id in the path");}
        Optional<Review> review =reviewDao.findById(reviewId.get());
        if (review.isEmpty()){return ResponseEntity.badRequest().body("Review with Id : "+reviewId.get()+" Not found");}
        return ResponseEntity.accepted().body(review.get());
    }

}
