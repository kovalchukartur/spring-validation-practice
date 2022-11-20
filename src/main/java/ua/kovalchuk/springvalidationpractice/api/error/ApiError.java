package ua.kovalchuk.springvalidationpractice.api.error;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.validation.FieldError;

@Getter
class ApiError {

    private final int status;
    private final String message;
    private final List<FieldError> fieldErrors = new ArrayList<>();

    ApiError(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public void addFieldError(FieldError fieldError) {
        fieldErrors.add(fieldError);
    }
}
