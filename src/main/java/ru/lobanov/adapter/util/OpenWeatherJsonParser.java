package ru.lobanov.adapter.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import ru.lobanov.adapter.configs.properties.OpenWeatherConfig;

/**
 * Json parser for OpenWeather API.
 *
 * @author NiLomy
 */
@Component
@Validated
@RequiredArgsConstructor
public class OpenWeatherJsonParser {
    /**
     * Configuration properties for OpenWeather API.
     */
    private final OpenWeatherConfig openWeatherConfig;

    /**
     * Parse current temperature from OpenWeather json.
     *
     * @param json provided json form OpenWeather API
     * @return string representation of temperature
     */
    public String parseTemperatureFromOpenWeatherResponse(
            @NotNull(message = "Provided json should not be null")
            @NotBlank(message = "Provided json should not be empty")
            String json
    ) {
        JsonObject weatherJson = JsonParser.parseString(json).getAsJsonObject();
        JsonObject currentWeather = weatherJson.get(openWeatherConfig.getMainKey()).getAsJsonObject();
        JsonElement temperature = currentWeather.get(openWeatherConfig.getTempKey());

        return temperature.getAsString();
    }
}
