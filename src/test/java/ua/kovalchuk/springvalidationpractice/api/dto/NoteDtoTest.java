package ua.kovalchuk.springvalidationpractice.api.dto;

import java.util.Set;
import java.util.stream.Stream;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NoteDtoTest {

    private static final String NAME_PATH = "name";
    private static final String DESC_PATH = "desc";
    private static final String VALID_VALUE = "desc_value";
    private Validator validator;

    public static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
            Arguments.of(null, VALID_VALUE, NAME_PATH),
            Arguments.of("", VALID_VALUE, NAME_PATH),
            Arguments.of("  ", VALID_VALUE, NAME_PATH),
            Arguments.of(VALID_VALUE, null, DESC_PATH),
            Arguments.of(VALID_VALUE, "  ", DESC_PATH),
            Arguments.of(VALID_VALUE, "  ", DESC_PATH)
        );
    }

    @BeforeEach
    public void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void returnErrorWhenValidateNoteDto(String nameValue, String descValue, String propertyPath) {
        NoteDto noteDto = buildNoteDto(nameValue, descValue);
        Set<ConstraintViolation<NoteDto>> constraintViolations = validator.validate(noteDto);

        assertNotNull(constraintViolations);
        assertFalse(constraintViolations.isEmpty());
        assertEquals(1, constraintViolations.size());
        ConstraintViolation<NoteDto> constraintViolation = constraintViolations.iterator().next();
        assertEquals("must not be blank", constraintViolation.getMessage());
        assertEquals(propertyPath, constraintViolation.getPropertyPath().toString());
    }

    @Test
    void whenValidateNoteDtoIsSuccess() {
        NoteDto noteDto = buildNoteDto("test", "test");
        Set<ConstraintViolation<NoteDto>> constraintViolations = validator.validate(noteDto);

        assertNotNull(constraintViolations);
        assertTrue(constraintViolations.isEmpty());
    }

    private NoteDto buildNoteDto(String name, String desc) {
        return NoteDto.builder()
            .name(name)
            .desc(desc)
            .build();
    }
}