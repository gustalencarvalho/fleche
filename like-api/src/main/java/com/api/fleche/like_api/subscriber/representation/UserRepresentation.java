package com.api.fleche.like_api.subscriber.representation;

import com.api.fleche.like_api.enums.StatusLike;
import lombok.Data;

@Data
public class UserRepresentation {
    private Long id;
    private String name;
    private StatusLike status;
}
