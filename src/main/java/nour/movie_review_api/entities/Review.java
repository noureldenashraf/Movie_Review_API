package nour.movie_review_api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Review", schema = "movies")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Lob
    @Column(name = "review_rating", nullable = false)
    private String reviewRating;

    @Lob
    @Column(name = "review_text")
    private String reviewText;

}