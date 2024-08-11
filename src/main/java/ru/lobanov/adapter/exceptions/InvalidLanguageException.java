package ru.lobanov.adapter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

/**
 * Exception to indicate that there was provided invalid language that is not supported.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidLanguageException extends RuntimeException {
    /**
     * Constructor of this class.
     *
     * @param providedLanguage  language that was provided
     * @param requiredLanguages array of languages that are supported
     */
    public InvalidLanguageException(String providedLanguage, String[] requiredLanguages) {
        super(String.format("You provided %s language but only %s are supported", providedLanguage, Arrays.toString(requiredLanguages)));
    }
}
