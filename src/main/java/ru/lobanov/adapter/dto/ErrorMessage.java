package ru.lobanov.adapter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;

/**
 * HTTP error message.
 *
 * @param status      HTTP status code
 * @param timestamp   date of occurred error
 * @param message     provided message of an error
 * @param description detailed description of an error
 */
public record ErrorMessage(
        @NotNull(message = "Status of the message should not be null")
        @Positive(message = "Status of the message should be positive")
        Integer status,
        @NotNull(message = "Timestamp should not be null")
        Date timestamp,
        @NotNull(message = "Error message should not be null")
        @NotBlank(message = "Error message should not be empty")
        String message,
        String description
) {
}
