package com.api.fleche.repository;

import com.api.fleche.enums.StatusBlockade;
import com.api.fleche.model.User;
import com.api.fleche.model.UserBlock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBlockRepository extends JpaRepository<UserBlock, Long> {
    boolean existsByUserAndBlockedUserAndStatus(User user, User blockedUser, StatusBlockade status);
}
