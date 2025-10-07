package com.api.fleche.repository;

import com.api.fleche.enums.StatusLike;
import com.api.fleche.model.Like;
import com.api.fleche.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    // Busca se já existe um like entre os usuários
    Optional<Like> findByUserOriginAndUserDestiny(User userOrigin, User userDestiny);

    // Verifica se há um match entre dois usuários
    boolean existsByUserOriginAndUserDestinyAndStatus(User userOrigin, User userDestiny, StatusLike statusLike);

}
