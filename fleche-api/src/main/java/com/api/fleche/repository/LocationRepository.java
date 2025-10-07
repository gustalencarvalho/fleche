package com.api.fleche.repository;

import com.api.fleche.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByQrCode(String qrCode);
}