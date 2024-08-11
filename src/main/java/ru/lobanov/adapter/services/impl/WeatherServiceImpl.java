package ru.lobanov.adapter.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.lobanov.adapter.configs.properties.OpenWeatherConfig;
import ru.lobanov.adapter.configs.properties.WeatherConfig;
import ru.lobanov.adapter.dto.WeatherRequest;
import ru.lobanov.adapter.dto.WeatherResponse;
import ru.lobanov.adapter.services.WeatherService;
import ru.lobanov.adapter.util.OpenWeatherJsonParser;

import java.time.Instant;

/**
 * Implementation of {@code WeatherService} interface.
 * It uses {@code RestClient} to get weather data in provided location from API.
 *
 * @author NiLomy
 * @see WeatherService
 */
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    /**
     * Pattern of request to weather API.
     */
    public static final String WEATHER_URL_PATTERN = "%s?lat=%s&lon=%s&appid=%s";
    /**
     * Key to get amount of Celsius in Kelvin.
     */
    public static final String KELVIN_KEY = "kelvin";

    /**
     * Parser for OpenWeather API's responses.
     */
    private final OpenWeatherJsonParser jsonParser;
    /**
     * Configuration properties for weather data.
     */
    private final WeatherConfig weatherConfig;
    /**
     * Configuration properties for OpenWeather API.
     */
    private final OpenWeatherConfig openWeatherConfig;
    /**
     * Client for networking.
     */
    private final RestClient restClient = RestClient.create();

    /**
     * Enriches weather message with temperature data in Celsius of the location.
     *
     * @param request for weather processing
     * @return response with enriched weather data
     */
    @Override
    public WeatherResponse getWeatherFromOpenWeather(WeatherRequest request) {
        // maybe implement reactive streams so that the service does not wait for a response for a long time

        // sending request to OpenWeather API
        String getResponse = restClient
                .get()
                .uri(String.format(
                        WEATHER_URL_PATTERN,
                        openWeatherConfig.getUrl(),
                        request.coordinates().latitude(),
                        request.coordinates().longitude(),
                        openWeatherConfig.getKey()
                ))
                .retrieve()
                .toEntity(String.class).getBody();

        // get temperature from json
        String temperature = jsonParser.parseTemperatureFromOpenWeatherResponse(getResponse);

        // convert temperature in Celsius
        Integer currentTemp = (int) (Double.parseDouble(temperature) - weatherConfig.getTemperature().get(KELVIN_KEY));

        // give generated response
        return new WeatherResponse(request.msg(), Instant.now(), currentTemp);
    }
}
