package com.api.fleche.controller;

import com.api.fleche.enums.StatusLike;
import com.api.fleche.model.dtos.StandardError;
import com.api.fleche.publisher.representation.LikeRepresentation;
import com.api.fleche.service.LikeService;
import com.api.fleche.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@Tag(name = "LikeController", description = "Controller responsible for like user")
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;
    private final UserService userService;

    @PostMapping("/{originId}/{destinyId}/{status}")
    @Operation(summary = "Like user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Like user"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class)))
    })
    public ResponseEntity<String> darLike(@PathVariable Long originId, @PathVariable Long destinyId, @PathVariable StatusLike status) {
        likeService.like(originId, destinyId, status);
        return ResponseEntity.ok().build();
    }

}