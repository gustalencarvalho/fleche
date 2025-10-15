package com.api.fleche.service;

import com.api.fleche.enums.StatusBlockade;
import com.api.fleche.model.User;
import com.api.fleche.model.UserBlock;
import com.api.fleche.model.dtos.UserBlockDto;
import com.api.fleche.model.exception.BlockadeExistsInUsersException;
import com.api.fleche.repository.UserBlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBlockService {

    private final UserBlockRepository repository;
    private final UserService userService;

    public UserBlockDto blockaedUser(UserBlockDto userBlockDto) {
        var user = userService.findById(userBlockDto.getUserId());
        var userBlocked = userService.findById(userBlockDto.getUserIdBlockade());
        existsBlocked(user,userBlocked, StatusBlockade.BLOCKED);
        var blockedUser = new UserBlock();
        blockedUser.setUser(user);
        blockedUser.setBlockedUser(userBlocked);
        blockedUser.setStatus(StatusBlockade.BLOCKED);
        blockedUser.setReason(userBlockDto.getReason());
        var blocked = repository.save(blockedUser);
        return new UserBlockDto(userBlocked.getName(), StatusBlockade.BLOCKED.name(), userBlockDto.getReason());
    }

    private void existsBlocked(User user, User blockedUser, StatusBlockade status) {
        Boolean verify = repository.existsByUserAndBlockedUserAndStatus(user, blockedUser, status);
        if (verify) {
            throw new BlockadeExistsInUsersException("There is a block between users");
        }
    }

}
