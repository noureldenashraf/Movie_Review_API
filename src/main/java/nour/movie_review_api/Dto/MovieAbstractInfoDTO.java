package nour.movie_review_api.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nour.movie_review_api.entities.Movie;

@Setter
@Getter
@NoArgsConstructor
public class MovieAbstractInfoDTO {
    private int id;
    private String title;
    private String genre;

    public MovieAbstractInfoDTO(Movie movie, int id, String genre, String title) {
        this.id = movie.getId();
        this.genre = movie.getGenre();
        this.title = movie.getTitle();
    }
}
