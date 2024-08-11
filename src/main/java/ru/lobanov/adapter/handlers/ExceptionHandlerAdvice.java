package ru.lobanov.adapter.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.lobanov.adapter.dto.ErrorMessage;

import java.util.Date;

/**
 * Class for global exception handling.
 *
 * @author NiLomy
 * @see ru.lobanov.adapter.exceptions.InvalidLanguageException
 * @see ErrorMessage
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    /**
     * Send dto as response with 400 status code.
     *
     * @param ex  caught exception
     * @param req initial web request
     * @return {@code ErrorMessage} to send as server's response with 400 status code.
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleBadRequest(Throwable ex, WebRequest req) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                req.getDescription(false)
        );
    }
}
