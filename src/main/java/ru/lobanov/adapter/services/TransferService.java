package ru.lobanov.adapter.services;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.lobanov.adapter.dto.WeatherRequest;

/**
 * This is an interface to transfer weather requests.
 *
 * @author NiLomy
 * @see ru.lobanov.adapter.services.impl.TransferServiceImpl
 */
@Validated
public interface TransferService {
    /**
     * Transfer message with enriched weather data.
     *
     * @param request for weather transfer
     */
    void transferMessage(
            @NotNull(message = "Request should not be null")
            WeatherRequest request
    );
}
