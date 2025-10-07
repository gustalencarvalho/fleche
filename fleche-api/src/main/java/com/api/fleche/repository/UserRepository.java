package com.api.fleche.repository;

import com.api.fleche.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    @Query("SELECT u FROM User u WHERE u.phone = :phone")
    Optional<User> findUserNotProfile(@Param("phone") String phone);
    UserDetails findByPhone(String phone);
}
