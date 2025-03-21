package nour.movie_review_api.Dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nour.movie_review_api.entities.Movie;

import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@Getter
@Setter
public class MovieResponseDTO {

        private int id;
        private String title;
        private int director_Id;
        private String genre;
        private LocalDate releaseDate;
        private BigDecimal averageRating;
        private String extraInfo;

        public MovieResponseDTO(Movie movie, String extraInfo) {
            this.id = movie.getId();
            this.title = movie.getTitle();
            this.director_Id = movie.getDirector_id();
            this.genre = movie.getGenre();
            this.releaseDate = movie.getReleaseDate();
            this.averageRating = movie.getAverageRating();
            this.extraInfo = extraInfo;
        }
}

