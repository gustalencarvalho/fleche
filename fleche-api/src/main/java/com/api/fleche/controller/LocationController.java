package com.api.fleche.controller;

import com.api.fleche.model.dtos.LocationRegisterDto;
import com.api.fleche.model.dtos.LocationDto;
import com.api.fleche.model.dtos.StandardError;
import com.api.fleche.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@Tag(name = "LocationController", description = "Controller responsible for register location")
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    @PostMapping("/register")
    @Operation(summary = "Save new location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Location register"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class)))
    })
    public ResponseEntity<Object> registerBar(@RequestBody @Valid LocationRegisterDto locationRegisterDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.registerBar(locationRegisterDto));
    }

    @Operation(summary = "List all locations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "All locations"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class)))
    })
    @GetMapping
    public ResponseEntity<List<LocationDto>> findAllLocations() {
        return ResponseEntity.status(HttpStatus.OK).body(locationService.findAll());
    }

}
