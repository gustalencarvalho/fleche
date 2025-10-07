package com.api.fleche.repository;

import com.api.fleche.model.UserLocationSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserLocationSessionRepository extends JpaRepository<UserLocationSession, Long> {
    Optional<UserLocationSession> findByUserIdAndDateExpiresAfter(Long usuarioId, LocalDateTime now);

    @Query(value = "SELECT STATUS_USER_LOCATION FROM USER_LOCATION_SESSION WHERE USER_ID = ?", nativeQuery = true)
    String findByUserId(Long userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE USER_LOCATION_SESSION SET STATUS_USER_LOCATION = ?, LOCATION_ID = ? WHERE USER_ID = ?", nativeQuery = true)
    void checkinOrCheckout(String status, Long locationId, Long userId);

    @Query(value = "SELECT LOCATION_ID FROM USER_LOCATION_SESSION WHERE USER_ID = ?", nativeQuery = true)
    Long findByLocationId(Long userId);

    @Query(value = "SELECT QR_CODE FROM LOCATION WHERE ID = ?", nativeQuery = true)
    String qrCodeBar(Long locationId);

    @Query(value = "SELECT STATUS_USER_LOCATION FROM USER_LOCATION_SESSION WHERE USER_ID = ? AND STATUS_USER_LOCATION = 'ONLINE'", nativeQuery = true)
    String verifyIfUserOnline(Long userId);

}
