package ru.lobanov.adapter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Request for weather in provided location.
 *
 * @param msg         provided message
 * @param lng         provided language
 * @param coordinates provided coordinates for location
 */
public record WeatherRequest(
        @NotNull(message = "Message field should not be null")
        @NotBlank(message = "Message should not be empty")
        String msg,
        @NotNull(message = "Language field should not be null")
        @NotBlank(message = "Language field should not be empty")
        String lng,
        @NotNull(message = "Coordinates should not be null")
        CoordinatesDto coordinates
) {
}
