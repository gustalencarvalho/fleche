package com.api.fleche.like_api.repository;

import com.api.fleche.like_api.enums.StatusLike;
import com.api.fleche.like_api.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    // Busca se já existe um like entre os usuários
    Optional<Like> findByUserOriginIdAndUserDestinyId(Long userOrigin, Long userDestiny);

    // Verifica se há um match entre dois usuários
    boolean existsByUserOriginIdAndUserDestinyIdAndStatus(Long userOrigin, Long userDestiny, StatusLike statusLike);

}
