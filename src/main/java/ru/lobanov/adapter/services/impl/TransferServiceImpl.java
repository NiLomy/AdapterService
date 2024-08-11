package ru.lobanov.adapter.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.lobanov.adapter.configs.properties.TransferConfig;
import ru.lobanov.adapter.configs.properties.WeatherConfig;
import ru.lobanov.adapter.dto.WeatherRequest;
import ru.lobanov.adapter.dto.WeatherResponse;
import ru.lobanov.adapter.exceptions.InvalidLanguageException;
import ru.lobanov.adapter.services.TransferService;
import ru.lobanov.adapter.services.WeatherService;

import java.util.Arrays;

/**
 * Implementation of {@code TransferService} interface.
 * It uses {@code WeatherService} to enrich message with weather data.
 * It uses {@code RestClient} to send enriched message to another service.
 *
 * @author NiLomy
 * @see TransferService
 */
@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    /**
     * Service to enrich message with weather data.
     */
    private final WeatherService weatherService;
    /**
     * Configuration properties to transfer message to another service.
     */
    private final TransferConfig transferConfig;
    /**
     * Configuration properties for weather data.
     */
    public final WeatherConfig weatherConfig;
    /**
     * Client for networking.
     */
    private final RestClient restClient = RestClient.create();

    /**
     * Transfer message with enriched weather data.
     *
     * @param request for weather transfer
     */
    @Override
    public void transferMessage(WeatherRequest request) {
        // check if request language is in supported languages
        if (Arrays.stream(weatherConfig.getLanguages()).noneMatch(lng -> request.lng().toLowerCase().equalsIgnoreCase(lng))) {
            throw new InvalidLanguageException(request.lng(), weatherConfig.getLanguages());
        } else {
            // get weather response and send it to another service
            WeatherResponse response = weatherService.getWeatherFromOpenWeather(request);

            restClient.post()
                    .uri(transferConfig.getUrl())
                    .body(response)
                    .retrieve();
        }
    }
}
