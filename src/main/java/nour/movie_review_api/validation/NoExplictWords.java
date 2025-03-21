package nour.movie_review_api.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = ExplictWordsValidator.class)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoExplictWords {
    String message() default "Your Review contains inappropriate words.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
