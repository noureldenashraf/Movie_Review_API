package nour.movie_review_api.controllers;


import jakarta.validation.Valid;
import nour.movie_review_api.entities.Review;
import nour.movie_review_api.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("/review")
public class ReviewsController {
    private ReviewService reviewService;

    public ReviewsController (ReviewService rs) {
        reviewService = rs;
    }

@GetMapping("/review") // all reviews
    public ResponseEntity<?> getAllReviews (@RequestParam(required = false) Optional<Integer> movieId) {
       return reviewService.getAllReviews(movieId);
    }

    @GetMapping("/review/{reviewId}") // get review by Id
    public ResponseEntity<?> getReviewById (@PathVariable Optional<Integer> reviewId) {
        return reviewService.getReviewById(reviewId);
    }

    @PostMapping("/review")
    public ResponseEntity<?> submitReview (@RequestBody @Valid Optional<Review> review) {
        return reviewService.addReview(review);
    }

}
