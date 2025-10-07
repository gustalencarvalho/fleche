package com.api.fleche.repository;

import com.api.fleche.model.ProfileUser;
import com.api.fleche.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfilerUserRespository extends JpaRepository<ProfileUser, Long> {
    Optional<ProfileUser> findByUserId(Optional<User> user);
}
