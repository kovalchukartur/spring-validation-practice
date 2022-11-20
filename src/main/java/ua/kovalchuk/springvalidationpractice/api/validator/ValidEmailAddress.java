package ua.kovalchuk.springvalidationpractice.api.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailAddressValidator.class)
public @interface ValidEmailAddress {

    String message() default "Email address is not recognized";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}