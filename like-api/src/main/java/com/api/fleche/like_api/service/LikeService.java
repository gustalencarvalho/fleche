package com.api.fleche.like_api.service;

import com.api.fleche.like_api.enums.StatusLike;
import com.api.fleche.like_api.model.Like;
import com.api.fleche.like_api.model.Match;
import com.api.fleche.like_api.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository repository;
    private final MatchService matchService;

    public void like(Like like) {
        Optional<Like> likeExist = repository.findByUserOriginIdAndUserDestinyId(like.getUserOriginId(), like.getUserDestinyId());
        if (likeExist.isPresent()) {
            like = likeExist.get();
            like.setStatus(like.getStatus());
            repository.save(like);
        }

        if (like.getStatus().equals(StatusLike.LIKE) && verifyMatch(like.getUserOriginId(), like.getUserDestinyId())) {
            var match = new Match();
            match.setUserOriginId(like.getUserOriginId());
            match.setUserDestinyId(like.getUserDestinyId());
            match.setMatchedAt(now());
            matchService.save(match);
        } else {
            Like newLike = new Like(like.getUserOriginId(), like.getUserDestinyId(), like.getStatus());
            repository.save(newLike);
        }
    }

    private boolean verifyMatch(Long userOrigin, Long userDestiny) {
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