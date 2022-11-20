package ua.kovalchuk.springvalidationpractice.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ua.kovalchuk.springvalidationpractice.api.error.MethodArgumentNotValidExceptionHandler;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
//    classes = {
//        MethodArgumentNotValidExceptionHandler.class,
//        UserController.class
//    }
)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenGetUserByIdIsSuccess() throws Exception {
        mockMvc.perform(get("/users/1/search"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(
                content().string(containsString("1235"))
            );
    }

    @Test
    void throwExceptionWhenGetUserByIdAndInvalidId() throws Exception {
        mockMvc.perform(get("/users/test/search"))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(
                content().string(containsString("1235"))
            );
    }
}