package ru.lobanov.adapter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Coordinates of a specific location.
 *
 * @param latitude
 * @param longitude
 */
public record CoordinatesDto(
        @NotNull(message = "Latitude should not be null")
        @NotBlank(message = "Latitude should not be empty")
        String latitude,
        @NotNull(message = "Longitude should not be null")
        @NotBlank(message = "Longitude should not be empty")
        String longitude
) {
}
