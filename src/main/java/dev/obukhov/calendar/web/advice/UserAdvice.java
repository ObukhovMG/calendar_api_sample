package dev.obukhov.calendar.web.advice;

import dev.obukhov.calendar.domain.exception.UserFieldsNotUnique;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserAdvice {
    @ResponseBody
    @ExceptionHandler(UserFieldsNotUnique.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String userNotUniqueHandler(UserFieldsNotUnique ex) {
        return ex.getMessage();
    }
}
