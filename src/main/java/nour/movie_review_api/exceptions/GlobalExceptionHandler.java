package nour.movie_review_api.exceptions;


import nour.movie_review_api.entities.ErrorResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
        // implement multiple exceptionhandlers to avoid messing up the http status code :)
        // maybe we want to find a way to get through the httpstatus code

        // {Actually i did find a way tho}
        /**
            the thing is when i had the problem that i can't specifiy the http status code while at the same time
            using only one @ExceptionHandler
            so i fixed it by writing the http status code in the first 3 char in the exception.message()
            so by that i can pass whatever http status code
            String --> Int --> HttpStatusCode
            and about the body i made a substring from the 4th char so i Don't Include the httpStatusCode
         */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions (Exception e){
        System.out.println(e.getMessage().substring(0,2));
        return ResponseEntity.status((HttpStatusCode.valueOf(Integer.parseInt(e.getMessage().substring(0,3))))).body(new ErrorResponse(LocalDateTime.now(),e.getMessage().substring(4),e.getClass().getSimpleName()));
    }





}
