package ua.kovalchuk.springvalidationpractice.api;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
class UserControllerTest {

    private final UserController userController = new UserController();
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController)
        .setHandlerExceptionResolvers(new DefaultHandlerExceptionResolver())
        .build();

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
        Exception exception = mockMvc.perform(get("/users/test/search"))
            .andDo(print())
            .andExpect(status().isBadRequest())
           .andReturn()
            .getResolvedException();

        assertNotNull(exception);
        assertEquals(MethodArgumentTypeMismatchException.class, exception.getClass());
    }

    @Test
    void whenCreateUserNoteByIdIsSuccess() throws Exception {
        mockMvc.perform(
                post("/users/1/note")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\": \"a\", \"desc\": \"a\"}")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(
                content().string(containsString("1235"))
            );
    }

    @Test
    void throwExceptionCreateUserNoteByIdAndInvalidName() throws Exception {
        Exception exception = mockMvc.perform(
                post("/users/1/note")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\": \"\", \"desc\": \"a\"}")
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andReturn()
            .getResolvedException();

        assertNotNull(exception);
        assertEquals(MethodArgumentNotValidException.class, exception.getClass());
    }
}