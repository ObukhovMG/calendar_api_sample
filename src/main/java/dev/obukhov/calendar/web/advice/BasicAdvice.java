package dev.obukhov.calendar.web.advice;

import dev.obukhov.calendar.domain.exception.NotFoundException;
import dev.obukhov.calendar.domain.exception.NotUniqueFields;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BasicAdvice {
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String notFoundHandler(NotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotUniqueFields.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String notUniqueHandler(NotUniqueFields ex) {
        return ex.getMessage();
    }

}
