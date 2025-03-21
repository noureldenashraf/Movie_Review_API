package nour.movie_review_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;


public class ExplictWordsValidator implements ConstraintValidator<NoExplictWords,String> {
    List<ExplicitWords> explicitWords = List.of(ExplicitWords.values());
    private final static String special_Characters = "[@!#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]";

    @Override
    public void initialize(NoExplictWords constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        s = s.replaceAll(special_Characters,""); // removes all the special characters used
         String[] words = s.split("\\s+");  // matches and or more whiteSpaces and split the word after
        for (String word:words) {
            if(explicitWords.contains(word)){ // checks if we got a bad word in the review
                return false;
            }
        }
        return true;
    }
}
