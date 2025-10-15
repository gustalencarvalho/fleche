package com.api.fleche.repository;

import com.api.fleche.enums.StatusBlockade;
import com.api.fleche.model.User;
import com.api.fleche.model.UserBlock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBlockRepository extends JpaRepository<UserBlock, Long> {
    boolean existsByUserAndBlockedUserAndStatus(User user, User blockedUser, StatusBlockade status);
    Optional<UserBlock> findByUserAndBlockedUser(User user, User blockedUser);
}
