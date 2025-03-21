package nour.movie_review_api.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import nour.movie_review_api.validation.NoExplictWords;

import java.math.BigDecimal;

@ToString
@NoArgsConstructor
@Getter
@Setter
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

    @NoExplictWords
    @Lob
    @Column(name = "review_text")
    private String reviewText;

    public Review(int movie_id, int user_id, Double reviewRating, String reviewText) {
        this.movie_id = movie_id;
        this.user_id = user_id;
        this.reviewRating = reviewRating;
        this.reviewText = reviewText;
    }
}