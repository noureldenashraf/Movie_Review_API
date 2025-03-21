package nour.movie_review_api.Dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nour.movie_review_api.entities.Movie;

import java.util.List;

@Getter
@Setter
@ToString
public class MovieListResponseDTO {
    List<Movie> movies;
    String moreInfo;

    public MovieListResponseDTO(List<Movie> movies, String moreInfo) {
        this.movies = movies;
        this.moreInfo = moreInfo;
    }

    public MovieListResponseDTO() {
    }
}

