package ua.kovalchuk.springvalidationpractice.api;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.kovalchuk.springvalidationpractice.api.dto.NoteDto;
import ua.kovalchuk.springvalidationpractice.api.dto.TagDto;

// todo - Intro
//  1. 4 types of passing parameters
//      - path
//      - query
//      - body
//      - header - most usage for balancer - https://www.baeldung.com/spring-rest-http-headers

// todo - Validation - https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#validation
//  - data type validation
//  - db validation

// todo Simple validation can be done on @RestController -  validation
//      - path and query - https://www.baeldung.com/spring-validate-requestparam-pathvariable
//      - body           - https://www.baeldung.com/spring-boot-bean-validation
//          - https://www.baeldung.com/spring-valid-vs-validated
//              - @Valid - essential to trigger the validation of nested attributes
//              - @Validated - essential for group validation
@Slf4j
@RestController
public class UserController {

    // todo
    //  1. Never use final for method parameters in 5 lines methods
    //  2. Never write method more than 5 line
    @GetMapping("/users/{userId}/search")
    public Integer getUserById(@PathVariable Integer userId) {
        log.info("getUserById = {}", userId);
        return searchUserById(userId);
    }

    // MethodArgumentTypeMismatchException
    // MethodArgumentNotValidExceptionHandler
    @PostMapping("/users/{userId}/note")
    public Integer createUserNoteById(@PathVariable Integer userId,
                                      @Valid @RequestBody NoteDto noteDto) {
        log.info("getUserById = {}, {}", userId, noteDto);
        return searchUserById(userId);
    }

    @PostMapping("/users/{userId}/tag")
    public Integer createUserTagById(@PathVariable Integer userId,
                                      @Validated @RequestBody TagDto tagDto) {
        log.info("getUserById = {}, {}", userId, tagDto);
        return searchUserById(userId);
    }

    // todo
    //  never reassign parameters
    private Integer searchUserById(Integer userId) {
        userId = userId + 1234;
        return userId;
    }
}
