package nour.movie_review_api.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MovieResponseDTO {

        private int id;
        private String title;
        private int director_Id;
        private String genre;
        private LocalDate releaseDate;
        private BigDecimal averageRating;
        private String extraInfo;  // 👈 Extra row added

        public MovieResponseDTO(Movie movie, String extraInfo) {
            this.id = movie.getId();
            this.title = movie.getTitle();
            this.director_Id = movie.getDirector_id();
            this.genre = movie.getGenre();
            this.releaseDate = movie.getReleaseDate();
            this.averageRating = movie.getAverageRating();
            this.extraInfo = extraInfo;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDirector_Id() {
        return director_Id;
    }

    public void setDirector_Id(int director_Id) {
        this.director_Id = director_Id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    @Override
    public String toString() {
        return "MovieResponseDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director_Id=" + director_Id +
                ", genre='" + genre + '\'' +
                ", releaseDate=" + releaseDate +
                ", averageRating=" + averageRating +
                ", extraInfo='" + extraInfo + '\'' +
                '}';
    }
}

