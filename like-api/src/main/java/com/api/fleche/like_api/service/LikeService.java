package com.api.fleche.like_api.service;

import com.api.fleche.like_api.enums.StatusLike;
import com.api.fleche.like_api.model.Like;
import com.api.fleche.like_api.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository repository;

    public void like(Like like) {
        Optional<Like> likeExist = repository.findByUserOriginIdAndUserDestinyId(like.getUserOriginId(), like.getUserOriginId());
        if (likeExist.isPresent()) {
            like = likeExist.get();
            like.setStatus(like.getStatus());
            repository.save(like);
        }

        if (like.getStatus().equals(StatusLike.LIKE) && verifyMatch(like.getUserOriginId(), like.getUserOriginId())) {
            like.setStatus(StatusLike.FLECHE);
            like.setUserOriginId(like.getUserOriginId());
            like.setUserDestinyId(like.getUserOriginId());
            repository.save(like);
        }
        Like newLike = new Like(like.getUserOriginId(), like.getUserOriginId(), like.getStatus());
        repository.save(newLike);
    }

    public boolean verifyMatch(Long userOrigin, Long userDestiny) {
        boolean status = repository.existsByUserOriginIdAndUserDestinyIdAndStatus(
                userOrigin,
                userDestiny,
                StatusLike.LIKE)
                ||
                repository.existsByUserOriginIdAndUserDestinyIdAndStatus(
                        userDestiny,
                        userOrigin,
                        StatusLike.LIKE);
        return status;
    }
}
