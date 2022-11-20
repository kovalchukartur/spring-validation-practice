package ua.kovalchuk.springvalidationpractice.api;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorViewResolver;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.PathVariableMethodArgumentResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import ua.kovalchuk.springvalidationpractice.api.error.MethodArgumentNotValidExceptionHandler;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}