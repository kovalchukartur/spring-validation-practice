package ua.kovalchuk.springvalidationpractice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import ua.kovalchuk.springvalidationpractice.api.dto.NoteDto;

@UtilityClass
public class ContentUtil {

   public byte[] getContent(NoteDto noteDto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(noteDto);
    }
}