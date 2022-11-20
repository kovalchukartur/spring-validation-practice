package ua.kovalchuk.springvalidationpractice.api;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

// https://www.baeldung.com/global-error-handler-in-a-spring-rest-api

// sample response
// {
//    "status": 400,
//    "message": "validation error",
//    "fieldErrors": [
//        {
//            "codes": [
//                "NotBlank.tagDto.name",
//                "NotBlank.name",
//                "NotBlank.java.lang.String",
//                "NotBlank"
//            ],
//            "arguments": [
//                {
//                    "codes": [
//                        "tagDto.name",
//                        "name"
//                    ],
//                    "arguments": null,
//                    "defaultMessage": "name",
//                    "code": "name"
//                }
//            ],
//            "defaultMessage": "must not be blank",
//            "objectName": "tagDto",
//            "field": "name",
//            "rejectedValue": "  ",
//            "bindingFailure": false,
//            "code": "NotBlank"
//        },
//        {
//            "codes": [
//                "NotBlank.tagDto.desc",
//                "NotBlank.desc",
//                "NotBlank.java.lang.String",
//                "NotBlank"
//            ],
//            "arguments": [
//                {
//                    "codes": [
//                        "tagDto.desc",
//                        "desc"
//                    ],
//                    "arguments": null,
//                    "defaultMessage": "desc",
//                    "code": "desc"
//                }
//            ],
//            "defaultMessage": "must not be blank",
//            "objectName": "tagDto",
//            "field": "desc",
//            "rejectedValue": "",
//            "bindingFailure": false,
//            "code": "NotBlank"
//        }
//    ]
//}
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Error methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    private Error processFieldErrors(List<FieldError> fieldErrors) {
        Error error = new Error(BAD_REQUEST.value(), "Validation error");
        for (FieldError fieldError: fieldErrors) {
            error.addFieldError(fieldError);
        }
        return error;
    }

    @Getter
    static class Error {
        private final int status;
        private final String message;
        private final List<FieldError> fieldErrors = new ArrayList<>();

        Error(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public void addFieldError(FieldError fieldError) {
            fieldErrors.add(fieldError);
        }
    }
}
