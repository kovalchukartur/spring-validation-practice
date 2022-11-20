package ua.kovalchuk.springvalidationpractice;

import lombok.experimental.UtilityClass;
import ua.kovalchuk.springvalidationpractice.api.dto.NoteDto;

@UtilityClass
public class NoteDtoBuilder {

    public NoteDto buildNoteDto(String name, String desc, String email, String verifyEmail) {
        return NoteDto.builder()
            .name(name)
            .desc(desc)
            .email(email)
            .verifyEmail(verifyEmail)
            .build();
    }
}