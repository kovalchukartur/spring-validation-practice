package ua.kovalchuk.springvalidationpractice.api.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NoteDto {

    @NotBlank
    private String name;
    @NotBlank
    private String desc;
}
