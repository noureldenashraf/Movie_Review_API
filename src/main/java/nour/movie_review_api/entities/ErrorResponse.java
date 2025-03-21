package nour.movie_review_api.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class ErrorResponse {
    private LocalDateTime localTime;
    private String message;
    private String details;

    public ErrorResponse(LocalDateTime localTime, String message, String details) {
        this.localTime = localTime;
        this.message = message;
        this.details = details;
    }
}
