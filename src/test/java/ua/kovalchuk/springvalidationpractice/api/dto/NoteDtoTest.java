package ua.kovalchuk.springvalidationpractice.api.dto;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NoteDtoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void returnErrorWhenValidateNoteDto() {
        NoteDto noteDto = new NoteDto();
        Set<ConstraintViolation<NoteDto>> constraintViolations = validator.validate(noteDto);

        assertNotNull(constraintViolations);
        assertFalse(constraintViolations.isEmpty());
    }
}