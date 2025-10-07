package com.api.fleche.service;

import com.api.fleche.enums.StatusLike;
import com.api.fleche.model.Like;
import com.api.fleche.model.User;
import com.api.fleche.model.exception.UserNotFounException;
import com.api.fleche.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserService userService;

    public Like like(Long originId, Long destinyId, StatusLike statusLike) {
        Optional<User> userOrigin = userService.findById(originId);
        Optional<User> userDestiny = userService.findById(destinyId);
        var like = new Like();

        if (userOrigin.isEmpty() || userDestiny.isEmpty()) {
            throw new UserNotFounException("User not found");
        }

        Optional<Like> likeExist = likeRepository.findByUserOriginAndUserDestiny(userOrigin.get(), userDestiny.get());
        if (likeExist.isPresent()) {
            like = likeExist.get();
            like.setStatus(statusLike);
            return likeRepository.save(like);
        }

        if (statusLike.equals(StatusLike.LIKE) && verifyMatch(userOrigin.get(), userDestiny.get())) {
            like.setStatus(StatusLike.FLECHE);
            like.setUserOrigin(userOrigin.get());
            like.setUserDestiny(userDestiny.get());
            likeRepository.save(like);
            return like;
        }
        Like newLike = new Like(userOrigin.get(), userDestiny.get(), statusLike);
        return likeRepository.save(newLike);
    }

    public boolean verifyMatch(User userOrigin, User userDestiny) {
        boolean status = likeRepository.existsByUserOriginAndUserDestinyAndStatus(
                userOrigin,
                userDestiny,
                StatusLike.LIKE)
                ||
                likeRepository.existsByUserOriginAndUserDestinyAndStatus(
                        userDestiny,
                        userOrigin,
                        StatusLike.LIKE);
        return status;
    }

}
