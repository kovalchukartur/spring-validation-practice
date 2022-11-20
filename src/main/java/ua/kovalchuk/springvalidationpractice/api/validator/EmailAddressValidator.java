package ua.kovalchuk.springvalidationpractice.api.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

import static org.apache.logging.log4j.util.Strings.isNotEmpty;

// todo
//  can be DB validator or complex validation
public class EmailAddressValidator implements ConstraintValidator<ValidEmailAddress, String> {

    private static final String EMAIL_ADDRESS_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_ADDRESS_REGEX);

    @Override
    public void initialize(final ValidEmailAddress constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(final String inputStr, final ConstraintValidatorContext context) {
        return isNotEmpty(inputStr) && emailPattern.matcher(inputStr).matches();
    }

}
