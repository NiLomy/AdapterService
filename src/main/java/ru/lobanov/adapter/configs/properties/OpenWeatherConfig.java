package ru.lobanov.adapter.configs.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for OpenWeather API.
 */
@Configuration
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "open-weather")
public class OpenWeatherConfig {
    /**
     * API URL.
     */
    private String url;
    /**
     * API key.
     */
    private String key;
    /**
     * Key for main information.
     */
    private String mainKey;
    /**
     * Key for temperature.
     */
    private String tempKey;
}
