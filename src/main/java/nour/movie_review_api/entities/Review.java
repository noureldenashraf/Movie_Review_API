package nour.movie_review_api.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "Review", schema = "movies")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Integer id;

    @Column(name = "movie_id",nullable = false)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private int movie_id;

    @Column(name = "user_id",nullable = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private int user_id;

    @NotNull
    @Lob
    @Column(name = "review_rating", nullable = false)
    private Double reviewRating;

    @Lob
    @Column(name = "review_text")
    private String reviewText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public @NotNull Double getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(@NotNull Double reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Review(int movie_id, int user_id, Double reviewRating, String reviewText) {
        this.movie_id = movie_id;
        this.user_id = user_id;
        this.reviewRating = reviewRating;
        this.reviewText = reviewText;
    }

    public Review() {
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", movie_id=" + movie_id +
                ", user_id=" + user_id +
                ", reviewRating='" + reviewRating + '\'' +
                ", reviewText='" + reviewText + '\'' +
                '}';
    }
}