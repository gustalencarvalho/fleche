package com.api.fleche.publisher.representation;

import com.api.fleche.enums.StatusLike;
import com.api.fleche.model.User;

public record LikeRepresentation(
        Long userOriginId,
        Long userDestinyId,
        StatusLike status
) {
}