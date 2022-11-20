package ua.kovalchuk.springvalidationpractice.api.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ua.kovalchuk.springvalidationpractice.api.validator.ValidEmailAddress;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {

    @NotBlank
    private String name;
    @NotBlank
    private String desc;
    @ValidEmailAddress
    private String email;
}
