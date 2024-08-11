package ru.lobanov.adapter.dto;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;

/**
 * Response of enriched weather data.
 *
 * @param txt         provided text for response
 * @param createdDt   date time of generated response
 * @param currentTemp current temperature in location
 */
public record WeatherResponse(
        @NotNull(message = "Text should not be null")
        String txt,
        @NotNull(message = "Created date time should not be null")
        Instant createdDt,
        @NotNull(message = "Current temperature should not be null")
        Integer currentTemp
) {
}
