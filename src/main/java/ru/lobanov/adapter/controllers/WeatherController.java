package ru.lobanov.adapter.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lobanov.adapter.dto.WeatherRequest;
import ru.lobanov.adapter.services.TransferService;

/**
 * Rest controller to erich request with weather data.
 * It uses {@code TransferService} to send message to another service.
 *
 * @author NiLomy
 */
@Tag(name = "Weather Controller API", description = "Contains all the operations that can be performed on weather")
@Validated
@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    /**
     * It uses to send enriched message to another service.
     */
    private final TransferService transferService;

    /**
     * Processing incoming message and returns certain http status code.
     *
     * @param request provided message to enrich and transfer.
     * @return {@code ResponseEntity} with certain status,
     */
    @Operation(summary = "Process weather request", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully processed request"),
            @ApiResponse(responseCode = "400", description = "Malformed data of request was provided")
    })
    @PostMapping
    public ResponseEntity<?> processWeatherRequest(
            @RequestBody
            @NotNull(message = "Request should not be null")
            @Parameter(description = "Request to process weather", required = true)
            WeatherRequest request
    ) {
        transferService.transferMessage(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
