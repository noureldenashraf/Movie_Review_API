package nour.movie_review_api.Dto;


import nour.movie_review_api.entities.Review;

public class ReviewResponseDTO {
    private Integer id;
    private int movie_id;
    private int user_id;
    private Double reviewRating;
    private String reviewText;

    public ReviewResponseDTO(Review review,Integer id, int movie_id, int user_id, Double reviewRating, String reviewText) {
        this.id = id;
        this.movie_id = movie_id;
        this.user_id = user_id;
        this.reviewRating = reviewRating;
        this.reviewText = reviewText;
    }
}
