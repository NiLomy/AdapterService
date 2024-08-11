package ru.lobanov.adapter.configs.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Configuration properties for weather data.
 */
@Configuration
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "weather")
public class WeatherConfig {
    /**
     * All acceptable temperatures in Celsius.
     */
    private Map<String, Integer> temperature;
    /**
     * All acceptable languages.
     */
    private String[] languages;
}
