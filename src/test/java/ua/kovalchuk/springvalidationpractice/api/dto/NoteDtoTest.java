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

    public static final String MAIL_ADDRESS = "mail@gmail.com";
    public static final String VERIFY_MAIL_ADDRESS = "mail@gmail.com";
    public static final String EXPECTED_MESSAGE = "must not be blank";
    private static final String NAME_PATH = "name";
    private static final String DESC_PATH = "desc";
    private static final String EMAIL_PATH = "email";
    private static final String VERIFY_EMAIL_PATH = "";
    private static final String VALID_VALUE = "desc_value";
    private Validator validator;

    public static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
            Arguments.of(null, VALID_VALUE, MAIL_ADDRESS, VERIFY_MAIL_ADDRESS, NAME_PATH, EXPECTED_MESSAGE),
            Arguments.of("", VALID_VALUE, MAIL_ADDRESS, VERIFY_MAIL_ADDRESS, NAME_PATH, EXPECTED_MESSAGE),
            Arguments.of("  ", VALID_VALUE, MAIL_ADDRESS, VERIFY_MAIL_ADDRESS, NAME_PATH, EXPECTED_MESSAGE),
            Arguments.of(VALID_VALUE, null, MAIL_ADDRESS, VERIFY_MAIL_ADDRESS, DESC_PATH, EXPECTED_MESSAGE),
            Arguments.of(VALID_VALUE, "  ", MAIL_ADDRESS, VERIFY_MAIL_ADDRESS, DESC_PATH, EXPECTED_MESSAGE),
            Arguments.of(VALID_VALUE, "  ", MAIL_ADDRESS, VERIFY_MAIL_ADDRESS, DESC_PATH, EXPECTED_MESSAGE),
            Arguments.of(VALID_VALUE, VALID_VALUE, MAIL_ADDRESS, "invalidMailAddress", VERIFY_EMAIL_PATH, "Email addresses do not match!"),
            Arguments.of(VALID_VALUE, VALID_VALUE, "invalidMailAddress", "invalidMailAddress", EMAIL_PATH, "Email address is not recognized")
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
    void returnErrorWhenValidateNoteDto(String nameValue, String descValue, String email, String verifyEmail,
                                        String propertyPath, String expectedMessage) {
        NoteDto noteDto = buildNoteDto(nameValue, descValue, email, verifyEmail);
        Set<ConstraintViolation<NoteDto>> constraintViolations = validator.validate(noteDto);

        assertNotNull(constraintViolations);
        assertFalse(constraintViolations.isEmpty());
        assertEquals(1, constraintViolations.size());
        ConstraintViolation<NoteDto> constraintViolation = constraintViolations.iterator().next();
        assertEquals(expectedMessage, constraintViolation.getMessage());
        assertEquals(propertyPath, constraintViolation.getPropertyPath().toString());
    }

    @Test
    void whenValidateNoteDtoIsSuccess() {
        String email = "Test@email.com";
        NoteDto noteDto = buildNoteDto("name", "desc", email, email);
        Set<ConstraintViolation<NoteDto>> constraintViolations = validator.validate(noteDto);

        assertNotNull(constraintViolations);
        assertTrue(constraintViolations.isEmpty());
    }

    private NoteDto buildNoteDto(String name, String desc, String email, String verifyEmail) {
        return NoteDto.builder()
            .name(name)
            .desc(desc)
            .email(email)
            .verifyEmail(verifyEmail)
            .build();
    }
}