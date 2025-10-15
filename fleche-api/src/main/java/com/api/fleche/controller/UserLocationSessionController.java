package com.api.fleche.controller;

import com.api.fleche.model.dtos.LocationDto;
import com.api.fleche.model.dtos.StandardError;
import com.api.fleche.model.dtos.UserLocationDto;
import com.api.fleche.model.dtos.UserLocationSessionDto;
import com.api.fleche.service.LocationService;
import com.api.fleche.service.UserLocationSessionService;
import com.api.fleche.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/session")
@Tag(name = "UserLocationSessionController", description = "Controller responsible for check-in/check-out in user")
@RequiredArgsConstructor
public class UserLocationSessionController {

    private final UserLocationSessionService userLocationSessionService;
    private final LocationService locationService;
    private final UserService userService;

    @Operation(summary = "Checkin user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Check-in user"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class)))
    })
    @PostMapping("/checkin/{qrCode}")
    public ResponseEntity<String> checkinUser(@PathVariable String qrCode) {
        userLocationSessionService.checkin(qrCode);
        return ResponseEntity.status(HttpStatus.OK).body("Check-in realized!");
    }

    @Operation(summary = "Check-out user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Check-out user"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class)))
    })
    @PatchMapping("/checkout/{userId}")
    public ResponseEntity<Object> checkoutUser(@PathVariable Long userId) {
        userLocationSessionService.checkout(userId);
        return ResponseEntity.status(HttpStatus.OK).body("Check-out realized");
    }

    @Operation(summary = "Users online list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users online"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class)))
    })
    @GetMapping("/user/online")
    public ResponseEntity<List<UserLocationDto>> usersList() {
        List<UserLocationDto> usuarioBarDtos = userLocationSessionService.usersOnlineList();
        return ResponseEntity.ok(usuarioBarDtos);
    }

    @GetMapping("/users/location/online")
    public ResponseEntity<List<LocationDto>> userssOnline() {
        return ResponseEntity.status(HttpStatus.OK).body(userLocationSessionService.listTotalUserBar());
    }

    @GetMapping("/user/{userId}/authenticated")
    public ResponseEntity<Object> verifyIfUserOnline(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message", userLocationSessionService.verifyIfUserOnline(userId)));
    }

}
