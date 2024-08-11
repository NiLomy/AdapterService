package ru.lobanov.adapter.services;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.lobanov.adapter.dto.WeatherRequest;
import ru.lobanov.adapter.dto.WeatherResponse;

/**
 * Interface to get weather data in provided location.
 *
 * @author NiLomy
 * @see ru.lobanov.adapter.services.impl.WeatherServiceImpl
 */
@Validated
public interface WeatherService {
    /**
     * Enriches weather message with temperature data in Celsius of the location.
     *
     * @param request for weather processing
     * @return response with enriched weather data
     */
    WeatherResponse getWeatherFromOpenWeather(
            @NotNull(message = "Request should not be null")
            WeatherRequest request
    );
}
