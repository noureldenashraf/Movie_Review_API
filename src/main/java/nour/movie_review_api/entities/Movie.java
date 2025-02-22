package nour.movie_review_api.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "movie", schema = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull(message = "Title Can't be null")
    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @NotNull(message = "Director_Id Can't be null")
    @Column(name = "director_id",nullable = false)
    @JoinColumn(name = "director_id", referencedColumnName = "id")
    private int director_id;

    @NotNull(message = "Genre Can't be null")
    @Lob
    @Column(name = "genre", nullable = false)
    private String genre;

    @NotNull(message = "Release date Can't be null")
    @Column(name = "releasedate")
    private LocalDate releaseDate;

    @Column(name = "average_rating", precision = 3, scale = 2)
    private BigDecimal averageRating;

    public Movie(String title, int director_id, String genre, LocalDate releaseDate, BigDecimal averageRating) {
        this.title = title;
        this.director_id = director_id;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.averageRating = averageRating;
    }
    public Movie () {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDirector_id() {
        return director_id;
    }

    public void setDirector_id(int director_id) {
        this.director_id = director_id;
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

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director_id=" + director_id +
                ", genre='" + genre + '\'' +
                ", releaseDate=" + releaseDate +
                ", averageRating=" + averageRating +
                '}';
    }
}