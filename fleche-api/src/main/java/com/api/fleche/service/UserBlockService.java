package com.api.fleche.service;

import com.api.fleche.enums.StatusBlockade;
import com.api.fleche.model.User;
import com.api.fleche.model.UserBlock;
import com.api.fleche.model.dtos.UserBlockDto;
import com.api.fleche.model.exception.BlockadeExistsInUsersException;
import com.api.fleche.model.exception.UserNotFounException;
import com.api.fleche.repository.UserBlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.api.fleche.infra.security.SecurityFilter.getAuthenticationUserId;

@Service
@RequiredArgsConstructor
public class UserBlockService {

    private final UserBlockRepository repository;
    private final UserService userService;

    public UserBlockDto blockaedUser(UserBlockDto userBlockDto) {
        var user = userService.findById(getAuthenticationUserId());
        var userBlocked = userService.findById(userBlockDto.getUserIdBlockade());
        existsBlocked(user, userBlocked, StatusBlockade.BLOCKED);
        var blockedUser = new UserBlock();
        blockedUser.setUser(user);
        blockedUser.setBlockedUser(userBlocked);
        blockedUser.setStatus(StatusBlockade.BLOCKED);
        blockedUser.setReason(userBlockDto.getReason());
        var blocked = repository.save(blockedUser);

        return new UserBlockDto(
                userBlocked.getName(),
                blockedUser.getStatus().name(),
                userBlockDto.getReason());
    }

    private void existsBlocked(User user, User blockedUser, StatusBlockade status) {
        Boolean verify = repository.existsByUserAndBlockedUserAndStatus(user, blockedUser, status);
        if (verify) {
            throw new BlockadeExistsInUsersException("There is a block between users");
        }
    }

    @Transactional
    public UserBlockDto removeBlock(Long userIdDesblock) {
        var user = userService.findById(getAuthenticationUserId());
        var userBlocked = userService.findById(userIdDesblock);
        var userDesblock = repository.findByUserAndBlockedUser(user, userBlocked)
                .orElseThrow(() -> new UserNotFounException("User not found for desblock"));
        userDesblock.setStatus(StatusBlockade.REMOVED);
        repository.save(userDesblock);

        return new UserBlockDto(
                userDesblock.getUser().getName(),
                userDesblock.getStatus().name(),
                userDesblock.getReason());
    }

}
