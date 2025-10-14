package com.api.fleche.service;

import com.api.fleche.enums.StatusLike;
import com.api.fleche.model.User;
import com.api.fleche.model.exception.UserNotFounException;
import com.api.fleche.publisher.LikePublisher;
import com.api.fleche.publisher.representation.LikeRepresentation;
import com.api.fleche.publisher.representation.UserRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final UserService userService;
    private final LikePublisher publisher;

    public void like(Long originId, Long destinyId, StatusLike statusLike) {
        var userOrigin = userService.findById(originId);
        var userDestiny = userService.findById(destinyId);

        var userRepresentationOrigin = UserRepresentation.builder()
                .id(userOrigin.getId())
                .build();

        var userRepresentationDestiny = UserRepresentation.builder()
                .id(userDestiny.getId())
                .build();

        var like = new LikeRepresentation(
                userRepresentationOrigin.getId(),
                userRepresentationDestiny.getId(),
                statusLike
        );
        publisher.publisherLike(like);
    }

}
